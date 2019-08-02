package com.school.foot_patroling.depotselection;

import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.database.DatabaseHelper;
import com.school.foot_patroling.login.LoginFragment;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.utils.CustomAlertDialog;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_IMEI;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED_TIME;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_DEPOT;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_SECTION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_USER;

public class DepotSelectionFragment extends BaseFragment {
    PreferenceHelper preferenceHelper;
    @BindView(R.id.depotLayout)
    RelativeLayout depotLayout;
    @BindView(R.id.sectionLayout)
    RelativeLayout sectionLayout;
    @BindView(R.id.scheduleLayout)
    RelativeLayout scheduleLayout;
    @BindView(R.id.depotTV)
    TextView depotTV;
    @BindView(R.id.sectionTV)
    TextView sectionTV;
    @BindView(R.id.scheduleTV)
    TextView scheduleTV;

    DepotsListAdapter depotsListAdapter;
    SectionsListAdapter sectionsListAdapter;
    ScheduleListAdapter scheduleListAdapter;
    String selectedDepotId;
    String selectedDepotName;
    String selectedSectionName;
    String selectedSectionID;
    String selectedScheduleType;
    @OnClick(R.id.depotLayout)
    public void depotClick(){
        displayDepotPopup();
    }
    @OnClick(R.id.sectionLayout)
    public void sectionClick(){
        displaySectionsPopup();
    }
    @OnClick(R.id.scheduleLayout)
    public void scheduleClick(){
        displaySchedulePopup();
    }
    @OnClick(R.id.btn_startFP)
    public void startFPClick(){
        if(validate()) {
            String fpStartTime = DateTimeUtils.getCurrentDate("dd-MM-yyyy HH:mm:ss.S");
            preferenceHelper.setBoolean(getActivity(), PREF_KEY_FP_STARTED, Boolean.TRUE);
            preferenceHelper.setString(getActivity(), PREF_KEY_SELECTED_DEPOT, selectedDepotName);
            preferenceHelper.setString(getActivity(), PREF_KEY_SELECTED_SECTION, selectedSectionName);
            preferenceHelper.setString(getActivity(), PREF_KEY_FP_STARTED_TIME, fpStartTime);

            Inspection inspection = new Inspection();
            String selectedImei = preferenceHelper.getString(getActivity(), BUNDLE_KEY_SELECTED_IMEI, "");
            String loggedInUser = preferenceHelper.getString(getActivity(), PREF_KEY_SELECTED_USER, "");
            inspection.setDeviceId(selectedImei);
            //   inspection.setDevice_seq_id();
            inspection.setFacilityId(selectedDepotId);
            inspection.setInspectionBy(loggedInUser);
            inspection.setInspectionType(selectedScheduleType);
            inspection.setSection(selectedSectionID);
            inspection.setSeqId("null");
            inspection.setDeviceSeqId(fpStartTime);
            inspection.setStartTime(fpStartTime);
            NavigationDrawerActivity.mFPDatabase.inspectionDao().insert(inspection);

            ((NavigationDrawerActivity) getActivity()).displayCheckedListFragment();
        }
    }
    public static DepotSelectionFragment newInstance() {
        DepotSelectionFragment fragment = new DepotSelectionFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depot_selection, container, false);
        ButterKnife.bind(this, view);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        return view;
    }
    private void displayDepotPopup()
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
        final List<FacilityDto> depotList = NavigationDrawerActivity.mFPDatabase.facilityDtoDao().getOHEFacilityDtos();
        if(depotList != null) {
            depotsListAdapter = new DepotsListAdapter(depotList, getActivity().getBaseContext());
            listView.setAdapter(depotsListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                FacilityDto dataModel = depotList.get(position);
                depotTV.setText(dataModel.getFacilityName());
                selectedDepotName = dataModel.getFacilityName();
                selectedDepotId = dataModel.getFacilityId();
                Snackbar.make(view, " " +dataModel.getFacilityName()+" "+dataModel.getFacilityId(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                //   displaySectionsPopup();

            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private void displaySectionsPopup()
    {
        if(selectedDepotId != null) {
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
            final List<FootPatrollingSectionsDto> sectionList = NavigationDrawerActivity.mFPDatabase.footPatrollingSectionsDao().getAllFootPatrollingSectionDtosByDepot(selectedDepotId);

            if (sectionList != null && sectionList.size() > 0) {
                sectionsListAdapter = new SectionsListAdapter(sectionList, getActivity().getBaseContext());
                listView.setAdapter(sectionsListAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        builder.dismiss();
                        FootPatrollingSectionsDto dataModel = sectionList.get(position);
                        sectionTV.setText(dataModel.getFpSection());
                        selectedSectionName = dataModel.getFpSection();
                        selectedSectionID = dataModel.getFpSection();
                        Snackbar.make(view, " " + dataModel.getFpSection() + " " + dataModel.getSeqId(), Snackbar.LENGTH_LONG)
                                .setAction("No action", null).show();

                    }
                });
                builder.setCanceledOnTouchOutside(true);
                builder.show();
            }
            else{
                CustomAlertDialog dialog = new CustomAlertDialog();
                dialog.showAlert1(getActivity(), R.string.text_alert, "No sections available for selected depot");
            }

        }
        else{
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Depot");
        }
    }
    private void displaySchedulePopup()
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
        final List<String> scheduleList = new ArrayList<String>();
        scheduleList.add("Scheduled");
        scheduleList.add("UnScheduled");
        if(scheduleList != null) {
            scheduleListAdapter = new ScheduleListAdapter(scheduleList, getActivity().getBaseContext());
            listView.setAdapter(scheduleListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                String dataModel = scheduleList.get(position);
                scheduleTV.setText(dataModel);
                selectedScheduleType = dataModel;
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
    private boolean validate(){
        boolean validate = true;
        if(selectedDepotId == null || selectedDepotId.isEmpty()){
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Depot");
            validate = false;
        }
        else if(selectedSectionID == null || selectedSectionID.isEmpty()){
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Section");
            validate = false;
        }
        else if(selectedScheduleType == null || selectedScheduleType.isEmpty()){
            CustomAlertDialog dialog = new CustomAlertDialog();
            dialog.showAlert1(getActivity(), R.string.text_alert, "Select Schedule Type");
            validate = false;
        }

        return validate;
    }

}
