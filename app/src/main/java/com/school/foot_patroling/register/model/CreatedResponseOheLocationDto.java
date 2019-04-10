
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ResponseOheLocationDto")
public class CreatedResponseOheLocationDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count13")
    private Integer count;
    @Embedded
    private Object oheLocationDtos;

    public CreatedResponseOheLocationDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getOheLocationDtos() {
        return oheLocationDtos;
    }

    public void setOheLocationDtos(Object oheLocationDtos) {
        this.oheLocationDtos = oheLocationDtos;
    }

}
