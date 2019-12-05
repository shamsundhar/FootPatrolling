package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.ObservationsCheckListDto;

import java.util.List;

@Dao
public interface ObservationsCheckListDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationsCheckListDto observationsCheckListDto);

    @Query("DELETE FROM ObservationsCheckListDto")
    void deleteAll();

    @Query("SELECT * from ObservationsCheckListDto") //" ORDER BY message_id ASC")
    List<ObservationsCheckListDto> getAllObservationsCheckListDtos();
    @Query("SELECT * FROM ObservationsCheckListDto WHERE observationCategory=:category")
    List<ObservationsCheckListDto> getAllObservationsCheckListDtosFromCategory(String category);
    @Query("SELECT COUNT(observationCategory) FROM ObservationsCheckListDto")
    int getCount();
}
