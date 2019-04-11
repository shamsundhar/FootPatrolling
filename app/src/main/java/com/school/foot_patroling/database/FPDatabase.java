package com.school.foot_patroling.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.dao.ObservationsCheckListDtoDao;
import com.school.foot_patroling.dao.UserLoginDtoDao;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.UserLoginDto;


@Database(entities = {UserLoginDto.class, ObservationsCheckListDto.class},version = 1)
public abstract class FPDatabase extends RoomDatabase {


    public abstract UserLoginDtoDao userLoginDtoDao();
    public abstract ObservationsCheckListDtoDao observationsCheckListDtoDao();


}
