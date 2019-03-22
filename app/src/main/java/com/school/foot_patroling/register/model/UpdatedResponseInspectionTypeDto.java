
package com.school.foot_patroling.register.model;

import java.util.List;

public class UpdatedResponseInspectionTypeDto {

    private Integer count;
    private List<InspectionTypeDto_> inspectionTypeDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<InspectionTypeDto_> getInspectionTypeDtos() {
        return inspectionTypeDtos;
    }

    public void setInspectionTypeDtos(List<InspectionTypeDto_> inspectionTypeDtos) {
        this.inspectionTypeDtos = inspectionTypeDtos;
    }

}
