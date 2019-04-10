
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.InspectionTypeDtoConverter;

import java.util.List;
@Entity(tableName = "ResponseInspectionTypeDto")
public class CreatedResponseInspectionTypeDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count3")
    private Integer count;
    @TypeConverters(InspectionTypeDtoConverter.class)
    @ColumnInfo(name = "inspectionTypeDtos5")
    private List<InspectionTypeDto> inspectionTypeDtos = null;

    public CreatedResponseInspectionTypeDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<InspectionTypeDto> getInspectionTypeDtos() {
        return inspectionTypeDtos;
    }

    public void setInspectionTypeDtos(List<InspectionTypeDto> inspectionTypeDtos) {
        this.inspectionTypeDtos = inspectionTypeDtos;
    }

}
