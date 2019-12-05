package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.FacilityDto;

import java.util.List;

@Dao
public interface FacilityDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FacilityDto facilityDto);

    @Query("DELETE FROM facility")
    void deleteAll();

    @Query("SELECT * from facility")
    List<FacilityDto> getAllFacilityDtos();

    @Query("SELECT * FROM facility WHERE depotType='OHE'")
    List<FacilityDto> getOHEFacilityDtos();
    @Query("SELECT COUNT(depotType) FROM facility")
    int getCount();
    @Query("SELECT DISTINCT(sub_division) FROM facility")
    List<String> getSubDivisions();
    @Query("SELECT * FROM facility WHERE depotType='OHE' AND sub_division=:subDivision")
    List<FacilityDto> getDepotsFromSubDivision(String subDivision);
}
