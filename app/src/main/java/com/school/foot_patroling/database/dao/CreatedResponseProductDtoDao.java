package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedResponseFacilityDto;
import com.school.foot_patroling.register.model.CreatedResponseProductDto;

import java.util.List;

@Dao
public interface CreatedResponseProductDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedResponseProductDto createdResponseProductDto);

    @Query("DELETE FROM ResponseProductDto")
    void deleteAll();

    @Query("SELECT * from ResponseProductDto") //" ORDER BY message_id ASC")
    List<CreatedResponseProductDto> getAllCreatedResponseProductDtos();
}
