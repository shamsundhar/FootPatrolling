package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.UserLoginDto;

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
    @Query("SELECT COUNT(user_login_id  ) FROM user_login")
    int getCount();
}
