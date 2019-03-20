package com.school.foot_patroling.register.model;

public class RegistrationRequestModel {
    private String previousTimestamp;
    private String currentTimestamp;

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

    private String imeiNo;
    private String appName;
}
