package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.UpdatedResponseOheLocationDto;
import com.school.foot_patroling.register.model.UpdatedResponseProductDto;

import java.util.List;
@Dao
public interface UpdatedResponseOheLocationDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UpdatedResponseOheLocationDto updatedResponseOheLocationDto);

    @Query("DELETE FROM UpdatedResponseOheLocationDto")
    void deleteAll();

    @Query("SELECT * from UpdatedResponseOheLocationDto") //" ORDER BY message_id ASC")
    List<UpdatedResponseOheLocationDto> getAllUpdatedResponseOheLocationDtos();
}
