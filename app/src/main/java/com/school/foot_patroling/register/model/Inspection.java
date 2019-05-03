package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "inspection")
public class Inspection {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "seq_id")
    private String seq_id;
    @ColumnInfo(name = "device_id")
    private String device_id;
    @ColumnInfo(name = "device_seq_id")
    private String device_seq_id;
    @ColumnInfo(name = "inspection_type")
    private String inspection_type;
    @ColumnInfo(name = "start_time")
    private String start_time;
    @ColumnInfo(name = "stop_time")
    private String stop_time;
    @ColumnInfo(name = "inspection_by")
    private String inspection_by;
    @ColumnInfo(name = "section")
    private String section;
    @ColumnInfo(name = "last_updated_stamp")
    private String last_updated_stamp;
    @ColumnInfo(name = "last_updated_tx_stamp")
    private String last_updated_tx_stamp;
    @ColumnInfo(name = "created_stamp")
    private String created_stamp;
    @ColumnInfo(name = "created_tx_stamp")
    private String created_tx_stamp;
    @ColumnInfo(name = "facility_id")
    private String facility_id;
    @ColumnInfo(name = "km")
    private String km;
    @ColumnInfo(name = "location")
    private String location;


    @NonNull
    public String getSeq_id() {
        return seq_id;
    }

    public void setSeq_id(@NonNull String seq_id) {
        this.seq_id = seq_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_seq_id() {
        return device_seq_id;
    }

    public void setDevice_seq_id(String device_seq_id) {
        this.device_seq_id = device_seq_id;
    }

    public String getInspection_type() {
        return inspection_type;
    }

    public void setInspection_type(String inspection_type) {
        this.inspection_type = inspection_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public String getInspection_by() {
        return inspection_by;
    }

    public void setInspection_by(String inspection_by) {
        this.inspection_by = inspection_by;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLast_updated_stamp() {
        return last_updated_stamp;
    }

    public void setLast_updated_stamp(String last_updated_stamp) {
        this.last_updated_stamp = last_updated_stamp;
    }

    public String getLast_updated_tx_stamp() {
        return last_updated_tx_stamp;
    }

    public void setLast_updated_tx_stamp(String last_updated_tx_stamp) {
        this.last_updated_tx_stamp = last_updated_tx_stamp;
    }

    public String getCreated_stamp() {
        return created_stamp;
    }

    public void setCreated_stamp(String created_stamp) {
        this.created_stamp = created_stamp;
    }

    public String getCreated_tx_stamp() {
        return created_tx_stamp;
    }

    public void setCreated_tx_stamp(String created_tx_stamp) {
        this.created_tx_stamp = created_tx_stamp;
    }

    public String getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(String facility_id) {
        this.facility_id = facility_id;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
