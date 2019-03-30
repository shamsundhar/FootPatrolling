
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;
@Entity(tableName = "ResponseInspectionTypeDto")
public class CreatedResponseInspectionTypeDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "inspectionTypeDtos")
    private List<InspectionTypeDto> inspectionTypeDtos = null;

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
