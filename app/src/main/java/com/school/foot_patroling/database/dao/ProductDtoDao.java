package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.RegistrationRequestModel;

import java.util.List;

@Dao
public interface ProductDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductDto productDto);

    @Query("DELETE FROM ProductDto")
    void deleteAll();

    @Query("SELECT * from ProductDto") //" ORDER BY message_id ASC")
    List<ProductDto> getAllProductDtos();
}
