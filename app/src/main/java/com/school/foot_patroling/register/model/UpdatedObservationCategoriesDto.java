
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedObservationCategoriesDto {

    private Integer count;
    private List<ObservationCategoriesDto_> observationCategoriesDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationCategoriesDto_> getObservationCategoriesDtos() {
        return observationCategoriesDtos;
    }

    public void setObservationCategoriesDtos(List<ObservationCategoriesDto_> observationCategoriesDtos) {
        this.observationCategoriesDtos = observationCategoriesDtos;
    }

}
