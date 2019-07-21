package com.school.foot_patrolling.observations;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.CameraActivity;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;
import com.school.foot_patroling.depotselection.ScheduleListAdapter;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.utils.DatePickerFragment;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_PICNAME;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.FP_PICS_FOLDER;

public class EditObservationFragment extends BaseFragment{
    Observation observationModel;
    private String picname;
    @BindView(R.id.tvChecklistItem)
    TextView tvCheckListItem;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.et_comments)
    TextInputEditText etComments;
    private int CAMERA_PIC_REQUEST = 100;
    private int currentCounter;
    private Uri outputImgUri;
    PreferenceHelper preferenceHelper;
    // Save the camera taken picture in this folder.
    private File pictureSaveFolderPath;
    @OnClick(R.id.btn_submit)
    public void clickOnSave() {
        String updatedComments = etComments.getText().toString().trim();
        observationModel.setObservation(updatedComments);
        NavigationDrawerActivity.mFPDatabase.observationDao().insert(observationModel);
        Toast.makeText(getActivity(), "Comments updated successfully", Toast.LENGTH_LONG).show();
        getActivity().finish();
    }
    @OnClick(R.id.launchCamera)
    public void clickOnCamera(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            picname = "O"+observationModel.getDeviceSeqId()+observationModel.getDeviceId()+getImageCounter()+".jpg";

            pictureSaveFolderPath = new File(Environment.getExternalStorageDirectory(), FP_PICS_FOLDER);
            if(!pictureSaveFolderPath.exists()){
                pictureSaveFolderPath.mkdirs();
            }

            File picToBeSaved = new File(pictureSaveFolderPath, picname);
            if(picToBeSaved.exists()){
                picToBeSaved.delete();
            }
            outputImgUri = getImageFileUriByOsVersion(picToBeSaved);
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImgUri);
            startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
        }
        else{
            requestPermission();
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ComplianceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditObservationFragment newInstance() {
        EditObservationFragment fragment = new EditObservationFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_observation, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String deviceSequenceId = getArguments().getString(BUNDLE_KEY_SELECTED_OBSERVATION);
        currentCounter = preferenceHelper.getInteger(getActivity(), "counter_"+deviceSequenceId,0);
        observationModel = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(deviceSequenceId);
        tvCheckListItem.setText(observationModel.getObservationItem());
        tvLocation.setText(observationModel.getLocation());
        etComments.setText(observationModel.getObservation());


        return view;
    }
    private String getImageCounter(){
        currentCounter++;

        return "_"+currentCounter;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

            // Process result for camera activity.
            if (requestCode == CAMERA_PIC_REQUEST) {

                // If camera take picture success.
                if (resultCode == RESULT_OK) {
                      Toast.makeText(getActivity(), "Image saved successfully", Toast.LENGTH_SHORT).show();
                    preferenceHelper.setInteger(getActivity(), "counter_"+observationModel.getDeviceSeqId(), currentCounter);
                    Log.i("Camera pic location:", ""+outputImgUri);
//                    // Get content resolver.
//                    ContentResolver contentResolver = getActivity().getContentResolver();
//                    // Use the content resolver to open camera taken image input stream through image uri.
//                    InputStream inputStream = contentResolver.openInputStream(outputImgUri);
//                    // Decode the image input stream to a bitmap use BitmapFactory.
//                    Bitmap pictureBitmap = BitmapFactory.decodeStream(inputStream);
////                    // Set the camera taken image bitmap in the image view component to display.
////                    takePictureImageView.setImageBitmap(pictureBitmap);
                }
            }
    }

    private void requestPermission(){
        MultiplePermissionsListener dialogMultiplePermissionsListener =
                DialogOnAnyDeniedMultiplePermissionsListener.Builder
                        .withContext(getActivity())
                        .withTitle("Camera & Storage permission")
                        .withMessage("Both camera and storage permission are needed to store pictures")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.drawable.ic_launcher)
                        .build();

        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(dialogMultiplePermissionsListener).check();

    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    private Uri getImageFileUriByOsVersion(File file)
    {
        Uri ret = null;

        // Get output image unique resource identifier. This uri is used by camera app to save taken picture temporary.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            // /sdcard/ folder link to /storage/41B7-12F1 folder
            // so below code return /storage/41B7-12F1
            File externalStorageRootDir = Environment.getExternalStorageDirectory();

            // contextRootDir = /data/user/0/com.dev2qa.example/files in my Huawei mate 8.
            File contextRootDir = getActivity().getFilesDir();

            // contextCacheDir = /data/user/0/com.dev2qa.example/cache in my Huawei mate 8.
            File contextCacheDir = getActivity().getCacheDir();

            // For android os version bigger than or equal to 7.0 use FileProvider class.
            // Otherwise android os will throw FileUriExposedException.
            // Because the system considers it is unsafe to use local real path uri directly.
            Context ctx = getActivity().getApplicationContext();
            ret = FileProvider.getUriForFile(ctx, "com.win.focus.footpatroling.fileprovider", file);

        }else
        {
            // For android os version less than 7.0 there are no safety issue,
            // So we can get the output image uri by file real local path directly.
            ret = Uri.fromFile(file);
        }

        return ret;
    }
}
