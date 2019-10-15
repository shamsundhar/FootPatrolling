package com.school.foot_patroling.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.Inspection;
import com.school.foot_patroling.register.model.Observation;

import java.util.List;

@Dao
public interface ObservationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Observation observation);

    @Query("DELETE FROM observations")
    void deleteAll();

    @Query("SELECT * from observations ORDER BY seq_id DESC")
    List<Observation> getAllObservationDtos();
    @Query("SELECT * FROM observations WHERE device_seq_id=:fpStartedTime")
    Observation getStartedObservation(String fpStartedTime);
    @Query("SELECT * FROM observations WHERE seq_id='null'")
    List<Observation> getNotSyncedObservation();
    @Query("SELECT COUNT(device_seq_id) FROM observations")
    int getCount();
}
