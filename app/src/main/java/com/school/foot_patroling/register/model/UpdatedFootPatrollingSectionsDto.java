
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.FootPatrollingSectionsDto_Converter;

import java.util.List;
@Entity(tableName = "UpdatedFootPatrollingSectionsDto")
public class UpdatedFootPatrollingSectionsDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count5")
    private Integer count;
    @TypeConverters(FootPatrollingSectionsDto_Converter.class)
    @ColumnInfo(name = "footPatrollingSectionsDtos2")
    private List<FootPatrollingSectionsDto_> footPatrollingSectionsDtos;

    public UpdatedFootPatrollingSectionsDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FootPatrollingSectionsDto_> getFootPatrollingSectionsDtos() {
        return footPatrollingSectionsDtos;
    }

    public void setFootPatrollingSectionsDtos(List<FootPatrollingSectionsDto_> footPatrollingSectionsDtos) {
        this.footPatrollingSectionsDtos = footPatrollingSectionsDtos;
    }

}
