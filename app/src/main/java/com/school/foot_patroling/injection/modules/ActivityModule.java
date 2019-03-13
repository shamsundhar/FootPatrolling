package com.school.foot_patroling.injection.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.school.foot_patroling.injection.qualifier.ActivityContext;
import com.school.foot_patroling.injection.qualifier.ActivityFragmentManager;
import com.school.foot_patroling.injection.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    Context provideActivityContext() { return mActivity; }

    @Provides
    @PerActivity
    @ActivityFragmentManager
    FragmentManager provideFragmentManager() { return mActivity.getSupportFragmentManager(); }
}
