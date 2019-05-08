package com.school.foot_patroling.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.Inspection;

import java.util.List;

@Dao
public interface FootPatrollingSectionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FootPatrollingSectionsDto footPatrollingSectionsDto);

    @Query("DELETE FROM foot_patrolling_sections")
    void deleteAll();

    @Query("SELECT * from foot_patrolling_sections")
    List<FootPatrollingSectionsDto> getAllFootPatrollingSectionDtos();

    @Query("SELECT * from foot_patrolling_sections WHERE facility_depot=:depotID")
    List<FootPatrollingSectionsDto> getAllFootPatrollingSectionDtosByDepot(String depotID);
}
