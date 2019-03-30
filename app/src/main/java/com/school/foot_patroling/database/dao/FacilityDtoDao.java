package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.FacilityDto;

import java.util.List;

@Dao
public interface FacilityDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FacilityDto facilityDto);

    @Query("DELETE FROM FacilityDto")
    void deleteAll();

    @Query("SELECT * from FacilityDto") //" ORDER BY message_id ASC")
    List<FacilityDto> getAllFacilityDtos();
}
