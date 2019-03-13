package com.school.foot_patroling;

import android.app.ProgressDialog;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
import android.widget.TextView;

import com.school.foot_patroling.login.LoginFragment;


import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NavigationDrawerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView profileAvatar;
    TextView profileNameTV;
    TextView profileClassTV;
    TextView profileSubjectsTV;
    MenuItem attendanceItem;
    MenuItem notesItem;

    String market_uri = "https://play.google.com/store/apps/details?id=";
    private static final String TODAY_FRAGMENT_TAG = "TODAY_FRAGMENT";
    private static final String ATTENDANCE_FRAGMENT_TAG = "ATTENDANCE_FRAGMENT";
    private static final String NOTES_FRAGMENT_TAG = "NOTES_FRAGMENT";
    private static final String RECOMENDATION_FRAGMENT_TAG = "RECOMENDATION_FRAGMENT";
    private static final String MESSAGES_FRAGMENT_TAG = "MESSAGES_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayLoginFragment();
    }

    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void displayLoginFragment(){
        setTitle(null);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,  LoginFragment.newInstance(), TODAY_FRAGMENT_TAG)
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
        }else if (id == R.id.nav_data) {

        } else if (id == R.id.nav_reports) {

        }
        else if (id == R.id.nav_schedule) {

        }
        else if (id == R.id.nav_reload) {

        }else if (id == R.id.nav_local_db_status) {

        } else if (id == R.id.nav_terms) {

        }else if (id == R.id.nav_exit) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void doLogout(){

    }
    private void navigateToMainActivity(){

    }
    private void clearDB(){

    }
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












