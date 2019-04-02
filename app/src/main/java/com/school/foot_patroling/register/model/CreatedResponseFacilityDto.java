
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;
@Entity(tableName = "ResponseFacilityDto")
public class CreatedResponseFacilityDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "facilityDtos")
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
