package com.school.foot_patroling.datasync;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.localdbstatus.LocalDBStatusFragment;
import com.school.foot_patroling.register.RegisterActivity;
import com.school.foot_patroling.register.RegisterApi;
import com.school.foot_patroling.register.model.AppToServerCreatedFootPatrollingInspectionDto;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.utils.Common;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import net.sqlcipher.database.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_AUTH;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI1;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_IMEI2;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_LAST_SYNC_DATE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_URL;

public class DataSyncFragment extends BaseFragment {
    @Inject
    RegisterApi registerApi;
    PreferenceHelper preferenceHelper;
    @OnClick(R.id.btn_syncNow)
    public void clickSyncNow() {

        if (Common.isNetworkAvailable(getActivity())) {
            //  String url = etUrl.getText().toString().trim();

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
            url = url + "/warehouse/fpApp/get-fp-data";
            RegistrationRequestModel model = new RegistrationRequestModel();
            model.setAppName("TRD_FP");
            model.setCurrentTimestamp(DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S"));
            model.setImeiNumber(selectedImei);
            model.setPreviousTimestamp(lastSyncDate);
            List<Inspection> inspectionDtoList = NavigationDrawerActivity.mFPDatabase.inspectionDao().getNotSyncedInspection();
            AppToServerCreatedFootPatrollingInspectionDto appToServerCreatedFootPatrollingInspectionDto = new AppToServerCreatedFootPatrollingInspectionDto();
            appToServerCreatedFootPatrollingInspectionDto.setCount("1");
            appToServerCreatedFootPatrollingInspectionDto.setFootPatrollingInspectionDtos(inspectionDtoList);

            model.setAppToServerCreatedFootPatrollingInspectionDto(appToServerCreatedFootPatrollingInspectionDto);
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
                                //   preferenceHelper.setString(getActivity(), BUNDLE_KEY_REG_ID, masterDto.getRegistrationId());
//                                        preferenceHelper.setBoolean(getActivity(), BUNDLE_KEY_AUTH, masterDto.getImeiAuth());
//                                        try {
//                                            dbhelper = DatabaseHelper.getInstance(getActivity());
//                                            dbhelper.deleteDatabase();
//                                            dbhelper.createDataBase();
//
//                                            DatabaseHelper dbhelper = DatabaseHelper.getInstance(getActivity());
//                                            SQLiteDatabase db = dbhelper.getDBObject(0);
//                                            String currentTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(Calendar.getInstance().getTime());
//                                            preferenceHelper.setString(getActivity(), BUNDLE_KEY_LAST_SYNC_DATE, currentTime);
//                                            String result = syncMasterData(db, masterDto);
//
//                                        } catch (Exception e){
//
//                                         //   Log.e(TAG, "creating database - "+ e.getMessage());
//                                        }
                                // startActivity(new Intent(getActivity(), NavigationDrawerActivity.class));
                                // finish();
                            }
                        }
                    });
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

        return view;
    }
}
