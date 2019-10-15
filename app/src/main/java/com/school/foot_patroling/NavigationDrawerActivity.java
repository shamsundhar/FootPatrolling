package com.school.foot_patroling;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.school.foot_patroling.com.school.foot_patroling.compliance.ComplianceFragment;
import com.school.foot_patroling.database.DtoWrapper;
import com.school.foot_patroling.database.FPDatabase;
import com.school.foot_patroling.datasync.DataSyncFragment;
import com.school.foot_patroling.depotselection.DepotSelectionFragment;
import com.school.foot_patroling.localdbstatus.LocalDBStatusFragment;
import com.school.foot_patroling.login.LoginFragment;
import com.school.foot_patroling.patrolinglist.PatrolingListFragment;
import com.school.foot_patroling.reload.ReloadFragment;
import com.school.foot_patroling.reports.ReportsFragment;
import com.school.foot_patroling.terms.TermsFragment;
import com.school.foot_patroling.utils.PreferenceHelper;
import com.school.foot_patrolling.observations.ObservationsFragment;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.school.foot_patroling.utils.Constants.FOOTPATROLLING_DATABASE;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_FP_STARTED;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_DEPOT;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_SECTION;
import static com.school.foot_patroling.utils.Constants.PREF_KEY_SELECTED_USER;

public class NavigationDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PreferenceHelper preferenceHelper;
    Menu nav_Menu;
    @BindView(R.id.toolbarDetails)
    TextView toolbarDetails;

    public boolean isDISPLAY_LOGIN() {
        return DISPLAY_LOGIN;
    }

    public void setDISPLAY_LOGIN(boolean DISPLAY_LOGIN) {
        this.DISPLAY_LOGIN = DISPLAY_LOGIN;
    }

    public boolean DISPLAY_LOGIN;

    String market_uri = "https://play.google.com/store/apps/details?id=";
    private static final String LOGIN_FRAGMENT_TAG = "LOGIN_FRAGMENT";
    private static final String CHECKEDLIST_FRAGMENT_TAG = "CHECKEDLIST_FRAGMENT";
    private static final String HOME_FRAGMENT_TAG = "HOME_FRAGMENT";
    private static final String DEPOT_SELECTION_FRAGMENT_TAG = "DEPO_SELECTION_FRAGMENT";
    private static final String COMPLIANCE_FRAGMENT_TAG = "COMPLIANCE_FRAGMENT";
    private static final String OBSERVATIONS_FRAGMENT_TAG = "OBSERVATIONS_FRAGMENT";
    private static final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT";
    private static final String RECOMENDATION_FRAGMENT_TAG = "RECOMENDATION_FRAGMENT";
    private static final String MESSAGES_FRAGMENT_TAG = "MESSAGES_FRAGMENT";
    public static FPDatabase mFPDatabase;
    public static DtoWrapper mDtoWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferenceHelper = PreferenceHelper.getPrefernceHelperInstace();
        mFPDatabase = Room.databaseBuilder(this,FPDatabase.class, FOOTPATROLLING_DATABASE).allowMainThreadQueries().build();
       // ActionBar actionBar = getSupportActionBar();
        mDtoWrapper = new DtoWrapper(mFPDatabase);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        nav_Menu = navigationView.getMenu();


        displayLoginFragment();
    }

    private void setTitle(String title){
        //getSupportActionBar().setTitle(title);
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getCurrentFragment() instanceof HomeFragment ||
                getCurrentFragment() instanceof LoginFragment ||
                getCurrentFragment() instanceof DepotSelectionFragment){
            displayExitDialog();
        }else{
            displayHomeFragment();
        }
    }
   public Fragment getCurrentFragment()
    {
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.container);
        return currentFragment;
    }
