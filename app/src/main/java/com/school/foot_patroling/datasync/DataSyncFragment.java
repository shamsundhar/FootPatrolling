package com.school.foot_patroling.datasync;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.GenericFileProvider;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DataUpdateDAO;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.localdbstatus.LocalDBStatusFragment;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.register.RegisterApi;
import com.school.foot_patroling.register.model.AppToServerCreatedFootPatrollingInspectionDto;
import com.school.foot_patroling.register.model.AppToServerCreatedResponseCompliancesDto;
import com.school.foot_patroling.register.model.AppToServerCreatedResponseObservationsDto;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FacilityDto_;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto_;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto_;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.Constants;
import com.school.foot_patroling.utils.CustomAlertDialog;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;
//import com.fasterxml.jackson.databind.ObjectMapper;
import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_CURRENT_SYNC_TIME;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI1;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI2;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT2;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT5;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT6;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT7;
import static com.school.foot_patroling.utils.Constants.FP_PICS_FOLDER;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;

public class DataSyncFragment extends BaseFragment {
    @Inject
    RegisterApi registerApi;
    PreferenceHelper preferenceHelper;
    String TAG = "DataSyncFragment";
    @BindView(R.id.tvDataSyncStartTime)
    TextView tvSyncStartTime;
    @BindView(R.id.tvDataSyncFinishTime)
    TextView tvSyncEndTime;
    @BindView(R.id.tvLastDataSyncDate)
    TextView tvLastSyncDate;
    @BindView(R.id.tvDataSyncResult)
    TextView tvResult;
    @BindView(R.id.tvDataSyncStatus)
    TextView tvSyncStatus;
    String url;
    ProgressDialog progressDialog;
    @OnClick(R.id.btn_syncNow)
    public void clickSyncNow() {
        Boolean isInspectionInProgress = preferenceHelper.getBoolean(getActivity(), PREF_KEY_FP_STARTED, Boolean.FALSE);
        if(!isInspectionInProgress) {
            if (Common.isNetworkAvailable(getActivity())) {
                //  String url = etUrl.getText().toString().trim();
                String syncStartTime = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
                tvSyncStartTime.setText("Start Time : " + syncStartTime);
                tvSyncStatus.setText("Data Sync Status : Started");
                progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage(getString(R.string.text_please_wait));
                progressDialog.show();

                String url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
                //preferenceHelper.setString(getActivity(), BUNDLE_KEY_IMEI1, imeiList.get(0));
                // preferenceHelper.setString(getActivity(), BUNDLE_KEY_IMEI2,imeiList.get(1));
                String selectedImei = preferenceHelper.getString(getActivity(), BUNDLE_KEY_SELECTED_IMEI, "");
                String lastSyncDate = preferenceHelper.getString(getActivity(), BUNDLE_KEY_LAST_SYNC_DATE, "");

                url = url + Constants.REST_GET_FP_DATA;
                RegistrationRequestModel model = new RegistrationRequestModel();
                model.setAppName("TRD_FP");
                model.setCurrentTimestamp(syncStartTime);
               // model.setImeiNumber("867520040587478");
                //TODO
                model.setImeiNumber(selectedImei);
                model.setPreviousTimestamp(lastSyncDate);
                List<Inspection> inspectionDtoList = NavigationDrawerActivity.mFPDatabase.inspectionDao().getNotSyncedInspection();
                AppToServerCreatedFootPatrollingInspectionDto appToServerCreatedFootPatrollingInspectionDto = new AppToServerCreatedFootPatrollingInspectionDto();
                appToServerCreatedFootPatrollingInspectionDto.setCount("" + inspectionDtoList.size());
                appToServerCreatedFootPatrollingInspectionDto.setFootPatrollingInspectionDtos(inspectionDtoList);

                model.setAppToServerCreatedFootPatrollingInspectionDto(appToServerCreatedFootPatrollingInspectionDto);

                List<Observation> observationList = NavigationDrawerActivity.mFPDatabase.observationDao().getNotSyncedObservation();
                AppToServerCreatedResponseObservationsDto appToServerCreatedResponseObservationsDto = new AppToServerCreatedResponseObservationsDto();
                appToServerCreatedResponseObservationsDto.setCount("" + observationList.size());
                appToServerCreatedResponseObservationsDto.setObservationsDtos(observationList);

                model.setAppToServerCreatedResponseObservationsDto(appToServerCreatedResponseObservationsDto);

                List<Compliance> complianceList = NavigationDrawerActivity.mFPDatabase.complianceDao().getNotSyncedCompliance();
                AppToServerCreatedResponseCompliancesDto appToServerCreatedResponseCompliancesDto = new AppToServerCreatedResponseCompliancesDto();
                appToServerCreatedResponseCompliancesDto.setCompliancesDtos(complianceList);
                appToServerCreatedResponseCompliancesDto.setCount(""+complianceList.size());

                model.setAppToServerCreatedResponseCompliancesDto(appToServerCreatedResponseCompliancesDto);

                registerApi.register(url, model)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MasterDto>() {
                            @Override
                            public void onError(Throwable e) {
                                System.out.println("error called::" + e.fillInStackTrace());
                                progressDialog.dismiss();
                                tvResult.setText("Result : Failed");
                                tvSyncStatus.setText("Data Sync Status : Failed");
                                String syncEndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(Calendar.getInstance().getTime());
                                tvSyncEndTime.setText("Finish Time : " + syncEndTime);
                            }

                            @Override
                            public void onComplete() {
                                System.out.println("complete called");

                                uploadImages();
                            }

                            @Override
                            public void onSubscribe(Disposable d) {
                                System.out.println("onsubscribe called");
                            }

                            @Override
                            public void onNext(MasterDto masterDto) {
                                if (masterDto.getImeiAuth()) {
                                    progressDialog.dismiss();
                                    try {
                                        String result = syncMasterData(masterDto);
                                        tvResult.setText("Result : " + result);
                                        tvSyncStatus.setText("Data Sync Status : Completed");

                                        if (result.equals("Success")) {
                                            String syncEndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(Calendar.getInstance().getTime());
                                            preferenceHelper.setString(getActivity(), BUNDLE_KEY_LAST_SYNC_DATE, syncEndTime);
                                            tvSyncEndTime.setText("Finish Time : " + syncEndTime);
                                        } else {

                                        }
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        });
            }
        }
        else{
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Please sync after closing inspection");
        }

    }

    private void uploadImages() {
        url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
        url = url + Constants.REST_POST_FILE_UPLOAD;
//        Map<String, byte[]> imagesMap = new HashMap<String, byte[]>();

        final File directory = new File(Environment.getExternalStorageDirectory() + FP_PICS_FOLDER);

        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);

int totalFiles = files.length;
        for (int i = 0; i < totalFiles; i++)
        {
            final File selectedFile = files[i];
            String message = "Uploading "+(i+1) +" / "+(totalFiles)+" images, Please wait.";
            progressDialog.setMessage(message);
            progressDialog.show();
            Map<String, String> filesMap = new HashMap<String, String>();
            byte[] bytesArray = new byte[(int) selectedFile.length()];
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                fis.read(bytesArray); //read file into bytes[]
                fis.close();
                String base64 = Base64.encodeToString(bytesArray, Base64.NO_WRAP);
                filesMap.put(files[i].getName(), base64);

                FileUpload fileUpload = new FileUpload();
                fileUpload.setFileData(filesMap);

                registerApi.fileUpload(url, fileUpload)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                progressDialog.show();
                            }

                            @Override
                            public void onNext(Object o) {
                                progressDialog.dismiss();
                                //TODO delete files
                                Log.d("next::", "next called");
                                // directory.delete();
                                selectedFile.delete();
                            }

                            @Override
                            public void onError(Throwable e) {
                                progressDialog.dismiss();
                                Log.d("error::", "error called");
                            }

                            @Override
                            public void onComplete() {
                                progressDialog.setMessage(getString(R.string.text_please_wait));
                                progressDialog.dismiss();
                                Log.d("complete::", "complete called");
                            }
                        });


//                Call<Object> call =
//                        registerApi.downloadFileWithDynamicUrlSync(url);
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d(TAG, "request success");
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e(TAG, "request failed");
//                    }
//                };);







            }
            catch (Exception e)
            {
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
    public static DataSyncFragment newInstance() {
        DataSyncFragment fragment = new DataSyncFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_sync, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        String date = preferenceHelper.getString(getActivity(), BUNDLE_KEY_LAST_SYNC_DATE, "");
        if(!date.isEmpty()) {
            tvLastSyncDate.setText("Last sync Date : " + DateTimeUtils.parseDateTime(date, DATE_FORMAT6, DATE_FORMAT7));
        }
        return view;
    }

    private String syncMasterData(MasterDto masterDto) {

        String result = "Failed";

        try {

            Log.d(TAG, "sync master data");

            String currentTimeStamp = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
            preferenceHelper.setString(getActivity(), BUNDLE_KEY_CURRENT_SYNC_TIME, currentTimeStamp);

            if (updateDatabase(masterDto)) {

                result = "Success";

            } else {

                result = "Failed";

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    private boolean updateDatabase(MasterDto dto) {

        Log.d(TAG, "flow checking**");
        boolean result = false;

        if (dto != null) {

            Log.d(TAG,"in update database method");

            try {
                List<FacilityDto> insertFacilityDtos = dto.getCreatedResponseFacilityDto().getFacilityDtos();

                if (insertFacilityDtos != null && insertFacilityDtos.size() > 0) {

                    Log.d(TAG, "facility insert records : " + insertFacilityDtos.size());

                    for (FacilityDto facilityDto : insertFacilityDtos) {

                        //dataUpdateDAO.insertFacilityData(facilityDto, db);
                        NavigationDrawerActivity.mFPDatabase.facilityDtoDao().insert(facilityDto);

                    }

                }

                List<FacilityDto_> updateFacilityDtos = dto.getUpdatedResponseFacilityDto().getFacilityDtos();

                if (updateFacilityDtos != null && updateFacilityDtos.size() > 0) {
                    Log.d(TAG, "facility update records : " + updateFacilityDtos.size());

                    for (FacilityDto_ facilityDto : updateFacilityDtos) {

                        //dataUpdateDAO.updateFacilityData(facilityDto, db);
                        NavigationDrawerActivity.mDtoWrapper.updateFacilityData(facilityDto);
                    }
                }

                List<FootPatrollingSectionsDto> insertFootPatrolingSectionsDtos =  dto.getCreatedFootPatrollingSectionsDto().getFootPatrollingSectionsDtos();
                if(insertFootPatrolingSectionsDtos != null && insertFootPatrolingSectionsDtos.size() > 0){
                    Log.d(TAG, "foot patroling section insert records : " + insertFootPatrolingSectionsDtos.size());
                    for(FootPatrollingSectionsDto sectionsDto : insertFootPatrolingSectionsDtos){
                        NavigationDrawerActivity.mFPDatabase.footPatrollingSectionsDao().insert(sectionsDto);
                    }
                }
                List<FootPatrollingSectionsDto_> updateFootPatrolingSectionsDtos =  dto.getUpdatedFootPatrollingSectionsDto().getFootPatrollingSectionsDtos();
                if(updateFootPatrolingSectionsDtos != null && updateFootPatrolingSectionsDtos.size() > 0){
                    Log.d(TAG, "foot patroling section insert records : " + insertFootPatrolingSectionsDtos.size());
                    for(FootPatrollingSectionsDto_ sectionsDto : updateFootPatrolingSectionsDtos){
                        NavigationDrawerActivity.mDtoWrapper.updateSections(sectionsDto);
                    }
                }


                List<ObservationsCheckListDto> insertChecklistDtos = dto.getCreatedObservationsCheckListDto().getObservationsCheckListDtos();
                if (insertChecklistDtos != null && insertChecklistDtos.size() > 0) {

                    Log.d(TAG, "product insert records : " + insertChecklistDtos.size());

                    for (ObservationsCheckListDto checkListDto : insertChecklistDtos) {
                        //dataUpdateDAO.insertChecklistData(checkListDto, db);
                        NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().insert(checkListDto);
                    }
                }

                List<ObservationCategoriesDto> insertCategoriesDtos = dto.getCreatedObservationCategoriesDto().getObservationCategoriesDtos();
                if (insertCategoriesDtos != null && insertCategoriesDtos.size() > 0) {

                    Log.d(TAG, "categories insert records : " + insertCategoriesDtos.size());

                    for (ObservationCategoriesDto categoriesDto : insertCategoriesDtos) {
                        //dataUpdateDAO.insertChecklistData(checkListDto, db);
                        NavigationDrawerActivity.mFPDatabase.observationCategoriesDtoDao().insert(categoriesDto);
                    }
                }
                HashMap<String, String> serverToAppFootPatrollingInspectionMap = dto.getServerToAppFootPatrollingInspectionMap();
                if(serverToAppFootPatrollingInspectionMap != null) {
                    // serverToAppFootPatrollingInspectionMap.keySet();

                    for ( String key : serverToAppFootPatrollingInspectionMap.keySet() ) {
                        String seqId = serverToAppFootPatrollingInspectionMap.get(key);
                        String timestamp = key.split("_")[0];
                        Inspection inspection = NavigationDrawerActivity.mFPDatabase.inspectionDao().getStartedInspection(timestamp);
                        inspection.setSeqId(seqId);
                        NavigationDrawerActivity.mFPDatabase.inspectionDao().insert(inspection);
                    }

                }

                HashMap<String, String> serverToAppObservationMap = dto.getServerToAppObservationMap();
                if(serverToAppObservationMap != null) {
                    // serverToAppFootPatrollingInspectionMap.keySet();

                    for ( String key : serverToAppObservationMap.keySet() ) {
                        String seqId = serverToAppObservationMap.get(key);
                        String timestamp = key.split("_")[0];
                        Observation observation = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(timestamp);
                        observation.setSeqId(seqId);
                        NavigationDrawerActivity.mFPDatabase.observationDao().insert(observation);
                    }

                }

                HashMap<String, String> serverToAppCompliancesMap = dto.getServerToAppCompliancesMap();
                if(serverToAppCompliancesMap != null) {
                    // serverToAppFootPatrollingInspectionMap.keySet();

                    for ( String key : serverToAppCompliancesMap.keySet() ) {
                        String seqId = serverToAppCompliancesMap.get(key);
                        String timestamp = key.split("_")[0];
                        Compliance compliance = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(timestamp);
                        compliance.setSeqId(seqId);
                        NavigationDrawerActivity.mFPDatabase.complianceDao().insert(compliance);
                    }

                }

                List<UserLoginDto> insertUserLoginDtos = dto.getCreatedResponseUserLoginDto().getUserLoginDtos();

                if (insertUserLoginDtos != null && insertUserLoginDtos.size() > 0) {

                    Log.d(TAG, "User Login Dto insert records : " + insertUserLoginDtos.size());


                    for (UserLoginDto userLoginDto : insertUserLoginDtos) {

                        //dataUpdateDAO.insertUserLoginData(userLoginDto,db);
                        NavigationDrawerActivity.mFPDatabase.userLoginDtoDao().insert(userLoginDto);

                    }

                    for(ObservationsCheckListDto observationsCheckListDto : dto.getCreatedObservationsCheckListDto().getObservationsCheckListDtos()){
                        NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().insert(observationsCheckListDto);
                    }
                }

                List<UserLoginDto_> updateUserLoginDtos = dto.getUpdatedResponseUserLoginDto().getUserLoginDtos();

                if (updateUserLoginDtos != null && updateUserLoginDtos.size() > 0) {

                    Log.d(TAG, "User Login Dto update records : " + updateUserLoginDtos.size());

                    for (UserLoginDto_ userLoginDto : updateUserLoginDtos) {

                        //dataUpdateDAO.updateUserLoginData(userLoginDto, db);
                        //NavigationDrawerActivity.mFPDatabase.userLoginDtoDao().insert(userLoginDto);
                        NavigationDrawerActivity.mDtoWrapper.updateUserLoginData(userLoginDto);
                    }
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

    public int uploadFile(String upLoadServerUri, File sourceFile) {


        String fileName = sourceFile.getName();
        int serverResponseCode = 0;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
      //  File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

//            dialog.dismiss();
//
//            Log.e("uploadFile", "Source File not exist :"
//                    +uploadFilePath + "" + uploadFileName);
//
//            runOnUiThread(new Runnable() {
//                public void run() {
//                    messageText.setText("Source File not exist :"
//                            +uploadFilePath + "" + uploadFileName);
//                }
//            });

            return 0;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;");
               // conn.setRequestProperty("file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name='file';filename='"
                                + fileName + lineEnd+"'");

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

//                    runOnUiThread(new Runnable() {
//                        public void run() {
//
//                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
//                                    +" http://www.androidexample.com/media/uploads/"
//                                    +uploadFileName;
//
//                            messageText.setText(msg);
//                            Toast.makeText(UploadToServer.this, "File Upload Complete.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

              //  dialog.dismiss();
                ex.printStackTrace();

//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        messageText.setText("MalformedURLException Exception : check script url.");
//                        Toast.makeText(UploadToServer.this, "MalformedURLException",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

//                dialog.dismiss();
                e.printStackTrace();

//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        messageText.setText("Got Exception : see logcat ");
//                        Toast.makeText(UploadToServer.this, "Got Exception : see logcat ",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
                Log.e("Upload file to server", "Exception : "
                        + e.getMessage(), e);
            }
           // dialog.dismiss();
            return serverResponseCode;

        } // End else block
    }
    private void sendPics(String url, File file){




            try{
                Bitmap original = BitmapFactory.decodeFile(file.getAbsolutePath());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                original.compress(Bitmap.CompressFormat.JPEG, 100, out);
                byte[] data = out.toByteArray();

                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Cache-Control", "no-cache");
                connection.setRequestProperty("Content-Type", "multipart/form-data");

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(data);
                dataOutputStream.flush();
                dataOutputStream.close();
                int c = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();
                Log.i("sendpics", "HTTP Response is : "
                        + serverResponseMessage + ": " + c);
                if(c == 200){

                }
               // connection.disconnect();

            }catch(Exception e){
                Log.e("cedError",e.getMessage());
            }


    }


}
