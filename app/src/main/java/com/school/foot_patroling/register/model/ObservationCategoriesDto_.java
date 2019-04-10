
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ObservationCategoriesDto_")
public class ObservationCategoriesDto_ {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seqId")
    private String seqId;
    @ColumnInfo(name = "department")
    private String department;
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "fromDate")
    private String fromDate;
    @ColumnInfo(name = "inspectionType")
    private String inspectionType;
    @ColumnInfo(name = "observationCategory")
    private String observationCategory;
    @ColumnInfo(name = "remark")
    private String remark;
    @ColumnInfo(name = "thruDate")
    private String thruDate;

    public ObservationCategoriesDto_() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getObservationCategory() {
        return observationCategory;
    }

    public void setObservationCategory(String observationCategory) {
        this.observationCategory = observationCategory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getThruDate() {
        return thruDate;
    }

    public void setThruDate(String thruDate) {
        this.thruDate = thruDate;
    }

}
