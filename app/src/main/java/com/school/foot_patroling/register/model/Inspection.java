package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "inspection")
public class Inspection {


    @ColumnInfo(name = "seq_id")
    private String seqid;
    @ColumnInfo(name = "device_id")
    private String deviceid;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "device_seq_id")
    private String deviceseqid;
    @ColumnInfo(name = "inspection_type")
    private String inspectiontype;
    @ColumnInfo(name = "start_time")
    private String starttime;
    @ColumnInfo(name = "stop_time")
    private String stoptime;
    @ColumnInfo(name = "inspection_by")
    private String inspectionby;
    @ColumnInfo(name = "section")
    private String section;
    @ColumnInfo(name = "last_updated_stamp")
    private String lastupdatedstamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String lastupdatedtxstamp;
    @ColumnInfo(name = "created_stamp")
    private String createdstamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String createdtxstamp;

    public String getSeqid() {
        return seqid;
    }

    public void setSeqid(String seqid) {
        this.seqid = seqid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    @NonNull
    public String getDeviceseqid() {
        return deviceseqid;
    }

    public void setDeviceseqid(@NonNull String deviceseqid) {
        this.deviceseqid = deviceseqid;
    }

    public String getInspectiontype() {
        return inspectiontype;
    }

    public void setInspectiontype(String inspectiontype) {
        this.inspectiontype = inspectiontype;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getInspectionby() {
        return inspectionby;
    }

    public void setInspectionby(String inspectionby) {
        this.inspectionby = inspectionby;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastupdatedstamp() {
        return lastupdatedstamp;
    }

    public void setLastupdatedstamp(String lastupdatedstamp) {
        this.lastupdatedstamp = lastupdatedstamp;
    }

    public String getLastupdatedtxstamp() {
        return lastupdatedtxstamp;
    }

    public void setLastupdatedtxstamp(String lastupdatedtxstamp) {
        this.lastupdatedtxstamp = lastupdatedtxstamp;
    }

    public String getCreatedstamp() {
        return createdstamp;
    }

    public void setCreatedstamp(String createdstamp) {
        this.createdstamp = createdstamp;
    }

    public String getCreatedtxstamp() {
        return createdtxstamp;
    }

    public void setCreatedtxstamp(String createdtxstamp) {
        this.createdtxstamp = createdtxstamp;
    }

    public String getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(String facilityid) {
        this.facilityid = facilityid;
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

    @ColumnInfo(name = "facility_id")
    private String facilityid;
    @ColumnInfo(name = "km")
    private String km;
    @ColumnInfo(name = "location")
    private String location;



}
