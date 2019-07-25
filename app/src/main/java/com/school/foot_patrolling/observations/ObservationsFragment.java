package com.school.foot_patrolling.observations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.school.foot_patroling.AWFActivity;
import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.R;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ClickListener;
import com.school.foot_patroling.register.model.Observation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_SELECTED_OBSERVATION;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_EDIT_OBSERVATION;

public class ObservationsFragment extends BaseFragment {
    @BindView(R.id.observationsRecyclerview)
    RecyclerView observationsRecyclerview;
    List<Observation> observationsList;
    @BindView(R.id.empty_view)
    TextView empty_view;
    ObservationsListAdapter observationsListAdapter;
    public static int EDIT_OBSERVATION_CODE = 200;
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
                startActivityForResult(in, EDIT_OBSERVATION_CODE);

            }
        });
        observationsRecyclerview.setAdapter(observationsListAdapter);
        observationsRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayObservationsFromDB();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_OBSERVATION_CODE){
           if(resultCode == RESULT_OK){
             //  Toast.makeText(getActivity(), "from observation edit", Toast.LENGTH_SHORT).show();
               displayObservationsFromDB();
           }
        }
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
