
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "MasterDto")
public class MasterDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "appName")
    private String appName;
    @Embedded
    private Object createdFootPatrollingInspectionDto;
    @Embedded
    private CreatedFootPatrollingSectionsDto createdFootPatrollingSectionsDto;
    @Embedded
    private Object createdFpLocationTrackDto;
    @Embedded
    private CreatedFunctionalLocationHierarchyDto createdFunctionalLocationHierarchyDto;
    @Embedded
    private CreatedObservationCategoriesDto createdObservationCategoriesDto;
    @Embedded
    private CreatedObservationsCheckListDto createdObservationsCheckListDto;
    @Embedded
    private Object createdResponseCompliancesDto;
    @Embedded
    private CreatedResponseFacilityDto createdResponseFacilityDto;
    @Embedded
    private CreatedResponseInspectionTypeDto createdResponseInspectionTypeDto;
    @Embedded
    private Object createdResponseObservationsDto;
    @Embedded
    private CreatedResponseOheLocationDto createdResponseOheLocationDto;
    @Embedded
    private CreatedResponseProductDto createdResponseProductDto;
    @Embedded
    private CreatedResponseUserLoginDto createdResponseUserLoginDto;
    @ColumnInfo(name = "currentTimestamp")
    private String currentTimestamp;
    @Embedded
    private Object deletedResponseFacilityDto;
    @Embedded
    private Object deletedResponseOheLocationDto;
    @Embedded
    private Object deletedResponseProductDto;
    @Embedded
    private Object deletedResponseUserLoginDto;
    @ColumnInfo(name = "imeiAuth")
    private Boolean imeiAuth;
    @ColumnInfo(name = "imeiNo")
    private String imeiNo;
    @ColumnInfo(name = "message")
    private String message;
    @ColumnInfo(name = "previousTimestamp")
    private String previousTimestamp;
    @Embedded
    private Object updatedFootPatrollingInspectionDto;
    @Embedded
    private UpdatedFootPatrollingSectionsDto updatedFootPatrollingSectionsDto;
    @Embedded
    private Object updatedFpLocationTrackDto;
    @Embedded
    private UpdatedFunctionalLocationHierarchyDto updatedFunctionalLocationHierarchyDto;
    @Embedded
    private UpdatedObservationCategoriesDto updatedObservationCategoriesDto;
    @Embedded
    private UpdatedObservationsCheckListDto updatedObservationsCheckListDto;
    @Embedded
    private Object updatedResponseCompliancesDto;
    @Embedded
    private UpdatedResponseFacilityDto updatedResponseFacilityDto;
    @Embedded
    private UpdatedResponseInspectionTypeDto updatedResponseInspectionTypeDto;
    @Embedded
    private Object updatedResponseObservationsDto;
    @Embedded
    private UpdatedResponseOheLocationDto updatedResponseOheLocationDto;
    @Embedded
    private UpdatedResponseProductDto updatedResponseProductDto;
    @Embedded
    private UpdatedResponseUserLoginDto updatedResponseUserLoginDto;

    public MasterDto() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Object getCreatedFootPatrollingInspectionDto() {
        return createdFootPatrollingInspectionDto;
    }

    public void setCreatedFootPatrollingInspectionDto(Object createdFootPatrollingInspectionDto) {
        this.createdFootPatrollingInspectionDto = createdFootPatrollingInspectionDto;
    }

    public CreatedFootPatrollingSectionsDto getCreatedFootPatrollingSectionsDto() {
        return createdFootPatrollingSectionsDto;
    }

    public void setCreatedFootPatrollingSectionsDto(CreatedFootPatrollingSectionsDto createdFootPatrollingSectionsDto) {
        this.createdFootPatrollingSectionsDto = createdFootPatrollingSectionsDto;
    }

    public Object getCreatedFpLocationTrackDto() {
        return createdFpLocationTrackDto;
    }

    public void setCreatedFpLocationTrackDto(Object createdFpLocationTrackDto) {
        this.createdFpLocationTrackDto = createdFpLocationTrackDto;
    }

    public CreatedFunctionalLocationHierarchyDto getCreatedFunctionalLocationHierarchyDto() {
        return createdFunctionalLocationHierarchyDto;
    }

    public void setCreatedFunctionalLocationHierarchyDto(CreatedFunctionalLocationHierarchyDto createdFunctionalLocationHierarchyDto) {
        this.createdFunctionalLocationHierarchyDto = createdFunctionalLocationHierarchyDto;
    }

    public CreatedObservationCategoriesDto getCreatedObservationCategoriesDto() {
        return createdObservationCategoriesDto;
    }

    public void setCreatedObservationCategoriesDto(CreatedObservationCategoriesDto createdObservationCategoriesDto) {
        this.createdObservationCategoriesDto = createdObservationCategoriesDto;
    }

    public CreatedObservationsCheckListDto getCreatedObservationsCheckListDto() {
        return createdObservationsCheckListDto;
    }

    public void setCreatedObservationsCheckListDto(CreatedObservationsCheckListDto createdObservationsCheckListDto) {
        this.createdObservationsCheckListDto = createdObservationsCheckListDto;
    }

    public Object getCreatedResponseCompliancesDto() {
        return createdResponseCompliancesDto;
    }

    public void setCreatedResponseCompliancesDto(Object createdResponseCompliancesDto) {
        this.createdResponseCompliancesDto = createdResponseCompliancesDto;
    }

    public CreatedResponseFacilityDto getCreatedResponseFacilityDto() {
        return createdResponseFacilityDto;
    }

    public void setCreatedResponseFacilityDto(CreatedResponseFacilityDto createdResponseFacilityDto) {
        this.createdResponseFacilityDto = createdResponseFacilityDto;
    }

    public CreatedResponseInspectionTypeDto getCreatedResponseInspectionTypeDto() {
        return createdResponseInspectionTypeDto;
    }

    public void setCreatedResponseInspectionTypeDto(CreatedResponseInspectionTypeDto createdResponseInspectionTypeDto) {
        this.createdResponseInspectionTypeDto = createdResponseInspectionTypeDto;
    }

    public Object getCreatedResponseObservationsDto() {
        return createdResponseObservationsDto;
    }

    public void setCreatedResponseObservationsDto(Object createdResponseObservationsDto) {
        this.createdResponseObservationsDto = createdResponseObservationsDto;
    }

    public CreatedResponseOheLocationDto getCreatedResponseOheLocationDto() {
        return createdResponseOheLocationDto;
    }

    public void setCreatedResponseOheLocationDto(CreatedResponseOheLocationDto createdResponseOheLocationDto) {
        this.createdResponseOheLocationDto = createdResponseOheLocationDto;
    }

    public CreatedResponseProductDto getCreatedResponseProductDto() {
        return createdResponseProductDto;
    }

    public void setCreatedResponseProductDto(CreatedResponseProductDto createdResponseProductDto) {
        this.createdResponseProductDto = createdResponseProductDto;
    }

    public CreatedResponseUserLoginDto getCreatedResponseUserLoginDto() {
        return createdResponseUserLoginDto;
    }

    public void setCreatedResponseUserLoginDto(CreatedResponseUserLoginDto createdResponseUserLoginDto) {
        this.createdResponseUserLoginDto = createdResponseUserLoginDto;
    }

    public String getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(String currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public Object getDeletedResponseFacilityDto() {
        return deletedResponseFacilityDto;
    }

    public void setDeletedResponseFacilityDto(Object deletedResponseFacilityDto) {
        this.deletedResponseFacilityDto = deletedResponseFacilityDto;
    }

    public Object getDeletedResponseOheLocationDto() {
        return deletedResponseOheLocationDto;
    }

    public void setDeletedResponseOheLocationDto(Object deletedResponseOheLocationDto) {
        this.deletedResponseOheLocationDto = deletedResponseOheLocationDto;
    }

    public Object getDeletedResponseProductDto() {
        return deletedResponseProductDto;
    }

    public void setDeletedResponseProductDto(Object deletedResponseProductDto) {
        this.deletedResponseProductDto = deletedResponseProductDto;
    }

    public Object getDeletedResponseUserLoginDto() {
        return deletedResponseUserLoginDto;
    }

    public void setDeletedResponseUserLoginDto(Object deletedResponseUserLoginDto) {
        this.deletedResponseUserLoginDto = deletedResponseUserLoginDto;
    }

    public Boolean getImeiAuth() {
        return imeiAuth;
    }

    public void setImeiAuth(Boolean imeiAuth) {
        this.imeiAuth = imeiAuth;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPreviousTimestamp() {
        return previousTimestamp;
    }

    public void setPreviousTimestamp(String previousTimestamp) {
        this.previousTimestamp = previousTimestamp;
    }

    public Object getUpdatedFootPatrollingInspectionDto() {
        return updatedFootPatrollingInspectionDto;
    }

    public void setUpdatedFootPatrollingInspectionDto(Object updatedFootPatrollingInspectionDto) {
        this.updatedFootPatrollingInspectionDto = updatedFootPatrollingInspectionDto;
    }

    public UpdatedFootPatrollingSectionsDto getUpdatedFootPatrollingSectionsDto() {
        return updatedFootPatrollingSectionsDto;
    }

    public void setUpdatedFootPatrollingSectionsDto(UpdatedFootPatrollingSectionsDto updatedFootPatrollingSectionsDto) {
        this.updatedFootPatrollingSectionsDto = updatedFootPatrollingSectionsDto;
    }

    public Object getUpdatedFpLocationTrackDto() {
        return updatedFpLocationTrackDto;
    }

    public void setUpdatedFpLocationTrackDto(Object updatedFpLocationTrackDto) {
        this.updatedFpLocationTrackDto = updatedFpLocationTrackDto;
    }

    public UpdatedFunctionalLocationHierarchyDto getUpdatedFunctionalLocationHierarchyDto() {
        return updatedFunctionalLocationHierarchyDto;
    }

    public void setUpdatedFunctionalLocationHierarchyDto(UpdatedFunctionalLocationHierarchyDto updatedFunctionalLocationHierarchyDto) {
        this.updatedFunctionalLocationHierarchyDto = updatedFunctionalLocationHierarchyDto;
    }

    public UpdatedObservationCategoriesDto getUpdatedObservationCategoriesDto() {
        return updatedObservationCategoriesDto;
    }

    public void setUpdatedObservationCategoriesDto(UpdatedObservationCategoriesDto updatedObservationCategoriesDto) {
        this.updatedObservationCategoriesDto = updatedObservationCategoriesDto;
    }

    public UpdatedObservationsCheckListDto getUpdatedObservationsCheckListDto() {
        return updatedObservationsCheckListDto;
    }

    public void setUpdatedObservationsCheckListDto(UpdatedObservationsCheckListDto updatedObservationsCheckListDto) {
        this.updatedObservationsCheckListDto = updatedObservationsCheckListDto;
    }

    public Object getUpdatedResponseCompliancesDto() {
        return updatedResponseCompliancesDto;
    }

    public void setUpdatedResponseCompliancesDto(Object updatedResponseCompliancesDto) {
        this.updatedResponseCompliancesDto = updatedResponseCompliancesDto;
    }

    public UpdatedResponseFacilityDto getUpdatedResponseFacilityDto() {
        return updatedResponseFacilityDto;
    }

    public void setUpdatedResponseFacilityDto(UpdatedResponseFacilityDto updatedResponseFacilityDto) {
        this.updatedResponseFacilityDto = updatedResponseFacilityDto;
    }

    public UpdatedResponseInspectionTypeDto getUpdatedResponseInspectionTypeDto() {
        return updatedResponseInspectionTypeDto;
    }

    public void setUpdatedResponseInspectionTypeDto(UpdatedResponseInspectionTypeDto updatedResponseInspectionTypeDto) {
        this.updatedResponseInspectionTypeDto = updatedResponseInspectionTypeDto;
    }

    public Object getUpdatedResponseObservationsDto() {
        return updatedResponseObservationsDto;
    }

    public void setUpdatedResponseObservationsDto(Object updatedResponseObservationsDto) {
        this.updatedResponseObservationsDto = updatedResponseObservationsDto;
    }

    public UpdatedResponseOheLocationDto getUpdatedResponseOheLocationDto() {
        return updatedResponseOheLocationDto;
    }

    public void setUpdatedResponseOheLocationDto(UpdatedResponseOheLocationDto updatedResponseOheLocationDto) {
        this.updatedResponseOheLocationDto = updatedResponseOheLocationDto;
    }

    public UpdatedResponseProductDto getUpdatedResponseProductDto() {
        return updatedResponseProductDto;
    }

    public void setUpdatedResponseProductDto(UpdatedResponseProductDto updatedResponseProductDto) {
        this.updatedResponseProductDto = updatedResponseProductDto;
    }

    public UpdatedResponseUserLoginDto getUpdatedResponseUserLoginDto() {
        return updatedResponseUserLoginDto;
    }

    public void setUpdatedResponseUserLoginDto(UpdatedResponseUserLoginDto updatedResponseUserLoginDto) {
        this.updatedResponseUserLoginDto = updatedResponseUserLoginDto;
    }

}
