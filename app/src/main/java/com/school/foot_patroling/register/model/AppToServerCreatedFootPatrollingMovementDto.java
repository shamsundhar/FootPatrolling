package com.school.foot_patroling.register.model;

import java.util.List;

public class AppToServerCreatedFootPatrollingMovementDto {
    private String count;
    private List<FpMovementDto> fpMovementDtos;

    public List<FpMovementDto> getFootPatrollingMovementDtos() {
        return fpMovementDtos;
    }

    public void setFootPatrollingMovementDtos(List<FpMovementDto> fpMovementDtos) {
        this.fpMovementDtos = fpMovementDtos;
    }
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
