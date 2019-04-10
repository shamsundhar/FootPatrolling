
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.FootPatrollingSectionsDtoConverter;

import java.util.List;

@Entity(tableName = "CreatedFootPatrollingSections")
public class CreatedFootPatrollingSectionsDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count10")
    private Integer count;

    @TypeConverters(FootPatrollingSectionsDtoConverter.class)
    @ColumnInfo(name = "footPatrollingSectionsDtos1")
    public List<FootPatrollingSectionsDto> footPatrollingSectionsDtos;

    public CreatedFootPatrollingSectionsDto(@NonNull Integer count, List<FootPatrollingSectionsDto> footPatrollingSectionsDtos) {
        this.count = count;
        this.footPatrollingSectionsDtos = footPatrollingSectionsDtos;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<FootPatrollingSectionsDto> getFootPatrollingSectionsDtos() {
        return footPatrollingSectionsDtos;
    }

    public void setFootPatrollingSectionsDtos(List<FootPatrollingSectionsDto> footPatrollingSectionsDtos) {
        this.footPatrollingSectionsDtos = footPatrollingSectionsDtos;
    }

}
