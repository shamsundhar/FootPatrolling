
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedObservationsCheckListDto {

    private Integer count;
    private List<ObservationsCheckListDto> observationsCheckListDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationsCheckListDto> getObservationsCheckListDtos() {
        return observationsCheckListDtos;
    }

    public void setObservationsCheckListDtos(List<ObservationsCheckListDto> observationsCheckListDtos) {
        this.observationsCheckListDtos = observationsCheckListDtos;
    }

}
