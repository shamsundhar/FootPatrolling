package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.ObservationCategoriesDto_;

import java.util.List;

@Dao
public interface MasterDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MasterDto masterDto);

    @Query("DELETE FROM MasterDto")
    void deleteAll();

    @Query("SELECT * from MasterDto") //" ORDER BY message_id ASC")
    List<MasterDto> getAllMasterDtos();
}
