package com.school.foot_patroling.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.school.foot_patroling.register.model.ProductDto;
import com.school.foot_patroling.register.model.ProductDto_;

import java.util.List;

@Dao
public interface ProductDto_Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductDto_ productDto_);

    @Query("DELETE FROM ProductDto_")
    void deleteAll();

    @Query("SELECT * from ProductDto_") //" ORDER BY message_id ASC")
    List<ProductDto_> getAllProductDto_s();
}
