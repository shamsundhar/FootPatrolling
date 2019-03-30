package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.FootPatrollingSectionsDto_;
import com.school.foot_patroling.register.model.InspectionTypeDto_;

import java.util.List;

@Dao
public interface FootPatrollingSectionsDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FootPatrollingSectionsDto_ footPatrollingSectionsDto_);

    @Query("DELETE FROM FootPatrollingSectionsDto_")
    void deleteAll();

    @Query("SELECT * from FootPatrollingSectionsDto_") //" ORDER BY message_id ASC")
    List<FootPatrollingSectionsDto_> getAllFootPatrollingSectionsDto_s();
}
