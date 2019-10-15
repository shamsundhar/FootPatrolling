package com.school.foot_patroling;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.school.foot_patroling.patrolinglist.PatrolingListFragment;
import com.school.foot_patroling.utils.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    PreferenceHelper preferenceHelper;
    @BindView(R.id.gridview)
    GridView gridView;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        fragmentComponent().inject(this);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();

        HomeGridViewAdapter gridViewAdapter = new HomeGridViewAdapter(getActivity());
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        ((NavigationDrawerActivity) getActivity()).displayCheckedListFragment();
                        break;
                    case 1 :
                        ((NavigationDrawerActivity) getActivity()).displayObservationsFragment();
                        break;
                    case 2 :
                        ((NavigationDrawerActivity) getActivity()).displayDataSyncFragment();
                        break;
                    case 3 :
                        ((NavigationDrawerActivity) getActivity()).displayComplianceFragment();
                        break;
                    case 4 :
                        ((NavigationDrawerActivity) getActivity()).displayReportsFragment();
                        break;

                }
            }
        });

        return view;
    }
}
