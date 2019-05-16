package com.school.foot_patroling.injection.components;



import com.school.foot_patroling.datasync.DataSyncFragment;
import com.school.foot_patroling.injection.modules.FragmentModule;
import com.school.foot_patroling.injection.scopes.PerFragment;
import com.school.foot_patroling.patrolinglist.PatrolingListFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(PatrolingListFragment fragment);
    void inject(DataSyncFragment fragment);
  //  void inject(ResetPasswordEnterOtpFragment fragment);
  //  void inject(ResetPasswordEnterMobileFragment fragment);
  //  void inject(ShopListFragment fragment);
  //  void inject(OrderHistoryFragment fragment);
}
