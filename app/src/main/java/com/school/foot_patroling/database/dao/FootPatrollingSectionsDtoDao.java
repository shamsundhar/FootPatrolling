package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.InspectionTypeDto_;

import java.util.List;

@Dao
public interface FootPatrollingSectionsDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FootPatrollingSectionsDto footPatrollingSectionsDto);

    @Query("DELETE FROM FootPatrollingSections")
    void deleteAll();

    @Query("SELECT * from FootPatrollingSections") //" ORDER BY message_id ASC")
    List<FootPatrollingSectionsDto> getAllFootPatrollingSectionsDtos();
}
