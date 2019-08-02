package com.school.foot_patroling;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.utils.PreferenceHelper;
import com.school.foot_patrolling.observations.EditObservationFragment;
import com.school.foot_patrolling.observations.ViewObservationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_EDIT_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_VIEW_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_DEPOT;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_SECTION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_USER;

/*
 * AWF - Activity With Fragment. this activity will act as holder activity for fragment - which holds only one fragment.
 *
 * */
public class AWFActivity extends BaseActivity {

    private static final String ADD_COMPLIANCE_FRAGMENT_TAG = "ADD_COMPLIANCE_FRAGMENT";
    private static final String EDIT_OBSERVATION_FRAGMENT_TAG = "EDIT_OBSERVATION_FRAGMENT";
    private static final String VIEW_OBSERVATION_FRAGMENT_TAG = "VIEW_OBSERVATION_FRAGMENT";
    @BindView(R.id.toolbarDetails)
    TextView toolbarDetails;

    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String displayFragment = bundle.getString(BUNDLE_KEY_DISPLAY_FRAGMENT);
            displayHeaderDetails();
            switch (displayFragment){
                case BUNDLE_VALUE_COMPLIANCE :
                    displayAddComplianceFragment(bundle);
                    break;
                case BUNDLE_VALUE_EDIT_OBSERVATION :
                    displayEditObservationFragment(bundle);
                    break;
                case BUNDLE_VALUE_VIEW_OBSERVATION :
                    displayViewObservationFragment(bundle);
                    break;
            }
        }

    }
    public void displayAddComplianceFragment(Bundle bundle){
        setTitle("Add Compliance");

        AddComplianceFragment fragment = AddComplianceFragment.newInstance();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  fragment, ADD_COMPLIANCE_FRAGMENT_TAG)
                .commit();
    }
    public void displayViewObservationFragment(Bundle bundle){
        setTitle("View Observation");
        ViewObservationFragment fragment = ViewObservationFragment.newInstance();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  fragment, VIEW_OBSERVATION_FRAGMENT_TAG)
                .commit();
    }
    public void displayEditObservationFragment(Bundle bundle){
        setTitle("Edit Observation");

        EditObservationFragment fragment = EditObservationFragment.newInstance();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  fragment, EDIT_OBSERVATION_FRAGMENT_TAG)
                .commit();

//        if (ActivityCompat.checkSelfPermission(AWFActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//
//
////            CameraFragment cameraFragment = CameraFragment.newInstance(new Configuration.Builder().build());
////            cameraFragment.takePhotoOrCaptureVideo(new CameraFragmentResultListener() {
//////                @Override
//////                public void onVideoRecorded(byte[] bytes, String filePath) {
//////                    //called when the video record is finished and saved
//////
//////                  //  startActivityForResult(PreviewActivity.newIntentVideo(AWFActivity.this, filePath));
//////                }
////
////                @Override
////                public void onPhotoTaken(byte[] bytes, String filePath) {
////                    //called when the photo is taken and saved
////
////                    startActivity(PreviewActivity.newIntentPhoto(AWFActivity.this, filePath));
////                }
////            });
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container, cameraFragment, "camera tag")
//                    .commit();
//
//        }
//        else{
//            requestCameraPermission();
//        }
    }
    private void requestCameraPermission(){
        Dexter.withActivity(AWFActivity.this)
                .withPermission(Manifest.permission.CAMERA)
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
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AWFActivity.this);
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
    private void displayHeaderDetails(){
        Boolean fpStarted = preferenceHelper.getBoolean(AWFActivity.this, PREF_KEY_FP_STARTED, false);
        if(fpStarted){
            toolbarDetails.setVisibility(View.VISIBLE);
            toolbarDetails.setText(preferenceHelper.getString(AWFActivity.this, PREF_KEY_SELECTED_DEPOT, "")+
                    "/"+
                    preferenceHelper.getString(AWFActivity.this, PREF_KEY_SELECTED_SECTION, "")+
                    "/"+
                    preferenceHelper.getString(AWFActivity.this, PREF_KEY_SELECTED_USER, ""));
        }else{
            toolbarDetails.setVisibility(View.GONE);
        }
    }
    private void setTitle(String title){
        //getSupportActionBar().setTitle(title);
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }
}
