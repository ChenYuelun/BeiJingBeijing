package com.example.beijingbeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by chenyuelun on 2017/6/2.
 */

public class CacheUtils {
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("beijingnews", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("beijingnews", Context.MODE_PRIVATE);
        return  sp.getBoolean(key,false);
    }
}
