package com.school.foot_patroling.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.school.foot_patroling.register.model.ObservationCategoriesDto;

import java.util.List;

@Dao
public interface ObservationCategoriesDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ObservationCategoriesDto observationCategoriesDto);

    @Query("DELETE FROM observation_categories")
    void deleteAll();

    @Query("SELECT * from observation_categories")
    List<ObservationCategoriesDto> getAllCategoryDtos();
}
