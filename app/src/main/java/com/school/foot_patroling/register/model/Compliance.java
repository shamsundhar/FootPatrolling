package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "compliances")
public class Compliance {
    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "device_id")
    private String deviceId;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "device_seq_id")
    private String deviceSeqId;
    @ColumnInfo(name = "obeservation_seq_id")
    private String obeservationSeqId;
    @ColumnInfo(name = "compliance_fullfilled")
    private String complianceFullfilled;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "compliance_by")
    private String complianceBy;
    @ColumnInfo(name = "complied_date_time")
    private String compliedDateTime;
    @ColumnInfo(name = "last_updated_stamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String lastUpdatedTxStamp;

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

    public String getObeservationSeqId() {
        return obeservationSeqId;
    }

    public void setObeservationSeqId(String obeservationSeqId) {
        this.obeservationSeqId = obeservationSeqId;
    }

    public String getComplianceFullfilled() {
        return complianceFullfilled;
    }

    public void setComplianceFullfilled(String complianceFullfilled) {
        this.complianceFullfilled = complianceFullfilled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplianceBy() {
        return complianceBy;
    }

    public void setComplianceBy(String complianceBy) {
        this.complianceBy = complianceBy;
    }

    public String getCompliedDateTime() {
        return compliedDateTime;
    }

    public void setCompliedDateTime(String compliedDateTime) {
        this.compliedDateTime = compliedDateTime;
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

    public String getComplianceRemark() {
        return complianceRemark;
    }

    public void setComplianceRemark(String complianceRemark) {
        this.complianceRemark = complianceRemark;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @ColumnInfo(name = "created_stamp")
    private String createdStamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String createdTxStamp;
    @ColumnInfo(name = "compliance_remark")
    private String complianceRemark;
    @ColumnInfo(name = "action")
    private String action;

}
