package com.school.foot_patroling.database.dbconverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.foot_patroling.register.model.UserLoginDto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserLoginDtoConverter {
    @TypeConverter
    public static List<UserLoginDto> fromString(String value) {
        Type listType = new TypeToken<List<UserLoginDto>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(List<UserLoginDto> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
