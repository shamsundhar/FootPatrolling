package com.school.foot_patroling.localdbstatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.login.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalDBStatusFragment extends BaseFragment {
    @BindView(R.id.tvCompliance)
    TextView tvCompliance;
    @BindView(R.id.tvObservationCategories)
    TextView tvObservationCategories;
    @BindView(R.id.tvInspection)
    TextView tvInspection;
    @BindView(R.id.tvObservation)
    TextView tvObservation;
    @BindView(R.id.tvfacility)
    TextView tvFacility;
    @BindView(R.id.tvFootPatrolingSections)
    TextView tvFootPatrollingSections;
    @BindView(R.id.tvProduct)
    TextView tvProduct;
    @BindView(R.id.tvObservationCheckList)
    TextView tvObservationChecklist;
    @BindView(R.id.tvTracking)
    TextView tvTracking;
    @BindView(R.id.tvOheLocation)
    TextView tvOheLocation;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocalDBStatusFragment newInstance() {
        LocalDBStatusFragment fragment = new LocalDBStatusFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_db_status, container, false);
        ButterKnife.bind(this, view);

        tvCompliance.setText("Compliance : "+NavigationDrawerActivity.mFPDatabase.complianceDao().getCount());
        tvFacility.setText("Facility : "+NavigationDrawerActivity.mFPDatabase.facilityDtoDao().getCount());
        tvFootPatrollingSections.setText("FP Sections : "+NavigationDrawerActivity.mFPDatabase.footPatrollingSectionsDao().getCount());
        tvInspection.setText("Inspection : "+NavigationDrawerActivity.mFPDatabase.inspectionDao().getCount());
        tvObservationCategories.setText("Observation Categories : "+NavigationDrawerActivity.mFPDatabase.observationCategoriesDtoDao().getCount());
        tvObservationChecklist.setText("CheckList : "+NavigationDrawerActivity.mFPDatabase.observationsCheckListDtoDao().getCount());
        tvProduct.setText("Product : "+NavigationDrawerActivity.mFPDatabase.productDtoDao().getCount());
        tvObservation.setText("Observation : "+NavigationDrawerActivity.mFPDatabase.observationDao().getCount());
        tvTracking.setText("Fp Movement : "+NavigationDrawerActivity.mFPDatabase.movementDao().getCount());
        tvOheLocation.setText("Ohe Location : "+NavigationDrawerActivity.mFPDatabase.oheLocationDtoDao().getCount());

        return view;
    }
}
