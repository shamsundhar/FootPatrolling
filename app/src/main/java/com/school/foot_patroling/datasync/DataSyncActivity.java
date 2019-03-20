package com.school.foot_patroling.datasync;

import android.os.Bundle;

import com.school.foot_patroling.BaseActivity;
import com.school.foot_patroling.R;

import butterknife.ButterKnife;

public class DataSyncActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_data_sync);
        ButterKnife.bind(this);
    }
}
