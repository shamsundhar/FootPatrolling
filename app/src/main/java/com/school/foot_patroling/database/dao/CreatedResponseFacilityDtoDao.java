package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedObservationCategoriesDto;
import com.school.foot_patroling.register.model.CreatedResponseFacilityDto;

import java.util.List;

@Dao
public interface CreatedResponseFacilityDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedResponseFacilityDto createdResponseFacilityDto);

    @Query("DELETE FROM ResponseFacilityDto")
    void deleteAll();

    @Query("SELECT * from ResponseFacilityDto") //" ORDER BY message_id ASC")
    List<CreatedResponseFacilityDto> getAllCreatedResponseFacilityDtos();
}
