package com.school.foot_patroling.datasync;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.school.foot_patroling.BaseFragment;
import com.school.foot_patroling.R;
import com.school.foot_patroling.localdbstatus.LocalDBStatusFragment;

import butterknife.ButterKnife;

public class DataSyncFragment extends BaseFragment {
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

        return view;
    }
}
