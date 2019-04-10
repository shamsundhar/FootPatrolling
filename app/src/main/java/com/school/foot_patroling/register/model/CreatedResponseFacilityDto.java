
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.FacilityDtoConverter;

import java.util.List;
@Entity(tableName = "ResponseFacilityDto")
public class CreatedResponseFacilityDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count12")
    private Integer count;
    @TypeConverters(FacilityDtoConverter.class)
    @ColumnInfo(name = "facilityDtos4")
    private List<FacilityDto> facilityDtos;

    public CreatedResponseFacilityDto() {
    }

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
