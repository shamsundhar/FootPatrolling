package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedResponseUserLoginDto;
import com.school.foot_patroling.register.model.FacilityDto_;

import java.util.List;

@Dao
public interface FacilityDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FacilityDto_ facilityDto_);

    @Query("DELETE FROM FacilityDto_")
    void deleteAll();

    @Query("SELECT * from FacilityDto_") //" ORDER BY message_id ASC")
    List<FacilityDto_> getAllFacilityDto_s();
}
