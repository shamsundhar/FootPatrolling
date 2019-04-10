
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ObservationsCheckListDtoConverter;

import java.util.List;
@Entity(tableName = "CreatedObservationsCheckListDto")
public class CreatedObservationsCheckListDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count2")
    private Integer count;
    @TypeConverters(ObservationsCheckListDtoConverter.class)
    @ColumnInfo(name = "observationsCheckListDtos")
    private List<ObservationsCheckListDto> observationsCheckListDtos;

    public CreatedObservationsCheckListDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationsCheckListDto> getObservationsCheckListDtos() {
        return observationsCheckListDtos;
    }

    public void setObservationsCheckListDtos(List<ObservationsCheckListDto> observationsCheckListDtos) {
        this.observationsCheckListDtos = observationsCheckListDtos;
    }

}
