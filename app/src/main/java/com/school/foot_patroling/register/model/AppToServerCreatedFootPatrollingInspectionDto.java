package com.school.foot_patroling.register.model;

import java.util.List;

public class AppToServerCreatedFootPatrollingInspectionDto {
    private String count;

    public List<Inspection> getFootPatrollingInspectionDtos() {
        return footPatrollingInspectionDtos;
    }

    public void setFootPatrollingInspectionDtos(List<Inspection> footPatrollingInspectionDtos) {
        this.footPatrollingInspectionDtos = footPatrollingInspectionDtos;
    }

    private List<Inspection> footPatrollingInspectionDtos;
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}
