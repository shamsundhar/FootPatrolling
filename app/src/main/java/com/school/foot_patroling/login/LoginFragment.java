package com.school.foot_patroling.login;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.depotselection.SectionsListAdapter;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.CustomAlertDialog;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_SECTION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_USER;

public class LoginFragment extends BaseFragment {
    DatabaseHelper dbhelper = null;
    PreferenceHelper preferenceHelper;
    SQLiteDatabase database;
    @BindView(R.id.input_password)
    EditText etPassword;
    @BindView(R.id.usernameLayout)
    RelativeLayout usernameLayout;
    @BindView(R.id.usernameTV)
    TextView usernameTV;
    @OnClick(R.id.usernameLayout)
    public void usernameClick(){
        displayUserNamePopup();
    }
    String mUsername;
    String mPassword;
    String shaPassword;
    UserLoginAdapter userLoginAdapter;

    @OnClick(R.id.btn_signin)
    public void clickOnLogin(){
        mPassword = etPassword.getText().toString().trim();
        if(validate(mUsername, mPassword)) {
            try {
                if (database != null) {
                    Log.d(TAG, "fetching user id");
                    UserLoginDto userLoginDto = NavigationDrawerActivity.mFPDatabase.userLoginDtoDao().getUserByUnamePassword(mUsername);
                    shaPassword = sha1(mPassword);
                    if(shaPassword.equals(userLoginDto.getCurrentPassword()))
                    {
                        ((NavigationDrawerActivity)getActivity()).setDISPLAY_LOGIN(true);
                        Boolean fpStarted = preferenceHelper.getBoolean(getActivity(), PREF_KEY_FP_STARTED, false);
                        preferenceHelper.setString(getActivity(), PREF_KEY_SELECTED_USER, mUsername);
                        if(fpStarted)
                            ((NavigationDrawerActivity)getActivity()).displayCheckedListFragment();
                        else
                            ((NavigationDrawerActivity)getActivity()).displayDepotSelectionFragment();
                    }
                    else{
                        CustomAlertDialog dialog = new CustomAlertDialog();
                        dialog.showAlert1(getActivity(), R.string.text_alert, "Invalid Username or Password");
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
     * @return A new instance of fragment LoginFragment.
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
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
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
        if(mUsername== null || mUsername.isEmpty()){
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Username");
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
    private void displayUserNamePopup()
    {
        final Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
        listView.setTextFilterEnabled(true);
        //  final List<FootPatrollingSectionsDto> sectionList = NavigationDrawerActivity.mFPDatabase.footPatrollingSectionsDao().getAllFootPatrollingSectionDtosByDepot(selectedDepotId);
        final List<UserLoginDto> userlists = NavigationDrawerActivity.mFPDatabase.userLoginDtoDao().getAllUserLoginDtos();

        if(userlists != null) {
            userLoginAdapter = new UserLoginAdapter(userlists, getActivity().getBaseContext());
            listView.setAdapter(userLoginAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                UserLoginDto dataModel = userlists.get(position);
                usernameTV.setText(dataModel.getUserLoginId());
                mUsername = dataModel.getUserLoginId();
               // selectedSectionID = dataModel.getSeqId();

            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
}
