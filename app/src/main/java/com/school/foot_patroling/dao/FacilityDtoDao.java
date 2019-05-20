package com.school.foot_patroling.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
}
