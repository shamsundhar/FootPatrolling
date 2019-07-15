package com.school.foot_patroling;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.camerakit.CameraKitView;
import com.school.foot_patroling.R;

import java.io.File;
import java.io.FileOutputStream;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_PICNAME;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.FP_PICS_FOLDER;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class CameraActivity extends AppCompatActivity {
    private CameraKitView cameraKitView;

    private View mControlsView;

private String picName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
        cameraKitView = findViewById(R.id.camera);
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        findViewById(R.id.take_photo_button).setOnClickListener(photoOnClickListener);
        picName = getIntent().getExtras().getString(BUNDLE_KEY_PICNAME);
    }
    // From button OnClickListener
    private View.OnClickListener photoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                @Override
                public void onImage(CameraKitView cameraKitView, final byte[] capturedImage) {
                    File root = new File(Environment.getExternalStorageDirectory(), FP_PICS_FOLDER);
                    if(!root.exists()){
                        root.mkdirs();
                    }
                    File savedPhoto = new File(root, picName);
                    if(savedPhoto.exists()){
                       savedPhoto.delete();
                    }
                    try {
                        FileOutputStream outputStream = new FileOutputStream(savedPhoto.getPath());
                        outputStream.write(capturedImage);
                        outputStream.close();
                        finish();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
