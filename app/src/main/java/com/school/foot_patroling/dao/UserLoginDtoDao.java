package com.school.foot_patroling.dao;

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

    @Query("DELETE FROM user_login")
    void deleteAll();

    @Query("SELECT * from user_login")
    List<UserLoginDto> getAllUserLoginDtos();

    @Query("SELECT * from user_login WHERE user_login_id=:userName")
    UserLoginDto getUserByUnamePassword(String userName);
}
