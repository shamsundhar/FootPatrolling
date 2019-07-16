package com.school.foot_patroling.datasync;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_CURRENT_SYNC_TIME;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI1;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI2;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;
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
    @BindView(R.id.tvDataSyncResult)
    TextView tvResult;
    @BindView(R.id.tvDataSyncStatus)
    TextView tvSyncStatus;
    @OnClick(R.id.btn_syncNow)
    public void clickSyncNow() {
        Boolean isInspectionInProgress = preferenceHelper.getBoolean(getActivity(), PREF_KEY_FP_STARTED, Boolean.FALSE);
        if(!isInspectionInProgress) {
            if (Common.isNetworkAvailable(getActivity())) {
                //  String url = etUrl.getText().toString().trim();
                String syncStartTime = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
                tvSyncStartTime.setText("Start Time : " + syncStartTime);
                tvSyncStatus.setText("Data Sync Status : Started");
                final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
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
                model.setImeiNumber("867520040587478");
                //TODO model.setImeiNumber(selectedImei);
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
                uploadImages();
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
        String url = preferenceHelper.getString(getActivity(), BUNDLE_KEY_URL, "");
        url = url + Constants.REST_POST_FILE_UPLOAD;
        Map<String, byte[]> imagesMap = new HashMap<String, byte[]>();

        final File directory = new File(Environment.getExternalStorageDirectory() + FP_PICS_FOLDER);

        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++)
        {
            Log.d("Files", "FileName:" + files[i].getName());
            int size = (int) files[i].length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(files[i]));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            imagesMap.put(files[i].getName(),bytes);
        }

        JSONObject jsonObject = new JSONObject(imagesMap);

        registerApi.fileUpload(url,jsonObject.toString())
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())

                   .subscribe(new Observer<Object>() {
                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(Object o) {
                            //TODO delete files
                           directory.delete();
                       }

                       @Override
                       public void onError(Throwable e) {

                       }

                       @Override
                       public void onComplete() {

                       }
                   });



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
        //uploadImages();
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
                        RegisterActivity.mFPDatabase.facilityDtoDao().insert(facilityDto);

                    }

                }

                List<FacilityDto_> updateFacilityDtos = dto.getUpdatedResponseFacilityDto().getFacilityDtos();

                if (updateFacilityDtos != null && updateFacilityDtos.size() > 0) {
                    Log.d(TAG, "facility update records : " + updateFacilityDtos.size());

                    for (FacilityDto_ facilityDto : updateFacilityDtos) {

                        //dataUpdateDAO.updateFacilityData(facilityDto, db);
                        RegisterActivity.mDtoWrapper.updateFacilityData(facilityDto);
                    }
                }

                List<FootPatrollingSectionsDto> insertFootPatrolingSectionsDtos =  dto.getCreatedFootPatrollingSectionsDto().getFootPatrollingSectionsDtos();
                if(insertFootPatrolingSectionsDtos != null && insertFootPatrolingSectionsDtos.size() > 0){
                    Log.d(TAG, "foot patroling section insert records : " + insertFootPatrolingSectionsDtos.size());
                    for(FootPatrollingSectionsDto sectionsDto : insertFootPatrolingSectionsDtos){
                        RegisterActivity.mFPDatabase.footPatrollingSectionsDao().insert(sectionsDto);
                    }
                }
                List<FootPatrollingSectionsDto_> updateFootPatrolingSectionsDtos =  dto.getUpdatedFootPatrollingSectionsDto().getFootPatrollingSectionsDtos();
                if(updateFootPatrolingSectionsDtos != null && updateFootPatrolingSectionsDtos.size() > 0){
                    Log.d(TAG, "foot patroling section insert records : " + insertFootPatrolingSectionsDtos.size());
                    for(FootPatrollingSectionsDto_ sectionsDto : updateFootPatrolingSectionsDtos){
                        RegisterActivity.mDtoWrapper.updateSections(sectionsDto);
                    }
                }


                List<ObservationsCheckListDto> insertChecklistDtos = dto.getCreatedObservationsCheckListDto().getObservationsCheckListDtos();
                if (insertChecklistDtos != null && insertChecklistDtos.size() > 0) {

                    Log.d(TAG, "product insert records : " + insertChecklistDtos.size());

                    for (ObservationsCheckListDto checkListDto : insertChecklistDtos) {
                        //dataUpdateDAO.insertChecklistData(checkListDto, db);
                        RegisterActivity.mFPDatabase.observationsCheckListDtoDao().insert(checkListDto);
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
}
