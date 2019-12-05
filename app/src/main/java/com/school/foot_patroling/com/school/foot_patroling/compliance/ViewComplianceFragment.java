package com.school.foot_patroling.com.school.foot_patroling.compliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.utils.DateTimeUtils;
import com.school.foot_patroling.utils.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT5;
import static com.school.foot_patroling.utils.Constants.DATE_FORMAT6;

public class ViewComplianceFragment extends BaseFragment {
    PreferenceHelper preferenceHelper;
    private int currentCounter;
    Observation observationModel;
    Compliance complianceModel;
    private String selectedDate;
    String selectedStatusType;
    @BindView(R.id.tvComplianceProvided)
    TextView complianceProvidedStatus;
    @BindView(R.id.et_actionBy)
    EditText actionBy;
    @BindView(R.id.et_actionDone)
    EditText actionDone;
    @BindView(R.id.statusTV)
    TextView statusTV;
    @BindView(R.id.tvAttachmentsCounter)
    TextView attachmentsCounter;
    @BindView(R.id.dateTV)
    TextView dateTv;
    @BindView(R.id.syncDoneImage)
    ImageView syncDoneImage;
    @BindView(R.id.tvChecklistItem)
    TextView tvCheckListItem;
    @BindView(R.id.tvObservation)
    TextView tvObservation;
    @OnClick(R.id.btn_ok)
    public void clickOkButton(){
        Intent resultIntent = new Intent();
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        getActivity().finish();
    }
    @BindView(R.id.tvLocation)
    TextView tvLocation;

    public static ViewComplianceFragment newInstance() {
        ViewComplianceFragment fragment = new ViewComplianceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_compliance, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        String deviceSequenceId = getArguments().getString(BUNDLE_KEY_SELECTED_COMPLIANCE);
        observationModel = NavigationDrawerActivity.mFPDatabase.observationDao().getStartedObservation(deviceSequenceId);
        tvCheckListItem.setText(observationModel.getObservationItem());
        tvObservation.setText(observationModel.getObservation());
        tvLocation.setText(observationModel.getLocation());
        complianceModel = NavigationDrawerActivity.mFPDatabase.complianceDao().getStartedCompliance(deviceSequenceId);
        initCounter();
        if(complianceModel != null){
            complianceProvidedStatus.setVisibility(View.VISIBLE);
            complianceProvidedStatus.setText("Compliance already provided");
            if(!complianceModel.getSeqId().equals("null")){
                actionBy.setText(complianceModel.getComplianceBy());
                actionDone.setText(complianceModel.getAction());
                statusTV.setText(complianceModel.getStatus());
                selectedStatusType = complianceModel.getStatus();
                String providedDate = complianceModel.getCompliedDateTime();
                String strDate = DateTimeUtils.parseDateTime(providedDate, DATE_FORMAT6, DATE_FORMAT5);
                selectedDate = providedDate;
                dateTv.setText(strDate);
                syncDoneImage.setVisibility(View.VISIBLE);
            }
        }
        attachmentsCounter.setText("Attachments : "+currentCounter);
        return view;
    }
    private void initCounter(){
        String deviceSequenceId = observationModel.getDeviceSeqId();
        currentCounter = preferenceHelper.getInteger(getActivity(), "Ccounter_"+deviceSequenceId,0);
    }
}
