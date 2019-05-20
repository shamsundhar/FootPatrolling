package com.school.foot_patroling.register.model;

public class RegistrationRequestModel {
    private String previousTimestamp;
    private String currentTimestamp;
    private String imeiNo;
    private String appName;
    private AppToServerCreatedFootPatrollingInspectionDto appToServerCreatedFootPatrollingInspectionDto;
    private AppToServerCreatedResponseObservationsDto appToServerCreatedResponseObservationsDto;

    public AppToServerCreatedResponseObservationsDto getAppToServerCreatedResponseObservationsDto() {
        return appToServerCreatedResponseObservationsDto;
    }

    public void setAppToServerCreatedResponseObservationsDto(AppToServerCreatedResponseObservationsDto appToServerCreatedResponseObservationsDto) {
        this.appToServerCreatedResponseObservationsDto = appToServerCreatedResponseObservationsDto;
    }



    public AppToServerCreatedFootPatrollingInspectionDto getAppToServerCreatedFootPatrollingInspectionDto() {
        return appToServerCreatedFootPatrollingInspectionDto;
    }

    public void setAppToServerCreatedFootPatrollingInspectionDto(AppToServerCreatedFootPatrollingInspectionDto appToServerCreatedFootPatrollingInspectionDto) {
        this.appToServerCreatedFootPatrollingInspectionDto = appToServerCreatedFootPatrollingInspectionDto;
    }
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
