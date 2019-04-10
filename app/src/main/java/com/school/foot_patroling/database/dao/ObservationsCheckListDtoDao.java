package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedObservationsCheckListDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.ProductDto_;

import java.util.List;

@Dao
public interface ObservationsCheckListDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationsCheckListDto observationsCheckListDto);

    @Query("DELETE FROM ObservationsCheckListDto_2")
    void deleteAll();

    @Query("SELECT * from ObservationsCheckListDto_2") //" ORDER BY message_id ASC")
    List<ObservationsCheckListDto> getAllObservationsCheckListDtos();
}
