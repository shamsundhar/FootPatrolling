package com.school.foot_patroling.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dao.CreatedFootPatrollingSectionsDtoDao;
import com.school.foot_patroling.database.dao.CreatedFunctionalLocationHierarchyDtoDao;
import com.school.foot_patroling.database.dao.CreatedObservationCategoriesDtoDao;
import com.school.foot_patroling.database.dao.CreatedObservationsCheckListDtoDao;
import com.school.foot_patroling.database.dao.CreatedResponseFacilityDtoDao;
import com.school.foot_patroling.database.dao.CreatedResponseInspectionTypeDtoDao;
import com.school.foot_patroling.database.dao.CreatedResponseOheLocationDtoDao;
import com.school.foot_patroling.database.dao.CreatedResponseProductDtoDao;
import com.school.foot_patroling.database.dao.CreatedResponseUserLoginDtoDao;
import com.school.foot_patroling.database.dao.DeviceAuthModelDao;
import com.school.foot_patroling.database.dao.FacilityDtoDao;
import com.school.foot_patroling.database.dao.FacilityDto_Dao;
import com.school.foot_patroling.database.dao.FootPatrollingSectionsDtoDao;
import com.school.foot_patroling.database.dao.FootPatrollingSectionsDto_Dao;
import com.school.foot_patroling.database.dao.InspectionTypeDtoDao;
import com.school.foot_patroling.database.dao.InspectionTypeDto_Dao;
import com.school.foot_patroling.database.dao.MasterDtoDao;
import com.school.foot_patroling.database.dao.ObservationCategoriesDtoDao;
import com.school.foot_patroling.database.dao.ObservationCategoriesDto_Dao;
import com.school.foot_patroling.database.dao.ObservationsCheckListDtoDao;
import com.school.foot_patroling.database.dao.ObservationsCheckListDto_Dao;
import com.school.foot_patroling.database.dao.ProductDtoDao;
import com.school.foot_patroling.database.dao.ProductDto_Dao;
import com.school.foot_patroling.database.dao.RegistrationRequestModelDao;
import com.school.foot_patroling.database.dao.UpdatedFootPatrollingSectionsDtoDao;
import com.school.foot_patroling.database.dao.UpdatedFunctionalLocationHierarchyDtoDao;
import com.school.foot_patroling.database.dao.UpdatedObservationCategoriesDtoDao;
import com.school.foot_patroling.database.dao.UpdatedObservationsCheckListDtoDao;
import com.school.foot_patroling.database.dao.UpdatedResponseFacilityDtoDao;
import com.school.foot_patroling.database.dao.UpdatedResponseInspectionTypeDtoDao;
import com.school.foot_patroling.database.dao.UpdatedResponseOheLocationDtoDao;
import com.school.foot_patroling.database.dao.UpdatedResponseProductDtoDao;
import com.school.foot_patroling.database.dao.UpdatedResponseUserLoginDtoDao;
import com.school.foot_patroling.database.dao.UserLoginDtoDao;
import com.school.foot_patroling.database.dao.UserLoginDto_Dao;
import com.school.foot_patroling.database.dbconverters.FacilityDtoConverter;
import com.school.foot_patroling.database.dbconverters.FacilityDto_Converter;
import com.school.foot_patroling.database.dbconverters.FootPatrollingSectionsDtoConverter;
import com.school.foot_patroling.database.dbconverters.FootPatrollingSectionsDto_Converter;
import com.school.foot_patroling.database.dbconverters.InspectionTypeDtoConverter;
import com.school.foot_patroling.database.dbconverters.InspectionTypeDto_Converter;
import com.school.foot_patroling.database.dbconverters.ObservationCategoriesDtoConverter;
import com.school.foot_patroling.database.dbconverters.ObservationCategoriesDto_Converter;
import com.school.foot_patroling.database.dbconverters.ObservationsCheckListDtoConverter;
import com.school.foot_patroling.database.dbconverters.ObservationsCheckListDto_Converter;
import com.school.foot_patroling.database.dbconverters.ProductDtoConverter;
import com.school.foot_patroling.database.dbconverters.ProductDto_Converter;
import com.school.foot_patroling.database.dbconverters.UserLoginDtoConverter;
import com.school.foot_patroling.database.dbconverters.UserLoginDto_Converter;
import com.school.foot_patroling.register.model.CreatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.CreatedFunctionalLocationHierarchyDto;
import com.school.foot_patroling.register.model.CreatedObservationCategoriesDto;
import com.school.foot_patroling.register.model.CreatedObservationsCheckListDto;
import com.school.foot_patroling.register.model.CreatedResponseFacilityDto;
import com.school.foot_patroling.register.model.CreatedResponseInspectionTypeDto;
import com.school.foot_patroling.register.model.CreatedResponseOheLocationDto;
import com.school.foot_patroling.register.model.CreatedResponseProductDto;
import com.school.foot_patroling.register.model.CreatedResponseUserLoginDto;
import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FacilityDto_;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto_;
import com.school.foot_patroling.register.model.InspectionTypeDto;
import com.school.foot_patroling.register.model.InspectionTypeDto_;
import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationCategoriesDto_;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto_;
import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.ProductDto_;
import com.school.foot_patroling.register.model.RegistrationRequestModel;
import com.school.foot_patroling.register.model.UpdatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.UpdatedFunctionalLocationHierarchyDto;
import com.school.foot_patroling.register.model.UpdatedObservationCategoriesDto;
import com.school.foot_patroling.register.model.UpdatedObservationsCheckListDto;
import com.school.foot_patroling.register.model.UpdatedResponseFacilityDto;
import com.school.foot_patroling.register.model.UpdatedResponseInspectionTypeDto;
import com.school.foot_patroling.register.model.UpdatedResponseOheLocationDto;
import com.school.foot_patroling.register.model.UpdatedResponseProductDto;
import com.school.foot_patroling.register.model.UpdatedResponseUserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto_;

