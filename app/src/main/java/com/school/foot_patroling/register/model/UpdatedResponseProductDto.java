
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedResponseProductDto {

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
