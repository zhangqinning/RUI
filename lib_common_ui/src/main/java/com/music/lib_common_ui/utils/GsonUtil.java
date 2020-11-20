package com.music.lib_common_ui.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.music.lib_api.user.AudioBean;

import java.util.ArrayList;

/**
 * 字符串和Java类的转换工具
 */
public class GsonUtil {
    private static final String TAG = "GsonUtil";

    public static String toJson(Object obj) {
        return createGson().toJson(obj);
    }

    private static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        return gsonBuilder.create();
    }


    /**
     * 将字符串转换成对应的Java对象
     */
    public static <T> T fromJSON(String json, Class<T> cls) {
        try {
            return createGson().fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换成对应的Java对象
     */
    public static ArrayList<AudioBean> fromAudioJSON(String json) {
        try {
            return createGson().fromJson(json, new TypeToken<ArrayList<AudioBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
