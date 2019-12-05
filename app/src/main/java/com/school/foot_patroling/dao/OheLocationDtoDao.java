package com.school.foot_patroling.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.school.foot_patroling.register.model.Compliance;
import com.school.foot_patroling.register.model.OheLocationDto;

import java.util.List;
@Dao
public interface OheLocationDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OheLocationDto oheLocationDto);

    @Query("DELETE FROM ohe_location")
    void deleteAll();

    @Query("SELECT * FROM ohe_location LIMIT 20")
    List<OheLocationDto> getAllLocationDtosLimit();

    @Query("SELECT * FROM ohe_location")
    List<OheLocationDto> getAllLocationDtos();

    @Query("SELECT * FROM ohe_location WHERE latitude > :first AND latitude < :second AND longitude < :third AND longitude > :fourth")
    List<OheLocationDto> getOheLocationsInRadius(String first, String second, String third, String fourth );

    @Query("SELECT COUNT(seqId) FROM ohe_location")
    int getCount();
}
