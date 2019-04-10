
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.ProductDto_Converter;
import com.school.foot_patroling.database.dbconverters.UserLoginDtoConverter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
@Entity(tableName = "ResponseUserLoginDto")
public class CreatedResponseUserLoginDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count14")
    private Integer count;
    @TypeConverters(UserLoginDtoConverter.class)
    @ColumnInfo(name = "userLoginDtos7")
    private List<UserLoginDto> userLoginDtos;

    public CreatedResponseUserLoginDto() {
    }

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
