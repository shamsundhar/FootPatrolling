package com.school.foot_patroling.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

@Database(entities = {},version = 1)
public abstract class FPDatabase extends RoomDatabase {

    //TODO Dao classes getDaos

}
