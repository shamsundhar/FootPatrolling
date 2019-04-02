
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;
@Entity(tableName = "ObservationsCheckListDto")
public class CreatedObservationsCheckListDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "observationsCheckListDtos")
    private List<ObservationsCheckListDto> observationsCheckListDtos = null;

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
