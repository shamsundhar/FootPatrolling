
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedResponseFacilityDto {

    private Integer count;
    private List<FacilityDto> facilityDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FacilityDto> getFacilityDtos() {
        return facilityDtos;
    }

    public void setFacilityDtos(List<FacilityDto> facilityDtos) {
        this.facilityDtos = facilityDtos;
    }

}
