
package com.school.foot_patroling.register.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.school.foot_patroling.database.dbconverters.UserLoginDtoConverter;
import com.school.foot_patroling.database.dbconverters.UserLoginDto_Converter;

import java.util.List;
@Entity(tableName = "UpdatedResponseUserLoginDto")
public class UpdatedResponseUserLoginDto {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "count9")
    private Integer count;
    @TypeConverters(UserLoginDto_Converter.class)
    @ColumnInfo(name = "userLoginDtos")
    private List<UserLoginDto_> userLoginDtos;

    public UpdatedResponseUserLoginDto() {
    }

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
