package com.school.foot_patroling.register.model;

import java.util.List;

public class AppToServerCreatedFootPatrollingInspectionDto {
    private String count;
    private List<Inspection> footPatrollingInspectionDtos;

    public List<Inspection> getFootPatrollingInspectionDtos() {
        return footPatrollingInspectionDtos;
    }

    public void setFootPatrollingInspectionDtos(List<Inspection> footPatrollingInspectionDtos) {
        this.footPatrollingInspectionDtos = footPatrollingInspectionDtos;
    }
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}
