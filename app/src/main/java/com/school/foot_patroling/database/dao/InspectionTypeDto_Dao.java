package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.InspectionTypeDto;
import com.school.foot_patroling.register.model.InspectionTypeDto_;

import java.util.List;

@Dao
public interface InspectionTypeDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InspectionTypeDto_ inspectionTypeDto);

    @Query("DELETE FROM InspectionTypeDto_")
    void deleteAll();

    @Query("SELECT * from InspectionTypeDto_") //" ORDER BY message_id ASC")
    List<InspectionTypeDto_> getAllInspectionTypeDto_s();
}
