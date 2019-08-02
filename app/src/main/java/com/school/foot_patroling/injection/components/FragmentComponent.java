package com.school.foot_patroling.injection.components;



import com.school.foot_patroling.com.school.foot_patroling.compliance.AddComplianceFragment;
import com.school.foot_patroling.com.school.foot_patroling.compliance.ComplianceFragment;
import com.school.foot_patroling.datasync.DataSyncFragment;
import com.school.foot_patroling.injection.modules.FragmentModule;
import com.school.foot_patroling.injection.scopes.PerFragment;
import com.school.foot_patroling.patrolinglist.PatrolingListFragment;
import com.school.foot_patroling.reload.ReloadFragment;
import com.school.foot_patroling.reports.ReportsFragment;
import com.school.foot_patrolling.observations.EditObservationFragment;
import com.school.foot_patrolling.observations.ObservationsFragment;
import com.school.foot_patrolling.observations.ViewObservationFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ActivityComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(PatrolingListFragment fragment);
    void inject(DataSyncFragment fragment);
    void inject(ComplianceFragment fragment);
    void inject(AddComplianceFragment fragment);
    void inject(ReloadFragment fragment);
    void inject(ReportsFragment fragment);
    void inject(ObservationsFragment fragment);
    void inject(EditObservationFragment fragment);
    void inject(ViewObservationFragment fragment);
  //  void inject(OrderHistoryFragment fragment);
}
