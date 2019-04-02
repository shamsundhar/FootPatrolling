
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "UpdatedResponseUserLoginDto")
public class UpdatedResponseUserLoginDto {

    @PrimaryKey
    @ColumnInfo(name = "count")
    private Integer count;
    private List<UserLoginDto_> userLoginDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserLoginDto_> getUserLoginDtos() {
        return userLoginDtos;
    }

    public void setUserLoginDtos(List<UserLoginDto_> userLoginDtos) {
        this.userLoginDtos = userLoginDtos;
    }

}
