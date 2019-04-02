package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UserLoginDto;
import com.school.foot_patroling.register.model.UserLoginDto_;

import java.util.List;

@Dao
public interface UserLoginDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLoginDto userLoginDto);

    @Query("DELETE FROM UserLoginDto")
    void deleteAll();

    @Query("SELECT * from UserLoginDto")
    List<UserLoginDto> getAllUserLoginDtos();
}
