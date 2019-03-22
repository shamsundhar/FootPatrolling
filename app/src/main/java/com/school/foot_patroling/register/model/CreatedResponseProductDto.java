
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedResponseProductDto {

    private Integer count;
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
