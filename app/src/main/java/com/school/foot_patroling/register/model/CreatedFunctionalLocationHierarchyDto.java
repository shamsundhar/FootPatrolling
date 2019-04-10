
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "FunctionalLocationHierarchyDto")
public class CreatedFunctionalLocationHierarchyDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count1")
    private Integer count;

    public CreatedFunctionalLocationHierarchyDto() {

    }

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
