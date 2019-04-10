package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedFunctionalLocationHierarchyDto;
import com.school.foot_patroling.register.model.CreatedObservationCategoriesDto;

import java.util.List;

@Dao
public interface CreatedObservationCategoriesDtoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedObservationCategoriesDto createdObservationCategoriesDto);

    @Query("DELETE FROM CreatedObservationCategoriesDto")
    void deleteAll();

    @Query("SELECT * from CreatedObservationCategoriesDto") //" ORDER BY message_id ASC")
    List<CreatedObservationCategoriesDto> getAllCreatedObservationCategoriesDtos();


}
