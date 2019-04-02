
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "UpdatedFunctionalLocationHierarchyDto")
public class UpdatedFunctionalLocationHierarchyDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    @Embedded
    private Object functionalLocationHierarchyDtos;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getFunctionalLocationHierarchyDtos() {
        return functionalLocationHierarchyDtos;
    }

    public void setFunctionalLocationHierarchyDtos(Object functionalLocationHierarchyDtos) {
        this.functionalLocationHierarchyDtos = functionalLocationHierarchyDtos;
    }

}
