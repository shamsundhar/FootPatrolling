
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "UpdatedFootPatrollingSectionsDto")
public class UpdatedFootPatrollingSectionsDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    private List<FootPatrollingSectionsDto_> footPatrollingSectionsDtos = null;

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
