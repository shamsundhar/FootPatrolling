
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ObservationsCheckListDto_Converter;

import java.util.List;
@Entity(tableName = "UpdatedObservationsCheckListDto")
public class UpdatedObservationsCheckListDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count16")
    private Integer count;
    @TypeConverters(ObservationsCheckListDto_Converter.class)
    @ColumnInfo(name = " observationsCheckListDtos")
    private List<ObservationsCheckListDto_> observationsCheckListDtos;

    public UpdatedObservationsCheckListDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationsCheckListDto_> getObservationsCheckListDtos() {
        return observationsCheckListDtos;
    }

    public void setObservationsCheckListDtos(List<ObservationsCheckListDto_> observationsCheckListDtos) {
        this.observationsCheckListDtos = observationsCheckListDtos;
    }

}
