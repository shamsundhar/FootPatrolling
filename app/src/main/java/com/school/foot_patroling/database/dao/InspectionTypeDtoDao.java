package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.InspectionTypeDto;
import com.school.foot_patroling.register.model.UpdatedObservationCategoriesDto;

import java.util.List;

@Dao
public interface InspectionTypeDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InspectionTypeDto inspectionTypeDto);

    @Query("DELETE FROM InspectionTypeDto")
    void deleteAll();

    @Query("SELECT * from InspectionTypeDto") //" ORDER BY message_id ASC")
    List<InspectionTypeDto> getAllInspectionTypeDtos();
}
