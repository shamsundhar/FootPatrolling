package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedResponseInspectionTypeDto;
import com.school.foot_patroling.register.model.FacilityDto;

import java.util.List;

@Dao
public interface CreatedResponseInspectionTypeDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedResponseInspectionTypeDto createdResponseInspectionTypeDto);

    @Query("DELETE FROM ResponseInspectionTypeDto")
    void deleteAll();

    @Query("SELECT * from ResponseInspectionTypeDto") //" ORDER BY message_id ASC")
    List<CreatedResponseInspectionTypeDto> getAllCreatedResponseInspectionTypeDtos();
}
