package com.school.foot_patroling.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dao.CreatedFootPatrollingSectionsDtoDao;
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
public abstract class FPDatabase extends RoomDatabase {

    public abstract CreatedFootPatrollingSectionsDtoDao CreatedFootPatrollingSectionsDtoDaoDao();
    public abstract CreatedFunctionalLocationHierarchyDto CreatedFunctionalLocationHierarchyDtoDao();
    public abstract CreatedObservationCategoriesDto CreatedObservationCategoriesDtoDao();
    public abstract CreatedObservationsCheckListDto CreatedObservationsCheckListDtoDao();
    public abstract CreatedResponseFacilityDto CreatedResponseFacilityDtoDao();
    public abstract CreatedResponseInspectionTypeDto CreatedResponseInspectionTypeDtoDao();
    public abstract CreatedResponseOheLocationDto CreatedResponseOheLocationDtoDao();
    public abstract CreatedResponseProductDto CreatedResponseProductDtoDao();
    public abstract CreatedResponseUserLoginDto CreatedResponseUserLoginDtoDao();
    public abstract DeviceAuthModel DeviceAuthModelDao();
    public abstract FacilityDto FacilityDtoDao();
    public abstract FacilityDto_ FacilityDto_Dao();
    public abstract FootPatrollingSectionsDto FootPatrollingSectionsDtoDao();
    public abstract FootPatrollingSectionsDto_ FootPatrollingSectionsDto_Dao();
    public abstract InspectionTypeDto InspectionTypeDtoDao();
    public abstract InspectionTypeDto_ InspectionTypeDto_Dao();
    public abstract MasterDto MasterDtoDao();
    public abstract ObservationCategoriesDto ObservationCategoriesDtoDao();
    public abstract ObservationCategoriesDto_ ObservationCategoriesDto_Dao();
    public abstract ObservationsCheckListDto ObservationsCheckListDtoDao();
    public abstract ObservationsCheckListDto_ ObservationsCheckListDto_Dao();
    public abstract ProductDto ProductDtoDao();
    public abstract ProductDto_ ProductDto_Dao();
    public abstract RegistrationRequestModel RegistrationRequestModelDao();
    public abstract UpdatedObservationCategoriesDto UpdatedObservationCategoriesDtoDao();
    public abstract UpdatedObservationsCheckListDto UpdatedObservationsCheckListDtoDao();
    public abstract UpdatedFootPatrollingSectionsDto UpdatedFootPatrollingSectionsDtoDao();
    public abstract UpdatedFunctionalLocationHierarchyDto UpdatedFunctionalLocationHierarchyDtoDao();
    public abstract UpdatedResponseFacilityDto UpdatedResponseFacilityDtoDao();
    public abstract UpdatedResponseInspectionTypeDto UpdatedResponseInspectionTypeDtoDao();
    public abstract UpdatedResponseOheLocationDto UpdatedResponseOheLocationDtoDao();
    public abstract UpdatedResponseProductDto UpdatedResponseProductDtoDao();
    public abstract UpdatedResponseUserLoginDto UpdatedResponseUserLoginDtoDao();
    public abstract UserLoginDto UserLoginDtoDao();
    public abstract UserLoginDto_ UserLoginDto_Dao();

}
