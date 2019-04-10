
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ObservationCategoriesDtoConverter;
import com.school.foot_patroling.database.dbconverters.ObservationCategoriesDto_Converter;

import java.util.List;

@Entity(tableName = "CreatedObservationCategoriesDto")
public class CreatedObservationCategoriesDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count")
    private Integer count;

    public CreatedObservationCategoriesDto(@NonNull Integer count, List<ObservationCategoriesDto> observationCategoriesDtos) {
        this.count = count;
        this.observationCategoriesDtos = observationCategoriesDtos;
    }
    @TypeConverters(ObservationCategoriesDtoConverter.class)
    @ColumnInfo(name = "observationCategoriesDtos3")
    private List<ObservationCategoriesDto> observationCategoriesDtos;

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
