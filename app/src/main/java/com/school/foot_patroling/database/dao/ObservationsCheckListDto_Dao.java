package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.ObservationsCheckListDto;
import com.school.foot_patroling.register.model.ObservationsCheckListDto_;

import java.util.List;

@Dao
public interface ObservationsCheckListDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationsCheckListDto_ observationsCheckListDto_);

    @Query("DELETE FROM ObservationsCheckListDto_")
    void deleteAll();

    @Query("SELECT * from ObservationsCheckListDto_") //" ORDER BY message_id ASC")
    List<ObservationsCheckListDto_> getAllObservationsCheckListDto_s();
}
