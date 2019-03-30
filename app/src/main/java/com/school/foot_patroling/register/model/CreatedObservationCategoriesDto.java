
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;

@Entity(tableName = "ObservationCategoriesDto")
public class CreatedObservationCategoriesDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "observationCategoriesDtos")
    private List<ObservationCategoriesDto> observationCategoriesDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationCategoriesDto> getObservationCategoriesDtos() {
        return observationCategoriesDtos;
    }

    public void setObservationCategoriesDtos(List<ObservationCategoriesDto> observationCategoriesDtos) {
        this.observationCategoriesDtos = observationCategoriesDtos;
    }

}