public void displayExitDialog(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    NavigationDrawerActivity.this.finish();
                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
    AlertDialog alert = builder.create();
    alert.show();
}
    public void displayLoginFragment(){
        setTitle("Login");
        displayHeaderDetails();
        nav_Menu.findItem(R.id.nav_login).setVisible(true);
        nav_Menu.findItem(R.id.nav_home).setVisible(false);
        nav_Menu.findItem(R.id.nav_checklist).setVisible(false);
        nav_Menu.findItem(R.id.nav_compliance).setVisible(false);
        nav_Menu.findItem(R.id.nav_observations).setVisible(false);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  LoginFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                .commit();
    }
    public void displayHomeFragment(){
        setTitle("Home");
        displayHeaderDetails();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
        nav_Menu.findItem(R.id.nav_home).setVisible(true);
        nav_Menu.findItem(R.id.nav_checklist).setVisible(true);
        nav_Menu.findItem(R.id.nav_observations).setVisible(true);
        nav_Menu.findItem(R.id.nav_compliance).setVisible(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  HomeFragment.newInstance(), HOME_FRAGMENT_TAG)
                .commit();

    }
    public void displayCheckedListFragment(){
        setTitle("Check List");
        displayHeaderDetails();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
        nav_Menu.findItem(R.id.nav_home).setVisible(true);
        nav_Menu.findItem(R.id.nav_checklist).setVisible(true);
        nav_Menu.findItem(R.id.nav_observations).setVisible(true);
        nav_Menu.findItem(R.id.nav_compliance).setVisible(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  PatrolingListFragment.newInstance(), CHECKEDLIST_FRAGMENT_TAG)
                .commit();
    }
    public void displayDepotSelectionFragment(){
        setTitle("FP Inspection");
        displayHeaderDetails();
        nav_Menu.findItem(R.id.nav_checklist).setVisible(true);
        nav_Menu.findItem(R.id.nav_observations).setVisible(true);
        nav_Menu.findItem(R.id.nav_compliance).setVisible(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  DepotSelectionFragment.newInstance(), DEPOT_SELECTION_FRAGMENT_TAG)
                .commit();
    }
    public void displayComplianceFragment(){
        setTitle("Compliance");
        displayHeaderDetails();
        //  nav_Menu.findItem(R.id.nav_login).setVisible(false);
        //   nav_Menu.findItem(R.id.nav_checklist).setVisible(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  ComplianceFragment.newInstance(), COMPLIANCE_FRAGMENT_TAG)
                .commit();
    }
    public void displayReportsFragment(){
        setTitle("Reports");
        displayHeaderDetails();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  ReportsFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                .commit();
    }
    public void displayDataSyncFragment(){
        setTitle("Data Sync");
        displayHeaderDetails();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  DataSyncFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                .commit();
    }
    public void displayObservationsFragment(){
        setTitle("Observations");
        displayHeaderDetails();
        //  nav_Menu.findItem(R.id.nav_login).setVisible(false);
        //   nav_Menu.findItem(R.id.nav_checklist).setVisible(true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  ObservationsFragment.newInstance(), OBSERVATIONS_FRAGMENT_TAG)
                .commit();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            doLogout();
        } else if(id == R.id.nav_login){
            displayLoginFragment();
        }else if(id == R.id.nav_home){
            displayHomeFragment();
        }else if(id == R.id.nav_checklist){
            displayHeaderDetails();
            Boolean fpStarted = preferenceHelper.getBoolean(NavigationDrawerActivity.this, PREF_KEY_FP_STARTED, false);
            if(fpStarted){
                displayCheckedListFragment();
            }
            else{
                displayDepotSelectionFragment();
            }

        }else if(id == R.id.nav_compliance) {
            displayComplianceFragment();
        }else if(id == R.id.nav_observations) {
            displayObservationsFragment();
        }else if (id == R.id.nav_data) {
           displayDataSyncFragment();
        } else if (id == R.id.nav_reports) {
           displayReportsFragment();
        }
        else if (id == R.id.nav_reload) {
            setTitle("Reload");
            displayHeaderDetails();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,  ReloadFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                    .commit();
        }else if (id == R.id.nav_local_db_status) {
            setTitle("Local DB status");
            displayHeaderDetails();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,  LocalDBStatusFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                    .commit();
        } else if (id == R.id.nav_terms) {
            setTitle("Terms");
            displayHeaderDetails();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,  TermsFragment.newInstance(), LOGIN_FRAGMENT_TAG)
                    .commit();
        }else if (id == R.id.nav_exit) {
            displayExitDialog();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void doLogout(){
        displayLoginFragment();
    }

    private void displayHeaderDetails(){
        Boolean fpStarted = preferenceHelper.getBoolean(NavigationDrawerActivity.this, PREF_KEY_FP_STARTED, false);
        if(fpStarted){
            toolbarDetails.setVisibility(View.VISIBLE);
            toolbarDetails.setText(preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_SELECTED_DEPOT, "")+
                    "/"+
                    preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_SELECTED_SECTION, "")+
                    "/"+
                    preferenceHelper.getString(NavigationDrawerActivity.this, PREF_KEY_SELECTED_USER, ""));
        }else{
            toolbarDetails.setVisibility(View.GONE);
        }
    }

}












