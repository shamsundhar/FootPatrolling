
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "UpdatedObservationsCheckListDto")
public class UpdatedObservationsCheckListDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    private List<ObservationsCheckListDto_> observationsCheckListDtos = null;

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
