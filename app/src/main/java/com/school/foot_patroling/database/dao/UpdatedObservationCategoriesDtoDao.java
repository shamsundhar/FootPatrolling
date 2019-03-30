package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.MasterDto;
import com.school.foot_patroling.register.model.UpdatedObservationCategoriesDto;

import java.util.List;

@Dao
public interface UpdatedObservationCategoriesDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedObservationCategoriesDto updatedObservationCategoriesDto);

    @Query("DELETE FROM UpdatedObservationCategoriesDto")
    void deleteAll();

    @Query("SELECT * from UpdatedObservationCategoriesDto") //" ORDER BY message_id ASC")
    List<UpdatedObservationCategoriesDto> getAllUpdatedObservationCategoriesDtos();
}
