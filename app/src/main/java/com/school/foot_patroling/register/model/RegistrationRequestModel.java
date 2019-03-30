package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "RegistrationRequestModel")
public class RegistrationRequestModel {

    @PrimaryKey
    @ColumnInfo(name = "imeiNo")
    private String imeiNo;
    @ColumnInfo(name = "previousTimestamp")
    private String previousTimestamp;
    @ColumnInfo(name = "currentTimestamp")
    private String currentTimestamp;
    @ColumnInfo(name = "appName")
    private String appName;

    public String getPreviousTimestamp() {
        return previousTimestamp;
    }

    public void setPreviousTimestamp(String previousTimestamp) {
        this.previousTimestamp = previousTimestamp;
    }

    public String getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(String currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public String getImeiNumber() {
        return imeiNo;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNo = imeiNumber;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
