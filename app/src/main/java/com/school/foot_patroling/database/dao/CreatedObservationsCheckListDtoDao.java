package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedObservationsCheckListDto;
import com.school.foot_patroling.register.model.UpdatedObservationsCheckListDto;

import java.util.List;

@Dao
public interface CreatedObservationsCheckListDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedObservationsCheckListDto createdObservationsCheckListDto);

    @Query("DELETE FROM ObservationsCheckListDto")
    void deleteAll();

    @Query("SELECT * from ObservationsCheckListDto") //" ORDER BY message_id ASC")
    List<CreatedObservationsCheckListDto> getAllCreatedObservationsCheckListDtos();
}
