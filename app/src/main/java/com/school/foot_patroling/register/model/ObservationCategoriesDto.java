
package com.school.foot_patroling.register.model;


public class ObservationCategoriesDto {

    private String department;
    private Object description;
    private Object fromDate;
    private String inspectionType;
    private String observationCategory;
    private Object remark;
    private String seqId;
    private Object thruDate;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getFromDate() {
        return fromDate;
    }

    public void setFromDate(Object fromDate) {
        this.fromDate = fromDate;
    }

    public String getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(String inspectionType) {
        this.inspectionType = inspectionType;
    }

    public String getObservationCategory() {
        return observationCategory;
    }

    public void setObservationCategory(String observationCategory) {
        this.observationCategory = observationCategory;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public Object getThruDate() {
        return thruDate;
    }

    public void setThruDate(Object thruDate) {
        this.thruDate = thruDate;
    }

}
