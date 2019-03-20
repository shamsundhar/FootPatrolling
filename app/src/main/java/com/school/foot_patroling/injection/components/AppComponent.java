package com.school.foot_patroling.injection.components;

import android.content.Context;
import android.content.res.Resources;

import com.school.foot_patroling.injection.modules.AppModule;
import com.school.foot_patroling.injection.modules.DataModule;
//import com.school.edsense_lite.injection.modules.NetModule;
import com.school.foot_patroling.injection.modules.NetModule;
import com.school.foot_patroling.injection.qualifier.AppContext;
import com.school.foot_patroling.injection.scopes.PerApplication;
import com.school.foot_patroling.register.RegisterApi;

import dagger.Component;

@PerApplication
@Component(modules={AppModule.class, DataModule.class, NetModule.class})
public interface AppComponent {
    @AppContext
    Context appContext();
    Resources resources();
    //    RefWatcher refWatcher();
//
//    Realm realm();
//    CountryRepo countryRepo();
    RegisterApi registerApi();
//    ShopApi shopApi();
}
