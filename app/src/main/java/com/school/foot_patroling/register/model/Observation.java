package com.school.foot_patroling.register.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "observations")
public class Observation {
    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "device_id")
    private String deviceId;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "device_seq_id")
    private String deviceSeqId;
    @ColumnInfo(name = "inspection_seq_id")
    private String inspectionSeqId;
    @ColumnInfo(name = "observation_category")
    private String observationCategory;
    @ColumnInfo(name = "observation_item")
    private String observationItem;
    @ColumnInfo(name = "observation")
    private String observation;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "action")
    private String action;
    @ColumnInfo(name = "action_by")
    private String actionBy;
    @ColumnInfo(name = "created_by")
    private String createdBy;
    @ColumnInfo(name = "created_date_time")
    private String createdDateTime;
    @ColumnInfo(name = "last_updated_stamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String lastUpdatedTxStamp;
    @ColumnInfo(name = "created_stamp")
    private String createdStamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String createdTxStamp;
    @ColumnInfo(name = "location")
    private String location;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceSeqId() {
        return deviceSeqId;
    }

    public void setDeviceSeqId(String deviceSeqId) {
        this.deviceSeqId = deviceSeqId;
    }

    public String getInspectionSeqId() {
        return inspectionSeqId;
    }

    public void setInspectionSeqId(String inspectionSeqId) {
        this.inspectionSeqId = inspectionSeqId;
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(String lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getLastUpdatedTxStamp() {
        return lastUpdatedTxStamp;
    }

    public void setLastUpdatedTxStamp(String lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }

    public String getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(String createdStamp) {
        this.createdStamp = createdStamp;
    }

    public String getCreatedTxStamp() {
        return createdTxStamp;
    }

    public void setCreatedTxStamp(String createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
