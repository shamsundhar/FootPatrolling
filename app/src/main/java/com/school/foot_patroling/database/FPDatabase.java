package com.school.foot_patroling.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.dao.FacilityDtoDao;
import com.school.foot_patroling.dao.FootPatrollingSectionsDao;
import com.school.foot_patroling.dao.InspectionDao;
import com.school.foot_patroling.dao.ObservationCategoriesDtoDao;
import com.school.foot_patroling.dao.ObservationDao;
import com.school.foot_patroling.dao.ObservationsCheckListDtoDao;
import com.school.foot_patroling.dao.ProductDtoDao;
import com.school.foot_patroling.dao.UserLoginDtoDao;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.UserLoginDto;


@Database(entities = {UserLoginDto.class, ObservationsCheckListDto.class,
        FacilityDto.class, ProductDto.class, Inspection.class, FootPatrollingSectionsDto.class,
        ObservationCategoriesDto.class, Observation.class}, version = 1)
public abstract class FPDatabase extends RoomDatabase {


    public abstract UserLoginDtoDao userLoginDtoDao();
    public abstract ObservationsCheckListDtoDao observationsCheckListDtoDao();
    public abstract FacilityDtoDao facilityDtoDao();
    public abstract ProductDtoDao productDtoDao();
    public abstract InspectionDao inspectionDao();
    public abstract FootPatrollingSectionsDao footPatrollingSectionsDao();
    public abstract ObservationCategoriesDtoDao observationCategoriesDtoDao();
    public abstract ObservationDao observationDao();


}
