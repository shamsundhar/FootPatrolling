package com.school.foot_patroling.register.model;

import java.util.List;

public class AppToServerCreatedResponseCompliancesDto {
    private String count;
    private List<Compliance> compliancesDtos;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Compliance> getCompliancesDtos() {
        return compliancesDtos;
    }

    public void setCompliancesDtos(List<Compliance> compliancesDtos) {
        this.compliancesDtos = compliancesDtos;
    }
}
