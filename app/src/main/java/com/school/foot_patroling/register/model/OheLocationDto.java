
package com.school.foot_patroling.register.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "ohe_location")
public class OheLocationDto {

    @ColumnInfo(name = "adeeSection")
    public String adeeSection;
    @ColumnInfo(name = "altitude")
    public String altitude;
    @ColumnInfo(name = "chainage")
    public String chainage;
    @ColumnInfo(name = "chainageRemark")
    public String chainageRemark;
    @ColumnInfo(name = "createdStamp")
    public String createdStamp;
    @ColumnInfo(name = "createdTxStamp")
    public String createdTxStamp;
    @ColumnInfo(name = "curvature")
    public String curvature;
    @ColumnInfo(name = "curvatureRemark")
    public String curvatureRemark;
    @ColumnInfo(name = "date")
    public String date;
    @ColumnInfo(name = "division")
    public String division;
    @ColumnInfo(name = "engFeature")
    public String engFeature;
    @ColumnInfo(name = "facilityId")
    public String facilityId;
    @ColumnInfo(name = "heading")
    public String heading;
    @ColumnInfo(name = "kilometer")
    public String kilometer;
    @ColumnInfo(name = "lastUpdatedStamp")
    public String lastUpdatedStamp;
    @ColumnInfo(name = "lastUpdatedTxStamp")
    public String lastUpdatedTxStamp;
    @ColumnInfo(name = "latitude")
    public String latitude;
    @ColumnInfo(name = "longitude")
    public String longitude;
    @ColumnInfo(name = "majorSection")
    public String majorSection;
    @ColumnInfo(name = "oheFeature")
    public String oheFeature;
    @ColumnInfo(name = "oheMast")
    public String oheMast;
    @ColumnInfo(name = "oheSequence")
    public String oheSequence;
    @ColumnInfo(name = "pwi")
    public String pwi;
    @ColumnInfo(name = "remarkOne")
    public String remarkOne;
    @ColumnInfo(name = "remarkTwo")
    public String remarkTwo;
    @ColumnInfo(name = "satellites")
    public String satellites;
    @ColumnInfo(name = "section")
    public String section;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seqId")
    public String seqId;
    @ColumnInfo(name = "sequenceNo")
    public String sequenceNo;
    @ColumnInfo(name = "speed")
    public String speed;

    public String getAdeeSection() {
        return adeeSection;
    }

    public void setAdeeSection(String adeeSection) {
        this.adeeSection = adeeSection;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getChainage() {
        return chainage;
    }

    public void setChainage(String chainage) {
        this.chainage = chainage;
    }

    public String getChainageRemark() {
        return chainageRemark;
    }

    public void setChainageRemark(String chainageRemark) {
        this.chainageRemark = chainageRemark;
    }

    public String getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(String createdStamp) {
        this.createdStamp = createdStamp;
    }

    public String getCreatedTxStamp() {
        return createdTxStamp;
    }

    public void setCreatedTxStamp(String createdTxStamp) {
        this.createdTxStamp = createdTxStamp;
    }

    public String getCurvature() {
        return curvature;
    }

    public void setCurvature(String curvature) {
        this.curvature = curvature;
    }

    public String getCurvatureRemark() {
        return curvatureRemark;
    }

    public void setCurvatureRemark(String curvatureRemark) {
        this.curvatureRemark = curvatureRemark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEngFeature() {
        return engFeature;
    }

    public void setEngFeature(String engFeature) {
        this.engFeature = engFeature;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(String lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getLastUpdatedTxStamp() {
        return lastUpdatedTxStamp;
    }

    public void setLastUpdatedTxStamp(String lastUpdatedTxStamp) {
        this.lastUpdatedTxStamp = lastUpdatedTxStamp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMajorSection() {
        return majorSection;
    }

    public void setMajorSection(String majorSection) {
        this.majorSection = majorSection;
    }

    public String getOheFeature() {
        return oheFeature;
    }

    public void setOheFeature(String oheFeature) {
        this.oheFeature = oheFeature;
    }

    public String getOheMast() {
        return oheMast;
    }

    public void setOheMast(String oheMast) {
        this.oheMast = oheMast;
    }

    public String getOheSequence() {
        return oheSequence;
    }

    public void setOheSequence(String oheSequence) {
        this.oheSequence = oheSequence;
    }

    public String getPwi() {
        return pwi;
    }

    public void setPwi(String pwi) {
        this.pwi = pwi;
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne;
    }

    public String getRemarkTwo() {
        return remarkTwo;
    }

    public void setRemarkTwo(String remarkTwo) {
        this.remarkTwo = remarkTwo;
    }

    public String getSatellites() {
        return satellites;
    }

    public void setSatellites(String satellites) {
        this.satellites = satellites;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getTrackLine() {
        return trackLine;
    }

    public void setTrackLine(String trackLine) {
        this.trackLine = trackLine;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    @ColumnInfo(name = "structureType")
    public String structureType;
    @ColumnInfo(name = "trackLine")
    public String trackLine;
    @ColumnInfo(name = "validity")
    public String validity;

}