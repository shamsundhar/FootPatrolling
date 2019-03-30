
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "FunctionalLocationHierarchyDto")
public class CreatedFunctionalLocationHierarchyDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "functionalLocationHierarchyDtos")
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
