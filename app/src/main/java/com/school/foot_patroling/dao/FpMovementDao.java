package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.FpMovementDto;

import java.util.List;
@Dao
public interface FpMovementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FpMovementDto fpMovementDto);

    @Query("DELETE FROM fp_movement")
    void deleteAll();

    @Query("SELECT * from fp_movement") //" ORDER BY message_id ASC")
    List<FpMovementDto> getAllFpMovementDtos();

    @Query("SELECT COUNT(seq_id) FROM fp_movement")
    int getCount();

}
