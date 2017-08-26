package com.shuzhongchen.githubrepo.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by shuzhongchen on 8/25/17.
 */

public class ModelUtils {

    private static Gson gson = new Gson();
    private static String PREF_NAME = "models";


    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> String toString(T object, TypeToken<T> typeToken) {
        return gson.toJson(object, typeToken.getType());
    }

}

