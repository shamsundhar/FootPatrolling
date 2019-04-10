
package com.school.foot_patroling.register.model;


public class ObservationsCheckListDto {

    private String description;
    private String displaySequence;
    private Object fromDate;
    private String inspectionType;
    private String observationCategory;
    private String observationItem;
    private String priority;
    private String seqId;
    private Object thruDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(String displaySequence) {
        this.displaySequence = displaySequence;
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

    public String getObservationItem() {
        return observationItem;
    }

    public void setObservationItem(String observationItem) {
        this.observationItem = observationItem;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