@Database(entities = {CreatedFootPatrollingSectionsDto.class,
        CreatedFunctionalLocationHierarchyDto.class,
        CreatedObservationCategoriesDto.class,
        CreatedObservationsCheckListDto.class,
        CreatedResponseFacilityDto.class,
        CreatedResponseInspectionTypeDto.class,
        CreatedResponseOheLocationDto.class,
        CreatedResponseProductDto.class,
        CreatedResponseUserLoginDto.class,
        DeviceAuthModel.class,
        FacilityDto.class,
        FacilityDto_.class,
        FootPatrollingSectionsDto.class,
        FootPatrollingSectionsDto_.class,
        InspectionTypeDto.class,
        InspectionTypeDto_.class,
        MasterDto.class,
        ObservationCategoriesDto.class,
        ObservationCategoriesDto_.class,
        ObservationsCheckListDto.class,
        ObservationsCheckListDto_.class,
        ProductDto.class,
        ProductDto_.class,
        RegistrationRequestModel.class,
        UpdatedObservationCategoriesDto.class,
        UpdatedObservationsCheckListDto.class,
        UpdatedFootPatrollingSectionsDto.class,
        UpdatedFunctionalLocationHierarchyDto.class,
        UpdatedResponseFacilityDto.class,
        UpdatedResponseInspectionTypeDto.class,
        UpdatedResponseOheLocationDto.class,
        UpdatedResponseProductDto.class,
        UpdatedResponseUserLoginDto.class,
        UserLoginDto.class,
        UserLoginDto_.class},version = 1)
//@TypeConverters({ObservationCategoriesDtoConverter.class, FacilityDto_Converter.class, FacilityDtoConverter.class,
//        FootPatrollingSectionsDto_Converter.class, FootPatrollingSectionsDtoConverter.class,
//        InspectionTypeDtoConverter.class, InspectionTypeDto_Converter.class,
//        ObservationCategoriesDtoConverter.class, ObservationCategoriesDto_Converter.class,
//        ObservationsCheckListDto_Converter.class, ObservationsCheckListDtoConverter.class,
//        ProductDto_Converter.class, ProductDtoConverter.class,
//        UserLoginDto_Converter.class, UserLoginDtoConverter.class})
public abstract class FPDatabase extends RoomDatabase {

    public abstract CreatedFootPatrollingSectionsDtoDao createdFootPatrollingSectionsDtoDaoDao();
    public abstract CreatedFunctionalLocationHierarchyDtoDao createdFunctionalLocationHierarchyDtoDao();
    public abstract CreatedObservationCategoriesDtoDao createdObservationCategoriesDtoDao();
    public abstract CreatedObservationsCheckListDtoDao createdObservationsCheckListDtoDao();
    public abstract CreatedResponseFacilityDtoDao createdResponseFacilityDtoDao();
    public abstract CreatedResponseInspectionTypeDtoDao createdResponseInspectionTypeDtoDao();
    public abstract CreatedResponseOheLocationDtoDao createdResponseOheLocationDtoDao();
    public abstract CreatedResponseProductDtoDao createdResponseProductDtoDao();
    public abstract CreatedResponseUserLoginDtoDao createdResponseUserLoginDtoDao();
    public abstract DeviceAuthModelDao deviceAuthModelDao();
    public abstract FacilityDtoDao facilityDtoDao();
    public abstract FacilityDto_Dao facilityDto_Dao();
    public abstract FootPatrollingSectionsDtoDao footPatrollingSectionsDtoDao();
    public abstract FootPatrollingSectionsDto_Dao footPatrollingSectionsDto_Dao();
    public abstract InspectionTypeDtoDao inspectionTypeDtoDao();
    public abstract InspectionTypeDto_Dao inspectionTypeDto_Dao();
    public abstract MasterDtoDao masterDtoDao();
    public abstract ObservationCategoriesDtoDao observationCategoriesDtoDao();
    public abstract ObservationCategoriesDto_Dao observationCategoriesDto_Dao();
    public abstract ObservationsCheckListDtoDao observationsCheckListDtoDao();
    public abstract ObservationsCheckListDto_Dao observationsCheckListDto_Dao();
    public abstract ProductDtoDao productDtoDao();
    public abstract ProductDto_Dao productDto_Dao();
    public abstract RegistrationRequestModelDao registrationRequestModelDao();
    public abstract UpdatedObservationCategoriesDtoDao updatedObservationCategoriesDtoDao();
    public abstract UpdatedObservationsCheckListDtoDao updatedObservationsCheckListDtoDao();
    public abstract UpdatedFootPatrollingSectionsDtoDao updatedFootPatrollingSectionsDtoDao();
    public abstract UpdatedFunctionalLocationHierarchyDtoDao updatedFunctionalLocationHierarchyDtoDao();
    public abstract UpdatedResponseFacilityDtoDao updatedResponseFacilityDtoDao();
    public abstract UpdatedResponseInspectionTypeDtoDao updatedResponseInspectionTypeDtoDao();
    public abstract UpdatedResponseOheLocationDtoDao updatedResponseOheLocationDtoDao();
    public abstract UpdatedResponseProductDtoDao updatedResponseProductDtoDao();
    public abstract UpdatedResponseUserLoginDtoDao updatedResponseUserLoginDtoDao();
    public abstract UserLoginDtoDao userLoginDtoDao();
    public abstract UserLoginDto_Dao userLoginDto_Dao();

}
