
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "UpdatedResponseProductDto")
public class UpdatedResponseProductDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    private List<ProductDto_> productDtos = null;

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
