
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "InspectionTypeDto")
public class InspectionTypeDto {

    @PrimaryKey
    @ColumnInfo(name = "seqId")
    private String seqId;
    @ColumnInfo(name = "department")
    private Object department;
    @ColumnInfo(name = "description")
    private Object description;
    @ColumnInfo(name = "fromDate")
    private Object fromDate;
    @ColumnInfo(name = "inspectionType")
    private String inspectionType;
    @ColumnInfo(name = "thruDate")
    private Object thruDate;

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public Object getThruDate() {
        return thruDate;
    }

    public void setThruDate(Object thruDate) {
        this.thruDate = thruDate;
    }

}
