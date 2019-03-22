
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedObservationCategoriesDto {

    private Integer count;
    private List<ObservationCategoriesDto> observationCategoriesDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationCategoriesDto> getObservationCategoriesDtos() {
        return observationCategoriesDtos;
    }

    public void setObservationCategoriesDtos(List<ObservationCategoriesDto> observationCategoriesDtos) {
        this.observationCategoriesDtos = observationCategoriesDtos;
    }

}
