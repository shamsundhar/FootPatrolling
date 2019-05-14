
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import javax.annotation.Nonnull;

@Entity(tableName = "observation_categories")
public class ObservationCategoriesDto {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "department")
    private String department;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "from_date")
    private String fromDate;
    @ColumnInfo(name = "inspection_type")
    private String inspectionType;
    @ColumnInfo(name = "observation_category")
    private String observationCategory;
    @ColumnInfo(name = "remark")
    private String remark;
    @ColumnInfo(name = "thru_date")
    private String thruDate;

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
