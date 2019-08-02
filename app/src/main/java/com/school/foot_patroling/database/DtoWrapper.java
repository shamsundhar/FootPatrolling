package com.school.foot_patroling.database;

import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FacilityDto_;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto_;
import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.ProductDto_;
import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto_;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.ArrayList;

public class DtoWrapper {

    public FPDatabase fpDatabase;
    public DtoWrapper(FPDatabase fpDatabase){
        this.fpDatabase = fpDatabase;
    }

    public void updateFacilityData(FacilityDto_ facilityDto){

        FacilityDto facilityDto1 = new FacilityDto();
        facilityDto1.setFacilityTypeId(facilityDto.getFacilityTypeId());
        facilityDto1.setParentFacilityId(facilityDto.getParentFacilityId());
        facilityDto1.setOwnerPartyId(facilityDto.getOwnerPartyId());
        facilityDto1.setDefaultInventoryItemTypeId(facilityDto.getDefaultInventoryItemTypeId());
        facilityDto1.setFacilityName(facilityDto.getFacilityName());
        facilityDto1.setLastUpdatedStamp(facilityDto.getLastUpdatedStamp());
        facilityDto1.setLastUpdatedTxStamp(facilityDto.getLastUpdatedTxStamp());
        facilityDto1.setCreatedStamp(facilityDto.getCreatedStamp());
        facilityDto1.setCreatedTxStamp(facilityDto.getCreatedTxStamp());
        facilityDto1.setFacilityId(facilityDto.getFacilityId());
        facilityDto1.setClosedDate(facilityDto.getClosedDate());
        facilityDto1.setDefaultDaysToShip(facilityDto.getDefaultDaysToShip());
        facilityDto1.setDefaultDimensionUomId(facilityDto.getDefaultDimensionUomId());
        facilityDto1.setDefaultWeightUomId(facilityDto.getDefaultWeightUomId());
        facilityDto1.setDepotType(facilityDto.getDepotType());
        facilityDto1.setDescription(facilityDto.getDescription());
        facilityDto1.setFacilitySize(facilityDto.getFacilitySize());
        facilityDto1.setFacilitySizeUomId(facilityDto.getFacilitySizeUomId());
        facilityDto1.setGeoPointId(facilityDto.getGeoPointId());
        facilityDto1.setIsDisable(facilityDto.getIsDisable());
        facilityDto1.setManufAllocEnable(facilityDto.getManufAllocEnable());
        facilityDto1.setOpenedDate(facilityDto.getOpenedDate());
        facilityDto1.setOrganized(facilityDto.getOrganized());
        facilityDto1.setPrimaryFacilityGroupId(facilityDto.getPrimaryFacilityGroupId());
        facilityDto1.setProductStoreId(facilityDto.getProductStoreId());
        facilityDto1.setRemarks(facilityDto.getRemarks());
        facilityDto1.setReserveOrderEnumId(facilityDto.getReserveOrderEnumId());
        facilityDto1.setSkipPackInvCheck(facilityDto.getSkipPackInvCheck());
        facilityDto1.setSquareFootage(facilityDto.getSquareFootage());
        facilityDto1.setParentDepot(facilityDto.getParentDepot());
        facilityDto1.setDivision(facilityDto.getDivision());
        facilityDto1.setSubDivision(facilityDto.getSubDivision());

        if(fpDatabase != null){
            fpDatabase.facilityDtoDao().insert(facilityDto1);
        }
    }

    public void updateUserLoginData(UserLoginDto_ userLoginDto) {

        UserLoginDto userLoginDto1 = new UserLoginDto();

        userLoginDto1.setUserLoginId(userLoginDto.getUserLoginId());
        userLoginDto1.setCurrentPassword(userLoginDto.getCurrentPassword());
        userLoginDto1.setPasswordHint(userLoginDto.getPasswordHint());
        userLoginDto1.setIsSystem(userLoginDto.getIsSystem());
        userLoginDto1.setEnabled(userLoginDto.getEnabled());

        userLoginDto1.setLastUpdatedStamp(userLoginDto.getLastUpdatedStamp());
        userLoginDto1.setLastUpdatedTxStamp(userLoginDto.getLastUpdatedTxStamp());
        userLoginDto1.setCreatedStamp(userLoginDto.getCreatedStamp());
        userLoginDto1.setCreatedTxStamp(userLoginDto.getCreatedTxStamp());

        fpDatabase.userLoginDtoDao().insert(userLoginDto1);
    }

    public void updateProductData(ProductDto_ productDto) {
        ProductDto productDto1 = new ProductDto();

        productDto1.setProductTypeId(productDto.getProductTypeId());
        productDto1.setInternalName(productDto.getInternalName());
        productDto1.setProductName(productDto.getProductName());
        productDto1.setBillOfMaterialLevel(productDto.getBillOfMaterialLevel());
        productDto1.setIsActive(productDto.getIsActive());
        productDto1.setProductCodeTypeId(productDto.getProductCodeTypeId());
        productDto1.setPlNo(productDto.getPlNo());
        productDto1.setRlyId(productDto.getRlyId());
        productDto1.setTrdDivId(productDto.getTrdDivId());
        productDto1.setLastUpdatedStamp(productDto.getLastUpdatedStamp());
        productDto1.setLastUpdatedTxStamp(productDto.getLastUpdatedTxStamp());
        productDto1.setCreatedStamp(productDto.getCreatedStamp());
        productDto1.setCreatedTxStamp(productDto.getCreatedTxStamp());
        productDto1.setProductId(productDto.getProductId());
        
        fpDatabase.productDtoDao().insert(productDto1);
    }
    public void updateSections(FootPatrollingSectionsDto_ sectionsDto){
        FootPatrollingSectionsDto footPatrollingSectionsDto = new FootPatrollingSectionsDto();
        footPatrollingSectionsDto.setFacilityDepot(sectionsDto.getFacilityDepot());
        footPatrollingSectionsDto.setFpSection(sectionsDto.getFpSection());
        footPatrollingSectionsDto.setFromDate(sectionsDto.getFromDate());
        footPatrollingSectionsDto.setFromLocation(sectionsDto.getFromLocation());
        footPatrollingSectionsDto.setRemarks(sectionsDto.getRemarks());
        footPatrollingSectionsDto.setToLocation(sectionsDto.getToLocation());
        footPatrollingSectionsDto.setToDate(sectionsDto.getToDate());
        footPatrollingSectionsDto.setSeqId(sectionsDto.getSeqId());

        fpDatabase.footPatrollingSectionsDao().insert(footPatrollingSectionsDto);
    }
}
