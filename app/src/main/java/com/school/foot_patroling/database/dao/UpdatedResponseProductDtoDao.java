package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedResponseProductDto;

import java.util.List;

@Dao
public interface UpdatedResponseProductDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedResponseProductDto updatedResponseProductDto);

    @Query("DELETE FROM UpdatedResponseProductDto")
    void deleteAll();

    @Query("SELECT * from UpdatedResponseProductDto") //" ORDER BY message_id ASC")
    List<UpdatedResponseProductDto> getAllUpdatedResponseProductDtos();
}
