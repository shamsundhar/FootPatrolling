package com.school.foot_patrolling.observations;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.school.foot_patroling.AWFActivity;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.CameraActivity;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ComplianceListAdapter;
import com.school.foot_patroling.register.model.Observation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_COMPLIANCE;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_EDIT_OBSERVATION;

public class ObservationsFragment extends BaseFragment {
    @BindView(R.id.observationsRecyclerview)
    RecyclerView observationsRecyclerview;
    List<Observation> observationsList;
    @BindView(R.id.empty_view)
    TextView empty_view;
    ObservationsListAdapter observationsListAdapter;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ComplianceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ObservationsFragment newInstance() {
        ObservationsFragment fragment = new ObservationsFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_observations, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        // preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        observationsListAdapter = new ObservationsListAdapter();
        observationsListAdapter.setClickListener(new ClickListener() {

            @Override
            public void onItemClick(Object model, int position) {
                Intent in = new Intent(getActivity(), AWFActivity.class);
                in.putExtra(BUNDLE_KEY_DISPLAY_FRAGMENT, BUNDLE_VALUE_EDIT_OBSERVATION);
                in.putExtra(BUNDLE_KEY_SELECTED_OBSERVATION, ((Observation)model).getDeviceSeqId());
                getActivity().startActivity(in);

            }
        });
        observationsRecyclerview.setAdapter(observationsListAdapter);
        observationsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayObservationsFromDB();
        return view;
    }

    private void displayObservationsFromDB(){

        try {
            //          if (database != null) {
            Log.d(TAG, "fetching user id");
            String sql = "select priority, description from observations_check_list";
            observationsList = new ArrayList<>();
            observationsList.addAll(NavigationDrawerActivity.mFPDatabase.observationDao().getAllObservationDtos());
            //        }
        }catch(Exception e){

        }

        if(observationsList != null && !observationsList.isEmpty()){
            empty_view.setVisibility(View.GONE);
            observationsRecyclerview.setVisibility(View.VISIBLE);

        }else{
            empty_view.setText("Observation list is empty");
            empty_view.setVisibility(View.VISIBLE);
            observationsRecyclerview.setVisibility(View.GONE);
        }
        observationsListAdapter.setItems(observationsList);
        observationsListAdapter.notifyDataSetChanged();
    }
}
