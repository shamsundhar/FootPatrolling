
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.InspectionTypeDto_Converter;

import java.util.List;
@Entity(tableName = "UpdatedResponseInspectionTypeDto")
public class UpdatedResponseInspectionTypeDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count17")
    private Integer count;
    @TypeConverters(InspectionTypeDto_Converter.class)
    @ColumnInfo(name = "inspectionTypeDtos")
    private List<InspectionTypeDto_> inspectionTypeDtos;

    public UpdatedResponseInspectionTypeDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<InspectionTypeDto_> getInspectionTypeDtos() {
        return inspectionTypeDtos;
    }

    public void setInspectionTypeDtos(List<InspectionTypeDto_> inspectionTypeDtos) {
        this.inspectionTypeDtos = inspectionTypeDtos;
    }

}
