package com.school.foot_patroling.com.school.foot_patroling.compliance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.depotselection.ScheduleListAdapter;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.utils.DatePickerFragment;
import com.school.foot_patroling.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT1;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT2;

public class AddComplianceFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{
    ScheduleListAdapter scheduleListAdapter;
    Observation observationModel;
    Compliance complianceModel;
    String selectedStatusType;
    @BindView(R.id.tvComplianceProvided)
    TextView complianceProvidedStatus;
    @BindView(R.id.statusTV)
    TextView statusTV;
    @OnClick(R.id.statusLayout)
    public void clickOnStatus(){
        displayStatusPopup();
    }
    @BindView(R.id.cameraLayout)
    LinearLayout cameraLayout;
    @BindView(R.id.dateTV)
    TextView dateTv;
    @BindView(R.id.tvChecklistItem)
    TextView tvCheckListItem;
    @BindView(R.id.tvObservation)
    TextView tvObservation;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @OnClick(R.id.calendar)
    public void clickOnCalendar(){
        displayDateDialog();
    }
    @OnClick(R.id.btn_submit)
    public void clickOnSubmit(){
        observationModel.setActionBy("");
        observationModel.setAction("");
        Compliance compliance = new Compliance();
        compliance.setAction(observationModel.getAction());
        compliance.setComplianceBy("");
        compliance.setCreatedStamp(observationModel.getCreatedStamp());
        compliance.setDescription(observationModel.getDescription());
        compliance.setDeviceId(observationModel.getDeviceId());
        compliance.setDeviceSeqId(observationModel.getDeviceSeqId());
        compliance.setObeservationSeqId(observationModel.getDeviceSeqId());
        compliance.setStatus(selectedStatusType);
        compliance.setSeqId("null");
        compliance.setCompliedDateTime("");
        NavigationDrawerActivity.mFPDatabase.complianceDao().insert(compliance);
        Toast.makeText(getActivity(), "Compliance saved successfully", Toast.LENGTH_SHORT).show();
        //List<Compliance> list = NavigationDrawerActivity.mFPDatabase.complianceDao().getAllCompliancesDtos();
        complianceModel = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(observationModel.getDeviceSeqId());
        if(complianceModel != null){
            cameraLayout.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setText("Compliance already provided");
        }else{
            cameraLayout.setVisibility(View.GONE);
            complianceProvidedStatus.setVisibility(View.GONE);
        }
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ComplianceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddComplianceFragment newInstance() {
        AddComplianceFragment fragment = new AddComplianceFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_compliance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        // preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        String deviceSequenceId = getArguments().getString(BUNDLE_KEY_SELECTED_COMPLIANCE);
        observationModel = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(deviceSequenceId);
        tvCheckListItem.setText(observationModel.getObservationItem());
        tvObservation.setText(observationModel.getObservation());
        tvLocation.setText(observationModel.getLocation());

        complianceModel = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(deviceSequenceId);
        if(complianceModel != null){
            cameraLayout.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setText("Compliance already provided");
        }else{
            cameraLayout.setVisibility(View.GONE);
            complianceProvidedStatus.setVisibility(View.GONE);
        }

        return view;
    }
    private void displayDateDialog(){
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        args.putLong("maxdate", calender.getTimeInMillis());
        date.setListener(AddComplianceFragment.this);
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onDateSet(DatePicker view, int i, int i1, int i2) {
        String strDate = padding(i1+1)+"-"+padding(i2)+"-"+padding(i);
        strDate = DateTimeUtils.parseDateTime(strDate, DATE_FORMAT2, DATE_FORMAT1);
        dateTv.setText(strDate);

    }
    String padding(int value)
    {
        String str = value+"";
        if(value < 10)
            str = "0"+str;
        return str;
    }
    private void displayStatusPopup()
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
        final List<String> statusList = new ArrayList<String>();
        statusList.add("Open");
        statusList.add("Pending");
        statusList.add("InProgress");
        statusList.add("Completed");
        if(statusList != null) {
            scheduleListAdapter = new ScheduleListAdapter(statusList, getActivity().getBaseContext());
            listView.setAdapter(scheduleListAdapter);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                builder.dismiss();
                String dataModel = statusList.get(position);
                statusTV.setText(dataModel);
                selectedStatusType = dataModel;
            }
        });
        builder.setCanceledOnTouchOutside(true);
        builder.show();
    }
}
