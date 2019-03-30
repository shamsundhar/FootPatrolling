package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedResponseInspectionTypeDto;
import com.school.foot_patroling.register.model.UpdatedResponseOheLocationDto;

import java.util.List;

@Dao
public interface UpdatedResponseInspectionTypeDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedResponseInspectionTypeDto updatedResponseInspectionTypeDto);

    @Query("DELETE FROM UpdatedResponseInspectionTypeDto")
    void deleteAll();

    @Query("SELECT * from UpdatedResponseInspectionTypeDto") //" ORDER BY message_id ASC")
    List<UpdatedResponseInspectionTypeDto> getAllUpdatedResponseInspectionTypeDtos();
}
