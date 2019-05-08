
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "foot_patrolling_sections")
public class FootPatrollingSectionsDto {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "facility_depot")
    private String facilityDepot;
    @ColumnInfo(name = "fp_section")
    private String fpSection;
    @ColumnInfo(name = "from_date")
    private String fromDate;
    @ColumnInfo(name = "from_location")
    private String fromLocation;
    @ColumnInfo(name = "remarks")
    private String remarks;
    @ColumnInfo(name = "seq_id")
    private String seqId;
    @ColumnInfo(name = "to_date")
    private String toDate;
    @ColumnInfo(name = "to_location")
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
