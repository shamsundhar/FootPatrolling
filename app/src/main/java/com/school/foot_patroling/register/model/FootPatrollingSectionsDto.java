
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FootPatrollingSections")
public class FootPatrollingSectionsDto {

    @PrimaryKey
    @ColumnInfo(name = "seqId")
    private String seqId;
    @ColumnInfo(name = "facilityDepot")
    private String facilityDepot;
    @ColumnInfo(name = "fpSection")
    private String fpSection;
    @ColumnInfo(name = "fromDate")
    private String fromDate;
    @ColumnInfo(name = "fromLocation")
    private String fromLocation;
    @ColumnInfo(name = "remarks")
    private Object remarks;
    @ColumnInfo(name = "toDate")
    private String toDate;
    @ColumnInfo(name = "toLocation")
    private String toLocation;

    public String getFacilityDepot() {
        return facilityDepot;
    }

    public void setFacilityDepot(String facilityDepot) {
        this.facilityDepot = facilityDepot;
    }

    public String getFpSection() {
        return fpSection;
    }

    public void setFpSection(String fpSection) {
        this.fpSection = fpSection;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

}
