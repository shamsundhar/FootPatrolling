
package com.school.foot_patroling.register.model;

import java.util.List;

public class CreatedResponseInspectionTypeDto {

    private Integer count;
    private List<InspectionTypeDto> inspectionTypeDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<InspectionTypeDto> getInspectionTypeDtos() {
        return inspectionTypeDtos;
    }

    public void setInspectionTypeDtos(List<InspectionTypeDto> inspectionTypeDtos) {
        this.inspectionTypeDtos = inspectionTypeDtos;
    }

}
