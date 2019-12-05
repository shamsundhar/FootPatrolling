package com.school.foot_patroling.register.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "fp_movement")
public class FpMovementDto {
    @ColumnInfo(name = "altitude")
    private String altitude;
    @ColumnInfo(name = "created_stamp")
    private String createdStamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String createdTxStamp;
    @ColumnInfo(name = "date_time")
    private String dateTime;
    @ColumnInfo(name = "device_id")
    private String deviceId;
    @ColumnInfo(name = "device_seq_id")
    private String deviceSeqId;
    @ColumnInfo(name = "last_updated_stamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String lastUpdatedTxStamp;
    @ColumnInfo(name = "latitude")
    private String latitude;
    @ColumnInfo(name = "longitude")
    private String longitude;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "track_of")
    private String trackOf;
    @ColumnInfo(name = "track_type")
    private String trackType;

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getTrackOf() {
        return trackOf;
    }

    public void setTrackOf(String trackOf) {
        this.trackOf = trackOf;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }
}
