package com.school.foot_patrolling.observations;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;
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

public class EditObservationFragment extends BaseFragment{
    ScheduleListAdapter scheduleListAdapter;
    Observation observationModel;
    String selectedStatusType;
    @BindView(R.id.statusTV)
    TextView statusTV;
    @BindView(R.id.dateTV)
    TextView dateTv;
    @BindView(R.id.tvChecklistItem)
    TextView tvCheckListItem;
    @BindView(R.id.tvObservation)
    TextView tvObservation;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @OnClick(R.id.btn_submit)
    public void clickOnSubmit() {

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
        View view = inflater.inflate(R.layout.fragment_add_compliance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        // preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        String deviceSequenceId = getArguments().getString(BUNDLE_KEY_SELECTED_COMPLIANCE);
        observationModel = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(deviceSequenceId);
//        tvCheckListItem.setText(observationModel.getObservationItem());
  //      tvObservation.setText(observationModel.getObservation());
    //    tvLocation.setText(observationModel.getLocation());
        // tvDepot.setText(model.get);

        return view;
    }
}
