package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UserLoginDto_;

import java.util.List;
@Dao
public interface UserLoginDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserLoginDto_ userLoginDto_);

    @Query("DELETE FROM UserLoginDto_")
    void deleteAll();

    @Query("SELECT * from UserLoginDto_")
    List<UserLoginDto_> getAllUserLoginDto_s();
}
