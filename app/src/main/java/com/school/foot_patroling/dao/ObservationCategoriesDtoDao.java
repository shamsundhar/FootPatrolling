package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
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
    @Query("SELECT COUNT(seq_id) FROM observation_categories")
    int getCount();
}
