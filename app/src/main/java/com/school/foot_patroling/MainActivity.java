package com.school.foot_patroling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.utils.PreferenceHelper;

import java.util.List;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;


public class MainActivity extends AppCompatActivity {
    PreferenceHelper preferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();


        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED
        ) {

            permissionCheckDisplayed();

        }
        else{
            requestPermission();
        }
    }
    private void permissionCheckDisplayed(){
        String lastSyncDate = preferenceHelper.getString(MainActivity.this, BUNDLE_KEY_LAST_SYNC_DATE, "");

        if (lastSyncDate.equals("")) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                            finish();
                        }
                    }, 3000);
        } else {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            startActivity(new Intent(MainActivity.this, NavigationDrawerActivity.class));
                            finish();
                        }
                    }, 3000);
        }
    }
    private void requestPermission(){
//        MultiplePermissionsListener dialogMultiplePermissionsListener =
//                DialogOnAnyDeniedMultiplePermissionsListener.Builder
//                        .withContext(MainActivity.this)
//                        .withTitle("Camera & Storage permission")
//                        .withMessage("Both camera and storage permission are needed.")
//                        .withButtonText(android.R.string.ok)
//                        .withIcon(R.drawable.ic_launcher)
//                        .build();

        Dexter.withActivity(MainActivity.this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                        report.areAllPermissionsGranted();
                        report.isAnyPermissionPermanentlyDenied();
                        report.getDeniedPermissionResponses();
                        permissionCheckDisplayed();
                    }
                    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Log.i("MainActivity", "onPermissionRationaleShouldBeShown");

                    }
                }).check();

    }
}
