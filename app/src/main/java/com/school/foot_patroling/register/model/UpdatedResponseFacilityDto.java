
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedResponseFacilityDto {

    private Integer count;
    private List<FacilityDto_> facilityDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FacilityDto_> getFacilityDtos() {
        return facilityDtos;
    }

    public void setFacilityDtos(List<FacilityDto_> facilityDtos) {
        this.facilityDtos = facilityDtos;
    }

}
