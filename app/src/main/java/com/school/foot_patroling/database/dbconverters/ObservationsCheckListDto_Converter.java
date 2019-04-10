package com.school.foot_patroling.database.dbconverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.foot_patroling.register.model.ObservationsCheckListDto_;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObservationsCheckListDto_Converter {
    @TypeConverter
    public static List<ObservationsCheckListDto_> fromString(String value) {
        Type listType = new TypeToken<List<ObservationsCheckListDto_>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(List<ObservationsCheckListDto_> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
