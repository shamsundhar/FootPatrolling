
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedObservationsCheckListDto {

    private Integer count;
    private List<ObservationsCheckListDto_> observationsCheckListDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationsCheckListDto_> getObservationsCheckListDtos() {
        return observationsCheckListDtos;
    }

    public void setObservationsCheckListDtos(List<ObservationsCheckListDto_> observationsCheckListDtos) {
        this.observationsCheckListDtos = observationsCheckListDtos;
    }

}
