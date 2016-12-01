package ru.ncedu.ecomm.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class JSONUtils {

    private static Gson gson = new GsonBuilder()
            .setDateFormat("dd.MM.yyyy")
            .create();

    public static String toJSON(Object object) {
        return gson.toJson(object);
    }

    public static <T> T toObject(String json, Class<T> targetClass) {
        return gson.fromJson(json, targetClass);
    }

    public static <T> List<T> toList(String json, Class<T> targetClass) {
        return gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
    }

}