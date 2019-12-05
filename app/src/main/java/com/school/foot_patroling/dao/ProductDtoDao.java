package com.school.foot_patroling.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.school.foot_patroling.register.model.ProductDto;

import java.util.List;

@Dao
public interface ProductDtoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductDto productDto);

    @Query("DELETE FROM product")
    void deleteAll();

    @Query("SELECT * from product")
    List<ProductDto> getAllProductDtos();
    @Query("SELECT COUNT(amountUomTypeId) FROM product")
    int getCount();
}
