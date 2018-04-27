package com.example.xuanke.util;

import com.google.gson.Gson;

public class GsonUtil {

    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object fromJson(String src,Class<?> cls){
        Gson gson = new Gson();
        return gson.fromJson(src,cls);
    }

}
