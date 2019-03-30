
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "ResponseOheLocationDto")
public class CreatedResponseOheLocationDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "oheLocationDtos")
    private Object oheLocationDtos;

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
