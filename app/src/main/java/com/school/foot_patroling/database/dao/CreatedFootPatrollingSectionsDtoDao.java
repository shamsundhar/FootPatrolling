package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.ProductDto;

import java.util.List;

@Dao
public interface CreatedFootPatrollingSectionsDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedFootPatrollingSectionsDto createdFootPatrollingSectionsDto);

    @Query("DELETE FROM createdfootpatrollingsections")
    void deleteAll();

    @Query("SELECT * from createdfootpatrollingsections") //" ORDER BY message_id ASC")
    List<CreatedFootPatrollingSectionsDto> getAllCreatedfootpatrollingsections();

}
