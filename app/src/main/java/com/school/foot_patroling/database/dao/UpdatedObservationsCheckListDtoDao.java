package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.UpdatedObservationsCheckListDto;

import java.util.List;

@Dao
public interface UpdatedObservationsCheckListDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedObservationsCheckListDto updatedObservationsCheckListDto);

    @Query("DELETE FROM UpdatedObservationsCheckListDto")
    void deleteAll();

    @Query("SELECT * from UpdatedObservationsCheckListDto") //" ORDER BY message_id ASC")
    List<UpdatedObservationsCheckListDto> getAllUpdatedObservationsCheckListDtos();
}
