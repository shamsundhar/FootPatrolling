package com.school.foot_patroling.injection.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.school.foot_patroling.injection.qualifier.AppContext;
import com.school.foot_patroling.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @PerApplication
    @AppContext
    Context provideAppContext() {
        return mApp;
    }

    @Provides
    @PerApplication
    Resources provideResources() {
        return mApp.getResources();
    }

}
