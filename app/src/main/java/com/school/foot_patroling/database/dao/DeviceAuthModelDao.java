package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.DeviceAuthModel;
import com.school.foot_patroling.register.model.FacilityDto_;

import java.util.List;

@Dao
public interface DeviceAuthModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DeviceAuthModel deviceAuthModel);

    @Query("DELETE FROM DeviceAuthModel")
    void deleteAll();

    @Query("SELECT * from DeviceAuthModel") //" ORDER BY message_id ASC")
    List<DeviceAuthModel> getAllDeviceAuthModels();
}
