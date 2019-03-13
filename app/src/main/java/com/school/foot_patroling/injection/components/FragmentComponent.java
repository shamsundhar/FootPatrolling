package com.school.foot_patroling.injection.components;



import com.school.foot_patroling.injection.modules.FragmentModule;
import com.school.foot_patroling.injection.scopes.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
  //  void inject(SignUpFragment fragment);
  //  void inject(SignUpEnterOtpFragment fragment);
  //  void inject(ResetPasswordEnterOtpFragment fragment);
  //  void inject(ResetPasswordEnterMobileFragment fragment);
  //  void inject(ShopListFragment fragment);
  //  void inject(OrderHistoryFragment fragment);
}
