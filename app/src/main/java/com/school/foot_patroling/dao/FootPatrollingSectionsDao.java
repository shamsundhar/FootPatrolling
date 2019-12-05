package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.FootPatrollingSectionsDto;

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
    @Query("SELECT COUNT(facility_depot) FROM foot_patrolling_sections")
    int getCount();
}
