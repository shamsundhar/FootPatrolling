package com.school.foot_patroling.injection.components;


import com.school.foot_patroling.NavigationDrawerActivity;
import com.school.foot_patroling.injection.modules.ActivityModule;
import com.school.foot_patroling.injection.scopes.PerActivity;
import com.school.foot_patroling.register.RegisterActivity;


import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent extends AppComponent {

//    @ActivityContext Context activityContext();
//    @ActivityFragmentManager FragmentManager defaultFragmentManager();


    // create inject methods for your Activities here

  //  void inject(SignupActivity activity);
    void inject(NavigationDrawerActivity activity);
    void inject(RegisterActivity activity);

}
