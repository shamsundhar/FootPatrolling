package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.Observation;

import java.util.List;

@Dao
public interface ObservationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Observation observation);

    @Query("DELETE FROM observations")
    void deleteAll();

    @Query("SELECT * from observations ORDER BY last_updated_stamp DESC")
    List<Observation> getAllObservationDtos();
    @Query("SELECT * FROM observations WHERE device_seq_id=:fpStartedTime")
    Observation getStartedObservation(String fpStartedTime);
    @Query("SELECT * FROM observations WHERE seq_id='null'")
    List<Observation> getNotSyncedObservation();
    @Query("SELECT COUNT(device_seq_id) FROM observations")
    int getCount();
}

