
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FacilityDto")
public class FacilityDto {

    @PrimaryKey
    @ColumnInfo(name = "facilityId")
    private String facilityId;
    @ColumnInfo(name = "closedDate")
    private String closedDate;
    @ColumnInfo(name = "createdStamp")
    private String createdStamp;
    @ColumnInfo(name = "createdTxStamp")
    private String createdTxStamp;
    @ColumnInfo(name = "defaultDaysToShip")
    private String defaultDaysToShip;
    @ColumnInfo(name = "defaultDimensionUomId")
    private String defaultDimensionUomId;
    @ColumnInfo(name = "defaultInventoryItemTypeId")
    private String defaultInventoryItemTypeId;
    @ColumnInfo(name = "defaultWeightUomId")
    private String defaultWeightUomId;
    @ColumnInfo(name = "depotType")
    private String depotType;
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "facilityName")
    private String facilityName;
    @ColumnInfo(name = "facilitySize")
    private String facilitySize;
    @ColumnInfo(name = "facilitySizeUomId")
    private String facilitySizeUomId;
    @ColumnInfo(name = "facilityTypeId")
    private String facilityTypeId;
    @ColumnInfo(name = "geoPointId")
    private String geoPointId;
    @ColumnInfo(name = "isDisable")
    private String isDisable;
    @ColumnInfo(name = "lastUpdatedStamp")
    private String lastUpdatedStamp;
    @ColumnInfo(name = "lastUpdatedTxStamp")
    private String lastUpdatedTxStamp;
    @ColumnInfo(name = "manufAllocEnable")
    private String manufAllocEnable;
    @ColumnInfo(name = "openedDate")
    private String openedDate;
    @ColumnInfo(name = "organized")
    private String organized;
    @ColumnInfo(name = "ownerPartyId")
    private String ownerPartyId;
    @ColumnInfo(name = "parentFacilityId")
    private String parentFacilityId;
    @ColumnInfo(name = "primaryFacilityGroupId")
    private String primaryFacilityGroupId;
    @ColumnInfo(name = "productStoreId")
    private String productStoreId;
    @ColumnInfo(name = "remarks")
    private String remarks;
    @ColumnInfo(name = "reserveOrderEnumId")
    private String reserveOrderEnumId;
    @ColumnInfo(name = "skipPackInvCheck")
    private String skipPackInvCheck;
    @ColumnInfo(name = "squareFootage")
    private String squareFootage;

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
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

    public String getDefaultDaysToShip() {
        return defaultDaysToShip;
    }

    public void setDefaultDaysToShip(String defaultDaysToShip) {
        this.defaultDaysToShip = defaultDaysToShip;
    }

    public String getDefaultDimensionUomId() {
        return defaultDimensionUomId;
    }

    public void setDefaultDimensionUomId(String defaultDimensionUomId) {
        this.defaultDimensionUomId = defaultDimensionUomId;
    }

    public String getDefaultInventoryItemTypeId() {
        return defaultInventoryItemTypeId;
    }

    public void setDefaultInventoryItemTypeId(String defaultInventoryItemTypeId) {
        this.defaultInventoryItemTypeId = defaultInventoryItemTypeId;
    }

    public String getDefaultWeightUomId() {
        return defaultWeightUomId;
    }

    public void setDefaultWeightUomId(String defaultWeightUomId) {
        this.defaultWeightUomId = defaultWeightUomId;
    }

    public String getDepotType() {
        return depotType;
    }

    public void setDepotType(String depotType) {
        this.depotType = depotType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilitySize() {
        return facilitySize;
    }

    public void setFacilitySize(String facilitySize) {
        this.facilitySize = facilitySize;
    }

    public String getFacilitySizeUomId() {
        return facilitySizeUomId;
    }

    public void setFacilitySizeUomId(String facilitySizeUomId) {
        this.facilitySizeUomId = facilitySizeUomId;
    }

    public String getFacilityTypeId() {
        return facilityTypeId;
    }

    public void setFacilityTypeId(String facilityTypeId) {
        this.facilityTypeId = facilityTypeId;
    }

    public String getGeoPointId() {
        return geoPointId;
    }

    public void setGeoPointId(String geoPointId) {
        this.geoPointId = geoPointId;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
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

    public String getManufAllocEnable() {
        return manufAllocEnable;
    }

    public void setManufAllocEnable(String manufAllocEnable) {
        this.manufAllocEnable = manufAllocEnable;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getOrganized() {
        return organized;
    }

    public void setOrganized(String organized) {
        this.organized = organized;
    }

    public String getOwnerPartyId() {
        return ownerPartyId;
    }

    public void setOwnerPartyId(String ownerPartyId) {
        this.ownerPartyId = ownerPartyId;
    }

    public String getParentFacilityId() {
        return parentFacilityId;
    }

    public void setParentFacilityId(String parentFacilityId) {
        this.parentFacilityId = parentFacilityId;
    }

    public String getPrimaryFacilityGroupId() {
        return primaryFacilityGroupId;
    }

    public void setPrimaryFacilityGroupId(String primaryFacilityGroupId) {
        this.primaryFacilityGroupId = primaryFacilityGroupId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReserveOrderEnumId() {
        return reserveOrderEnumId;
    }

    public void setReserveOrderEnumId(String reserveOrderEnumId) {
        this.reserveOrderEnumId = reserveOrderEnumId;
    }

    public String getSkipPackInvCheck() {
        return skipPackInvCheck;
    }

    public void setSkipPackInvCheck(String skipPackInvCheck) {
        this.skipPackInvCheck = skipPackInvCheck;
    }

    public String getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(String squareFootage) {
        this.squareFootage = squareFootage;
    }

}
