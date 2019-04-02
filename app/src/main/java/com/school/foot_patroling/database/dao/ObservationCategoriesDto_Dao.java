package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.ObservationCategoriesDto;
import com.school.foot_patroling.register.model.ObservationCategoriesDto_;

import java.util.List;

@Dao
public interface ObservationCategoriesDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationCategoriesDto_ observationCategoriesDto_);

    @Query("DELETE FROM ObservationCategoriesDto_")
    void deleteAll();

    @Query("SELECT * from ObservationCategoriesDto_") //" ORDER BY message_id ASC")
    List<ObservationCategoriesDto_> getAllObservationCategoriesDto_s();
}
