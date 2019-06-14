package com.school.foot_patroling.reports;

import com.google.gson.annotations.SerializedName;

public class ReportModel {

    @SerializedName("reportId")
    private String reportId;
    @SerializedName("facilityId")
    private String facilityId;
    @SerializedName("subDivision")
    private String subDivision;
    @SerializedName("fromDate")
    private String fromDate;
    @SerializedName("thruDate")
    private String thruDate;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getSubDivision() {
        return subDivision;
    }

    public void setSubDivision(String subDivision) {
        this.subDivision = subDivision;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getThruDate() {
        return thruDate;
    }

    public void setThruDate(String thruDate) {
        this.thruDate = thruDate;
    }
}
