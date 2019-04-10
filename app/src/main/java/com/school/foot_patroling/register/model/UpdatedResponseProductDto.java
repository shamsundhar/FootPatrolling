
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ProductDtoConverter;
import com.school.foot_patroling.database.dbconverters.ProductDto_Converter;

import java.util.List;

@Entity(tableName = "UpdatedResponseProductDto")
public class UpdatedResponseProductDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count18")
    private Integer count;
    @TypeConverters(ProductDto_Converter.class)
    @ColumnInfo(name = "productDtos")
    private List<ProductDto_> productDtos;

    public UpdatedResponseProductDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductDto_> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto_> productDtos) {
        this.productDtos = productDtos;
    }

}
