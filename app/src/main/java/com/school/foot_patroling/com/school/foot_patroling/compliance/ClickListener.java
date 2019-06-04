package com.school.foot_patroling.com.school.foot_patroling.compliance;

import com.school.foot_patroling.register.model.ObservationsCheckListDto;

public interface ClickListener {
    void onCheckListSwitchSelected(ObservationsCheckListDto model, int position);
}
