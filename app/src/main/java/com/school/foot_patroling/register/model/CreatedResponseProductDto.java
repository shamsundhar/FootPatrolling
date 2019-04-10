
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ProductDtoConverter;

import java.util.List;
@Entity(tableName = "ResponseProductDto")
public class CreatedResponseProductDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count4")
    private Integer count;
    @TypeConverters(ProductDtoConverter.class)
    @ColumnInfo(name = "productDtos6")
    private List<ProductDto> productDtos;

    public CreatedResponseProductDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

}
