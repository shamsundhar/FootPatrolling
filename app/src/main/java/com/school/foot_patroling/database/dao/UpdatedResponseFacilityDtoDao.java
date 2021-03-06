package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedResponseFacilityDto;
import com.school.foot_patroling.register.model.UpdatedResponseInspectionTypeDto;

import java.util.List;
@Dao
public interface UpdatedResponseFacilityDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedResponseFacilityDto updatedResponseFacilityDto);

    @Query("DELETE FROM UpdatedResponseFacilityDto")
    void deleteAll();

    @Query("SELECT * from UpdatedResponseFacilityDto") //" ORDER BY message_id ASC")
    List<UpdatedResponseFacilityDto> getAllUpdatedResponseFacilityDtos();
}
