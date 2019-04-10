
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.FacilityDto_Converter;

import java.util.List;
@Entity(tableName = "UpdatedResponseFacilityDto")
public class UpdatedResponseFacilityDto {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count7")
    private Integer count;
    @TypeConverters(FacilityDto_Converter.class)
    @ColumnInfo(name = "facilityDtos")
    private List<FacilityDto_> facilityDtos;

    public UpdatedResponseFacilityDto() {
    }

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
