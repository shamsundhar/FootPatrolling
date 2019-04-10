package com.school.foot_patroling.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class LoginFragment extends BaseFragment {
    DatabaseHelper dbhelper = null;
    SQLiteDatabase database;
    @BindView(R.id.input_username)
    EditText etUsername;
    @BindView(R.id.input_password)
    EditText etPassword;
    String mUsername;
    String mPassword;
    String shaPassword;

    @OnClick(R.id.btn_signin)
    public void clickOnLogin(){
         mUsername = etUsername.getText().toString().trim();
         mPassword = etPassword.getText().toString().trim();
         if(validate(mUsername, mPassword)) {
             try {
                 if (database != null) {
                     Log.d(TAG, "fetching user id");
                     String sql = "select user_login_id,current_password from user_login";
                     String db_username = "";
                     String db_password = "";
                     Cursor cursor = database.rawQuery(sql, null);
                     if (cursor.moveToFirst()) {
                         while (!cursor.isAfterLast()) {
                             db_username = cursor.getString(0);
                             db_password = cursor.getString(1);
                             cursor.moveToNext();
                         }
                     }
                     cursor.close();
                     shaPassword = sha1(mPassword);
                     if(shaPassword.equals(db_password))
                     {
                         ((NavigationDrawerActivity)getActivity()).setDISPLAY_LOGIN(true);
                         ((NavigationDrawerActivity)getActivity()).displayCheckedListFragment();
//                         globals.setFacilityNameList(facility.getFacilityNameList());
//                         globals.setFacilityIdList(facility.getFacilityIdList());
//
//                         Intent intent = new Intent(LoginActivity.this, AssetActivity.class);
//                         intent.putExtra("username", username);
//
//                         //   Log.d(TAG, "facility - " +facility.getFacilityId());
//                         startActivity(intent);
//                         finish();
                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        try {
            dbhelper = DatabaseHelper.getInstance(getActivity());
            dbhelper.createDataBase();
            database = dbhelper.getReadableDatabase("Wf@trd841$ams327");

        } catch (Exception e){

            Log.e(TAG, "creating database - "+ e.getMessage());
        }





        return view;
    }
    private String sha1(String password)
    {
        String hash = null;

        try{

            MessageDigest md = MessageDigest.getInstance("SHA");
            byte [] bytes = password.getBytes("UTF-8");
            md.update(bytes,0,bytes.length);
            bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            sb.append("{").append("SHA").append("}");
            for(byte b : bytes){
                sb.append(String.format("%02x",b));
            }
            hash = sb.toString();

            Log.d(TAG,"encrypted is--"+hash);

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return hash;
    }
    private boolean validate(String mUsername, String mPassword){
        boolean validate = true;
        if(mUsername.isEmpty()){
            etUsername.setError("Enter Username");
            validate = false;
        }
        else {
            if(mPassword.isEmpty()){
                etPassword.setError("Enter Password");
                validate = false;
            }
            else{
                etPassword.setError(null);
                validate = true;
            }
        }
        return validate;
    }
}
