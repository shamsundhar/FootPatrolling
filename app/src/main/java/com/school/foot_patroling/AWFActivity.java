package com.school.foot_patroling;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;

import butterknife.ButterKnife;

import static com.school.foot_patroling.utils.Constants.BUNDLE_KEY_DISPLAY_FRAGMENT;
import static com.school.foot_patroling.utils.Constants.BUNDLE_VALUE_COMPLIANCE;

/*
 * AWF - Activity With Fragment. this activity will act as holder activity for fragment - which holds only one fragment.
 *
 * */
public class AWFActivity extends BaseActivity {

    private static final String ADD_COMPLIANCE_FRAGMENT_TAG = "ADD_COMPLIANCE_FRAGMENT";
    private static final String COMPOSE_MESSAGE_FRAGMENT_TAG = "COMPOSE_MESSAGE_FRAGMENT";
    private static final String MESSAGE_DETAILS_FRAGMENT_TAG = "MESSAGE_DETAILS_FRAGMENT";
    private static final String EVENTS_FRAGMENT_TAG = "EVENST_FRAGMENT";
    private static final String ADD_NOTES_FRAGMENT_TAG = "ADD_NOTES_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String displayFragment = bundle.getString(BUNDLE_KEY_DISPLAY_FRAGMENT);
            switch (displayFragment){
                case BUNDLE_VALUE_COMPLIANCE :
                    displayAddComplianceFragment(bundle);
                    break;
            }
        }

    }
    public void displayAddComplianceFragment(Bundle bundle){
        setTitle(null);

        AddComplianceFragment fragment = AddComplianceFragment.newInstance();
        if(bundle != null){
            fragment.setArguments(bundle);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  fragment, ADD_COMPLIANCE_FRAGMENT_TAG)
                .commit();
    }
//    public void displayComposeMessageFragment(Bundle bundle){
//        setTitle(null);
//
//        NewMessageFragment nMF = NewMessageFragment.newInstance();
//        if(bundle != null){
//            nMF.setArguments(bundle);
//        }
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container,  nMF, COMPOSE_MESSAGE_FRAGMENT_TAG)
//                .commit();
//    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){

        // set toolbar logo to center programmatically
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        int offset = (toolbar.getWidth() / 2) - (logo.getWidth() / 2);
        // set
        logo.setX(offset);

    }
}
