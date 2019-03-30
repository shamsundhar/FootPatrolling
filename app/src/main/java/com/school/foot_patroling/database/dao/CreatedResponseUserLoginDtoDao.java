package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedResponseProductDto;
import com.school.foot_patroling.register.model.CreatedResponseUserLoginDto;

import java.util.List;

@Dao
public interface CreatedResponseUserLoginDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedResponseUserLoginDto createdResponseUserLoginDto);

    @Query("DELETE FROM ResponseUserLoginDto")
    void deleteAll();

    @Query("SELECT * from ResponseUserLoginDto") //" ORDER BY message_id ASC")
    List<CreatedResponseUserLoginDto> getAllCreatedResponseUserLoginDtos();
}
