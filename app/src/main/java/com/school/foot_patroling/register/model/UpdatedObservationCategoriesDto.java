
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ObservationCategoriesDto_Converter;

import java.util.List;

@Entity(tableName = "UpdatedObservationCategoriesDto")
public class UpdatedObservationCategoriesDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count6")
    private Integer count;
    @TypeConverters(ObservationCategoriesDto_Converter.class)
    @ColumnInfo(name = "observationCategoriesDtos")
    private List<ObservationCategoriesDto_> observationCategoriesDtos;

    public UpdatedObservationCategoriesDto() {
    }

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
