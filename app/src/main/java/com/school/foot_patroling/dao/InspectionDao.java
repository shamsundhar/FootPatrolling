package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.school.foot_patroling.register.model.Inspection;

import java.util.List;

@Dao
public interface InspectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Inspection inspection);

    @Query("DELETE FROM inspection")
    void deleteAll();

    @Query("SELECT * from inspection")
    List<Inspection> getAllInspectionDtos();
    @Query("SELECT * FROM inspection WHERE device_seq_id=:fpStartedTime")
    Inspection getStartedInspection(String fpStartedTime);
    @Query("SELECT * FROM inspection WHERE seq_id='null'")
    List<Inspection> getNotSyncedInspection();
    @Query("SELECT COUNT(device_seq_id) FROM inspection")
    int getCount();
}
