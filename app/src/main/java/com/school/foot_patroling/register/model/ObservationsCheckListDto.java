package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ObservationsCheckListDto")
public class ObservationsCheckListDto{
    public static final int LOW = 0;
    public static final int MEDIUM = 1;
    public static final int HIGH = 2;



    private boolean isChecked;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seqId")
    private String seqId;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "displaySequence")
    private String displaySequence;
    @ColumnInfo(name = "fromDate")
    private String fromDate;
    @ColumnInfo(name = "inspectionType")
    private String inspectionType;
    @ColumnInfo(name = "observationCategory")
    private String observationCategory;
    @ColumnInfo(name = "observationItem")
    private String observationItem;
    @ColumnInfo(name = "priority")
    private String priority;

    @ColumnInfo(name = "thruDate")
    private String thruDate;

    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(String displaySequence) {
        this.displaySequence = displaySequence;
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
        if(priority.equals("null") || priority.equals("")){
            priority="Low";
        }
        this.priority = priority;
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

    public int getPriorityValue(){
        if(getPriority().equals("Low")){
            return LOW;
        }
        else if(getPriority().equals("Medium")){
            return MEDIUM;
        }
        else{
            return HIGH;
        }
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

//    @Override
//    public int compareTo(@NonNull ObservationsCheckListDto o2) {
//        return getPriorityValue() - o2.getPriorityValue();
//    }
}