package com.school.foot_patroling.register.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "inspection")
public class Inspection {


    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "device_id")
    private String deviceId;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "device_seq_id")
    private String deviceSeqId;
    @ColumnInfo(name = "inspection_type")
    private String inspectionType;
    @ColumnInfo(name = "start_time")
    private String startTime;
    @ColumnInfo(name = "stop_time")
    private String stopTime;
    @ColumnInfo(name = "inspection_by")
    private String inspectionBy;
    @ColumnInfo(name = "section")
    private String section;
    @ColumnInfo(name = "last_updated_stamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String lastUpdatedtxStamp;
    @ColumnInfo(name = "created_stamp")
    private String createdStamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String createdTxStamp;
    @ColumnInfo(name = "facility_id")
    private String facilityId;
    @ColumnInfo(name = "km")
    private String km;
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

    @NonNull
    public String getDeviceSeqId() {
        return deviceSeqId;
    }

    public void setDeviceSeqId(@NonNull String deviceSeqId) {
        this.deviceSeqId = deviceSeqId;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getInspectionBy() {
        return inspectionBy;
    }

    public void setInspectionBy(String inspectionBy) {
        this.inspectionBy = inspectionBy;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(String lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getLastUpdatedtxStamp() {
        return lastUpdatedtxStamp;
    }

    public void setLastUpdatedtxStamp(String lastUpdatedtxStamp) {
        this.lastUpdatedtxStamp = lastUpdatedtxStamp;
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

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
