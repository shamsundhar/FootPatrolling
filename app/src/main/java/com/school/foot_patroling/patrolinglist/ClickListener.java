package com.school.foot_patroling.patrolinglist;

import com.school.foot_patroling.register.model.ObservationsCheckListDto;

public interface ClickListener {
    void onCheckListSwitchSelected(ObservationsCheckListDto model, int position);
}
