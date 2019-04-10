
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "FootPatrollingSectionsDto_")
public class FootPatrollingSectionsDto_ {

    @PrimaryKey
    @NonNull
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
    @Embedded
    private String remarks;
    @ColumnInfo(name = "toDate")
    private String toDate;
    @ColumnInfo(name = "toLocation")
    private String toLocation;

    public FootPatrollingSectionsDto_() {
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
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
