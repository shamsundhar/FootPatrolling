package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedResponseUserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto;

import java.util.List;

@Dao
public interface UpdatedResponseUserLoginDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedResponseUserLoginDto updatedResponseUserLoginDto);

    @Query("DELETE FROM UpdatedResponseUserLoginDto")
    void deleteAll();

    @Query("SELECT * from UpdatedResponseUserLoginDto")
    List<UpdatedResponseUserLoginDto> getAllUpdatedResponseUserLoginDtos();
}
