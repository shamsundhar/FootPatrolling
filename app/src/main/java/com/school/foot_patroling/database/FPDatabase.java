package com.school.foot_patroling.database;

import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.school.foot_patroling.dao.ComplianceDao;
import com.school.foot_patroling.dao.FacilityDtoDao;
import com.school.foot_patroling.dao.FootPatrollingSectionsDao;
import com.school.foot_patroling.dao.FpMovementDao;
import com.school.foot_patroling.dao.InspectionDao;
import com.school.foot_patroling.dao.ObservationCategoriesDtoDao;
import com.school.foot_patroling.dao.ObservationDao;
import com.school.foot_patroling.dao.ObservationsCheckListDtoDao;
import com.school.foot_patroling.dao.OheLocationDtoDao;
import com.school.foot_patroling.dao.ProductDtoDao;
import com.school.foot_patroling.dao.UserLoginDtoDao;
import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.FacilityDto;
import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.FpMovementDto;
import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.Observation;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.OheLocationDto;
import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.UserLoginDto;


@Database(entities = {UserLoginDto.class, ObservationsCheckListDto.class,
        FacilityDto.class, ProductDto.class, Inspection.class, FootPatrollingSectionsDto.class,
        ObservationCategoriesDto.class, Observation.class, Compliance.class, FpMovementDto.class, OheLocationDto.class}, version = 1)
public abstract class FPDatabase extends RoomDatabase {


    public abstract UserLoginDtoDao userLoginDtoDao();
    public abstract ObservationsCheckListDtoDao observationsCheckListDtoDao();
    public abstract FacilityDtoDao facilityDtoDao();
    public abstract ProductDtoDao productDtoDao();
    public abstract InspectionDao inspectionDao();
    public abstract FootPatrollingSectionsDao footPatrollingSectionsDao();
    public abstract ObservationCategoriesDtoDao observationCategoriesDtoDao();
    public abstract ObservationDao observationDao();
    public abstract FpMovementDao movementDao();
    public abstract ComplianceDao complianceDao();
    public abstract OheLocationDtoDao oheLocationDtoDao();

    public void clearDBTables(){
        userLoginDtoDao().deleteAll();
        observationsCheckListDtoDao().deleteAll();
        facilityDtoDao().deleteAll();
        productDtoDao().deleteAll();
        inspectionDao().deleteAll();
        footPatrollingSectionsDao().deleteAll();
        observationCategoriesDtoDao().deleteAll();
        observationDao().deleteAll();
        complianceDao().deleteAll();
        movementDao().deleteAll();
    }

}
