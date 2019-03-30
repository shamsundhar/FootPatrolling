package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedFunctionalLocationHierarchyDto;
import com.school.foot_patroling.register.model.UpdatedResponseFacilityDto;

import java.util.List;

@Dao
public interface UpdatedFunctionalLocationHierarchyDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedFunctionalLocationHierarchyDto updatedFunctionalLocationHierarchyDto);

    @Query("DELETE FROM UpdatedFunctionalLocationHierarchyDto")
    void deleteAll();

    @Query("SELECT * from UpdatedFunctionalLocationHierarchyDto") //" ORDER BY message_id ASC")
    List<UpdatedFunctionalLocationHierarchyDto> getAllUpdatedFunctionalLocationHierarchyDtos();
}
