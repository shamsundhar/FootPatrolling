package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.CreatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.CreatedFunctionalLocationHierarchyDto;

import java.util.List;

@Dao
public interface CreatedFunctionalLocationHierarchyDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CreatedFunctionalLocationHierarchyDto createdFunctionalLocationHierarchyDto);

    @Query("DELETE FROM FunctionalLocationHierarchyDto")
    void deleteAll();

    @Query("SELECT * from FunctionalLocationHierarchyDto") //" ORDER BY message_id ASC")
    List<CreatedFunctionalLocationHierarchyDto> getAllCreatedFunctionalLocationHierarchyDtos();
}
