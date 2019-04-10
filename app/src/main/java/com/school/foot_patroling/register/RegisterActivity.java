package com.school.foot_patroling.register;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.school.foot_patroling.BaseActivity;
import com.school.foot_patroling.MainActivity;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DataUpdateDAO;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.datasync.DataSyncActivity;
import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FacilityDto_;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.ProductDto_;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto_;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_CURRENT_SYNC_TIME;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI1;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI2;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_REG_ID;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;

public class RegisterActivity extends BaseActivity {

    @Inject
    RegisterApi registerApi;

    @BindView(R.id.imeiTV)
    TextView imeiTV;

    @BindView(R.id.et_url)
    EditText etUrl;
    String TAG = "Registration Activity";
    String selectedImei;
    DatabaseHelper dbhelper;
    PreferenceHelper preferenceHelper;
    @OnClick(R.id.imeiLayout)
    public void clickImei(){
        displayImeiPopup();
    }
    @OnClick(R.id.btn_register)
    public void clickRegister() {
        if (validate()) {
            if (ActivityCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (Common.isNetworkAvailable(RegisterActivity.this)) {
                    String url = etUrl.getText().toString().trim();

                    final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage(getString(R.string.text_please_wait));
                    progressDialog.show();

                    preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_URL, url);
                    preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_IMEI1, imeiList.get(0));
                    preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_IMEI2,imeiList.get(1) );
                    url = url + "/warehouse/fpApp/get-fp-data";
                    RegistrationRequestModel model = new RegistrationRequestModel();
                    model.setAppName("TRD_FP");
                    model.setCurrentTimestamp(DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S"));
                    model.setImeiNumber(selectedImei);
                    model.setPreviousTimestamp("31-01-1990 17:26:15.613");
                    registerApi.register(url, model)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<MasterDto>() {
                                @Override
                                public void onError(Throwable e) {
                                    System.out.println("error called::" + e.fillInStackTrace());
                                }

                                @Override
                                public void onComplete() {
                                    System.out.println("complete called");
                                }

                                @Override
                                public void onSubscribe(Disposable d) {
                                    System.out.println("onsubscribe called");
                                }

                                @Override
                                public void onNext(MasterDto masterDto) {
                                    if (masterDto.getImeiAuth()){
                                        progressDialog.dismiss();
                                        //   preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_REG_ID, masterDto.getRegistrationId());
                                        preferenceHelper.setBoolean(RegisterActivity.this, BUNDLE_KEY_AUTH, masterDto.getImeiAuth());
                                        try {
                                            dbhelper = DatabaseHelper.getInstance(RegisterActivity.this);
                                            dbhelper.deleteDatabase();
                                            dbhelper.createDataBase();

                                            DatabaseHelper dbhelper = DatabaseHelper.getInstance(RegisterActivity.this);
                                            SQLiteDatabase db = dbhelper.getDBObject(0);
                                            String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(Calendar.getInstance().getTime());
                                            preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_LAST_SYNC_DATE, currentTime);
                                            String result = syncMasterData(db, masterDto);

                                        } catch (Exception e){

                                            Log.e(TAG, "creating database - "+ e.getMessage());
                                        }
                                        startActivity(new Intent(RegisterActivity.this, NavigationDrawerActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            } else {
                requestAccessStatePermission();
            }
        }
    }
    List<String> imeiList = new ArrayList<String>();
    IMEIListAdapter listAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_register2);
        ButterKnife.bind(this);

        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        requestPhoneStatePermission();
        requestAccessStatePermission();

    }
    private void readIMEI(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imeiList.add(telephonyManager.getDeviceId(0).toString());
            imeiList.add(telephonyManager.getDeviceId(1).toString());
        }
        else{
            requestPhoneStatePermission();
        }
    }
    private void displayImeiPopup()
    {
        final Dialog builder = new Dialog(RegisterActivity.this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = builder.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        builder.setContentView(R.layout.popup_listview);

        final ListView listView = (ListView) builder.findViewById(R.id.popupListView);
        listView.setTextFilterEnabled(true);
        if(imeiList != null) {
            listAdapter = new IMEIListAdapter(imeiList, getBaseContext());
            listView.setAdapter(listAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                selectedImei = imeiList.get(position);
                imeiTV.setText( imeiList.get(position));
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }

    /**
     * Requesting camera permission
     * This uses single permission model from dexter
     * Once the permission granted, opens the camera
     * On permanent denial opens settings dialog
     */
    private void requestPhoneStatePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_PHONE_STATE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        readIMEI();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }
    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    /**
     * Requesting camera permission
     * This uses single permission model from dexter
     * Once the permission granted, opens the camera
     * On permanent denial opens settings dialog
     */
    private void requestAccessStatePermission() {
        Dexter.withActivity(RegisterActivity.this)
                .withPermission(Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public boolean validate() {
        boolean valid = true;

        String url = etUrl.getText().toString().trim();
        if (url.isEmpty()) {
            etUrl.setError("Enter a valid URL");
            valid = false;
        } else {
            etUrl.setError(null);
            if (!Common.isValidURL(url)) {
                etUrl.setError("Enter between 4 and 10 alphanumeric characters");
                valid = false;
            } else {
                etUrl.setError(null);
            }
        }
        return valid;
    }

    private String syncMasterData(final SQLiteDatabase db, MasterDto masterDto) {

        String result = "Failed";

        try {

            Log.d(TAG, "Performing");

            String currentTimeStamp = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
            preferenceHelper.setString(RegisterActivity.this, BUNDLE_KEY_CURRENT_SYNC_TIME, currentTimeStamp);

            String sql1 = " ";
            String sql2 = " ";
            String sql3 = " ";

            String args[] = {};
            Cursor c1 = null;
            Cursor c2 = null;
            Cursor c3 = null;

            Response response = null;


//                try {
//
//                    ResponseFacilityDto responseFacilityDto = dto.getCreatedResponseFacilityDto();
//                    List<FacilityDto> facilityDtos = responseFacilityDto.getFacilityDtos();
//                    Log.d(TAG,"flow order check here**");
//                   /* for (FacilityDto facilityDto : facilityDtos) {
//                        Log.d(TAG, ".if..facility..id.." + facilityDto.getFacilityId());
//                    }*/
//                    progressValue = progressValue + 2;
//                    publishProgress(progressValue);
//
//                    if (dto != null) {
//                        Log.d(TAG, "entering this block");
//
            if (updateDatabase(masterDto, db)) {

                result = "Success";

            } else {

                result = "Failed";

            }
//
//
//                        Log.d(TAG, "Value of result**" + result);
//
//
//                    } else
//
//                    {
//                        Log.d(TAG, "not coming");
//                    }
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//                }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    private boolean updateDatabase(MasterDto dto, SQLiteDatabase db) {

        Log.d(TAG, "flow checking**");
        boolean result = false;

        int progressValue = 7;

        if (dto != null) {

            Log.d(TAG,"in update database method");

            try {
                DataUpdateDAO dataUpdateDAO = DataUpdateDAO.getInstance();

                List<FacilityDto> insertFacilityDtos = dto.getCreatedResponseFacilityDto().getFacilityDtos();

                if (insertFacilityDtos != null && insertFacilityDtos.size() > 0) {

                    Log.d(TAG, "facility insert records : " + insertFacilityDtos.size());

                    for (FacilityDto facilityDto : insertFacilityDtos) {

                        dataUpdateDAO.insertFacilityData(facilityDto, db);
                    }

                    progressValue = progressValue + 1;
//                    publishProgress(progressValue);
                }

                List<FacilityDto_> updateFacilityDtos = dto.getUpdatedResponseFacilityDto().getFacilityDtos();

                if (updateFacilityDtos != null && updateFacilityDtos.size() > 0) {
                    Log.d(TAG, "facility update records : " + updateFacilityDtos.size());

                    for (FacilityDto_ facilityDto : updateFacilityDtos) {

                        dataUpdateDAO.updateFacilityData(facilityDto, db);
                    }
                    progressValue = progressValue + 1;
                    // publishProgress(progressValue);
                }

                List<ProductDto> insertProductDtos = dto.getCreatedResponseProductDto().getProductDtos();

                if (insertProductDtos != null && insertProductDtos.size() > 0) {

                    Log.d(TAG, "product insert records : " + insertProductDtos.size());

                    for (ProductDto productDto : insertProductDtos) {
                        dataUpdateDAO.insertProductData(productDto, db);
                    }
                    progressValue = progressValue + 1;
                    //  publishProgress(progressValue);
                }

                List<ProductDto_> updateProductDtos = dto.getUpdatedResponseProductDto().getProductDtos();

                if (updateProductDtos != null && updateProductDtos.size() > 0) {
                    Log.d(TAG, "product update records : " + updateProductDtos.size());


                    for (ProductDto_ productDto : updateProductDtos) {
                        dataUpdateDAO.updateProductData(productDto, db);
                    }
                    progressValue = progressValue + 1;
                    //   publishProgress(progressValue);
                }
                List<UserLoginDto> insertUserLoginDtos = dto.getCreatedResponseUserLoginDto().getUserLoginDtos();

                if (insertUserLoginDtos != null && insertUserLoginDtos.size() > 0) {

                    Log.d(TAG, "User Login Dto insert records : " + insertUserLoginDtos.size());


                    for (UserLoginDto userLoginDto : insertUserLoginDtos) {

                        dataUpdateDAO.insertUserLoginData(userLoginDto,db);
                    }

                    progressValue = progressValue + 1;
                    //    publishProgress(progressValue);
                }

                List<UserLoginDto_> updateUserLoginDtos = dto.getUpdatedResponseUserLoginDto().getUserLoginDtos();

                if (updateUserLoginDtos != null && updateUserLoginDtos.size() > 0) {

                    Log.d(TAG, "User Login Dto update records : " + updateUserLoginDtos.size());

                    for (UserLoginDto_ userLoginDto : updateUserLoginDtos) {

                        dataUpdateDAO.updateUserLoginData(userLoginDto, db);
                    }

                    progressValue = progressValue + 1;
                    //  publishProgress(progressValue);
                }

                result = true;
            } catch (Exception e) {

                Log.e(TAG, "updateDatabase  - : " + e.getMessage());
                e.printStackTrace();
            }


        } else {

            result = true;
        }


        return result;
    }
}
