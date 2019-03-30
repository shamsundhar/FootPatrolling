
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ObservationsCheckListDto")
public class ObservationsCheckListDto {

    @PrimaryKey
    @ColumnInfo(name = "seqId")
    private String seqId;
    @ColumnInfo(name = "description")
    private Object description;
    @ColumnInfo(name = "displaySequence")
    private String displaySequence;
    @ColumnInfo(name = "fromDate")
    private Object fromDate;
    @ColumnInfo(name = "inspectionType")
    private String inspectionType;
    @ColumnInfo(name = "observationCategory")
    private String observationCategory;
    @ColumnInfo(name = "observationItem")
    private String observationItem;
    @ColumnInfo(name = "priority")
    private String priority;
    @ColumnInfo(name = "thruDate")
    private Object thruDate;

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(String displaySequence) {
        this.displaySequence = displaySequence;
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

    public String getObservationCategory() {
        return observationCategory;
    }

    public void setObservationCategory(String observationCategory) {
        this.observationCategory = observationCategory;
    }

    public String getObservationItem() {
        return observationItem;
    }

    public void setObservationItem(String observationItem) {
        this.observationItem = observationItem;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
