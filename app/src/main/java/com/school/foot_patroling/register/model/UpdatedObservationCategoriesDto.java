
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "UpdatedObservationCategoriesDto")
public class UpdatedObservationCategoriesDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    private List<ObservationCategoriesDto_> observationCategoriesDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ObservationCategoriesDto_> getObservationCategoriesDtos() {
        return observationCategoriesDtos;
    }

    public void setObservationCategoriesDtos(List<ObservationCategoriesDto_> observationCategoriesDtos) {
        this.observationCategoriesDtos = observationCategoriesDtos;
    }

}
