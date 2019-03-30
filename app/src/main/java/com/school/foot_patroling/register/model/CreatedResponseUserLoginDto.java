
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.util.List;
@Entity(tableName = "ResponseUserLoginDto")
public class CreatedResponseUserLoginDto {

    @ColumnInfo(name = "count")
    private Integer count;
    @ColumnInfo(name = "userLoginDtos")
    private List<UserLoginDto> userLoginDtos = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<UserLoginDto> getUserLoginDtos() {
        return userLoginDtos;
    }

    public void setUserLoginDtos(List<UserLoginDto> userLoginDtos) {
        this.userLoginDtos = userLoginDtos;
    }

}
