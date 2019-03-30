package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedResponseInspectionTypeDto;
import com.school.foot_patroling.register.model.CreatedResponseOheLocationDto;

import java.util.List;

@Dao
public interface CreatedResponseOheLocationDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedResponseOheLocationDto createdResponseOheLocationDto);

    @Query("DELETE FROM ResponseOheLocationDto")
    void deleteAll();

    @Query("SELECT * from ResponseOheLocationDto") //" ORDER BY message_id ASC")
    List<CreatedResponseOheLocationDto> getAllCreatedResponseOheLocationDtos();
}
