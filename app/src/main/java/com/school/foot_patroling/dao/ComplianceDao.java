package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.Compliance;

import java.util.List;

@Dao
public interface ComplianceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Compliance compliance);

    @Query("DELETE FROM compliances")
    void deleteAll();

    @Query("SELECT * from compliances")
    List<Compliance> getAllCompliancesDtos();

    @Query("SELECT * FROM compliances WHERE device_seq_id=:cmpStartedTime")
    Compliance getStartedCompliance(String cmpStartedTime);
    @Query("SELECT * FROM compliances WHERE seq_id='null'")
    List<Compliance> getNotSyncedCompliance();
    @Query("SELECT COUNT(device_seq_id) FROM compliances")
    int getCount();
}
