package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedFootPatrollingSectionsDto;
import com.school.foot_patroling.register.model.UpdatedFunctionalLocationHierarchyDto;

import java.util.List;

@Dao
public interface UpdatedFootPatrollingSectionsDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedFootPatrollingSectionsDto updatedFootPatrollingSectionsDto);

    @Query("DELETE FROM UpdatedFootPatrollingSectionsDto")
    void deleteAll();

    @Query("SELECT * from UpdatedFootPatrollingSectionsDto") //" ORDER BY message_id ASC")
    List<UpdatedFootPatrollingSectionsDto> getAllUpdatedFootPatrollingSectionsDtos();
}
