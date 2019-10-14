package com.school.foot_patrolling.observations;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.PreferenceHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;
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
    @BindView(R.id.tvAttachmentsCounter)
    TextView attachmentsCounter;
    private int CAMERA_PIC_REQUEST = 100;
    private int GALLERY_PIC_REQUEST = 101;
    private int currentCounter;
    private Uri outputImgUri;
    PreferenceHelper preferenceHelper;
    ProgressDialog progressDialog;
    // Save the camera taken picture in this folder.
    private File pictureSaveFolderPath;
    @OnClick(R.id.btn_submit)
    public void clickOnSave() {
        String updatedComments = etComments.getText().toString().trim();
        observationModel.setObservation(updatedComments);
        NavigationDrawerActivity.mFPDatabase.observationDao().insert(observationModel);
        Toast.makeText(getActivity(), "Observation updated successfully", Toast.LENGTH_LONG).show();
        View view = getView().getRootView();
        Common.hideKeyboardFrom(getActivity(), view);
        Intent resultIntent = new Intent();
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        getActivity().finish();
    }
    @OnClick(R.id.launchCamera)
    public void clickOnCamera(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            picname = "O_"+observationModel.getDeviceSeqId()+"_"+observationModel.getDeviceId()+getImageCounter()+".jpg";

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
    @OnClick(R.id.launchGallery)
    public void launchGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_PIC_REQUEST);
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
        attachmentsCounter.setText("Attachments : "+currentCounter);


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
                    attachmentsCounter.setText("Attachments : "+currentCounter);
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
            else if(requestCode == GALLERY_PIC_REQUEST){
                if(resultCode == RESULT_OK){
                    Log.i("gallery pic location", ""+data.getData());

                    progressDialog = new ProgressDialog(getActivity(),
                            R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setMessage(getString(R.string.text_please_wait));
                    progressDialog.show();

                    picname = "O_"+observationModel.getDeviceSeqId()+"_"+observationModel.getDeviceId()+getImageCounter()+".jpg";

                    pictureSaveFolderPath = new File(Environment.getExternalStorageDirectory(), FP_PICS_FOLDER);
                    if(!pictureSaveFolderPath.exists()){
                        pictureSaveFolderPath.mkdirs();
                    }

                    File picToBeSaved = new File(pictureSaveFolderPath, picname);
                    if(picToBeSaved.exists()){
                        picToBeSaved.delete();
                    }


                    Uri selectedImage = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        storeImageInFPDirectory(bitmap, picToBeSaved);
                        attachmentsCounter.setText("Attachments : "+currentCounter);
                        Toast.makeText(getActivity(), "Image saved successfully", Toast.LENGTH_SHORT).show();
                        preferenceHelper.setInteger(getActivity(), "counter_"+observationModel.getDeviceSeqId(), currentCounter);
                        progressDialog.cancel();
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        progressDialog.cancel();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        progressDialog.cancel();
                    }

                }
                else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.e("EditObservationFragment", "Selecting picture cancelled");
                }
            }
    }
//    private void uriToBitmap(Uri selectedFileUri, File picToBeSaved) {
//        try {
//            ParcelFileDescriptor parcelFileDescriptor =
//                    getActivity().getContentResolver().openFileDescriptor(selectedFileUri, "r");
//            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
//            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//            storeImageInFPDirectory(image, picToBeSaved);
//
//            parcelFileDescriptor.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public boolean storeImageInFPDirectory(Bitmap imageData, File pictobesaved) {

        try {
            String filePath = pictobesaved.getPath();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            //Toast.makeText(m_cont, "Image Saved at----" + filePath, Toast.LENGTH_LONG).show();
            // choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }
        return true;
    }
//    private void copyFile2(File inputFile, File outputFile) {
//
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//
//            //create output directory if it doesn't exist
//         //   File dir = new File (outputPath);
//            if (!outputFile.exists())
//            {
//                outputFile.mkdirs();
//            }
//
//
//            in = new FileInputStream(inputFile);
//            out = new FileOutputStream(outputFile);
//
//            byte[] buffer = new byte[1024];
//            int read;
//            while ((read = in.read(buffer)) != -1) {
//                out.write(buffer, 0, read);
//            }
//            in.close();
//            in = null;
//
//            // write the output file (You have now copied the file)
//            out.flush();
//            out.close();
//            out = null;
//
//        }  catch (FileNotFoundException fnfe1) {
//            Log.e("tag", fnfe1.getMessage());
//        }
//        catch (Exception e) {
//            Log.e("tag", e.getMessage());
//        }
//
//    }
    /**
     * helper to retrieve the path of an image URI
     */
//    public String getPath(Uri uri) {
//        // just some safety built in
//        if( uri == null ) {
//            // TODO perform some logging or show user feedback
//            return null;
//        }
//        // try to retrieve the image from the media store first
//        // this will only work for images selected from gallery
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
//        if( cursor != null ){
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            String path = cursor.getString(column_index);
//            cursor.close();
//            return path;
//        }
//        // this is our fallback here
//        return uri.getPath();
//    }
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
//    private void copyFile(File sourceFile, File destFile) throws IOException {
//        if (!sourceFile.exists()) {
//            return;
//        }
//
//        FileChannel source = null;
//        FileChannel destination = null;
//        source = new FileInputStream(sourceFile).getChannel();
//        destination = new FileOutputStream(destFile).getChannel();
//        if (destination != null && source != null) {
//            destination.transferFrom(source, 0, source.size());
//        }
//        if (source != null) {
//            source.close();
//        }
//        if (destination != null) {
//            destination.close();
//        }
//
//
//    }
//
//
//    public String getRealPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
//        if(cursor.moveToFirst()){;
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//        return res;
//    }
}
