package com.school.foot_patroling.database.dbconverters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.foot_patroling.register.model.FacilityDto_;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FacilityDto_Converter {

    @TypeConverter
    public static List<FacilityDto_> fromString(String value) {
        Type listType = new TypeToken<List<FacilityDto_>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(List<FacilityDto_> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
