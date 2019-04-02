package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto_;

import java.util.List;

@Dao
public interface ObservationCategoriesDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationCategoriesDto observationCategoriesDto);

    @Query("DELETE FROM ObservationCategoriesDto")
    void deleteAll();

    @Query("SELECT * from ObservationCategoriesDto") //" ORDER BY message_id ASC")
    List<ObservationCategoriesDto> getAllObservationCategoriesDtos();
}
