
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;
@Entity(tableName = "ResponseProductDto")
public class CreatedResponseProductDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "productDtos")
    private List<ProductDto> productDtos = null;

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
