package com.example.administrator.mynews.utils;

import android.util.Log;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.WeatherBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class GsonUtil {
    private static final String TAG = "GsonUtil";

    private static GsonUtil instance;
    public static JsonParser parser;
    public static Gson gson;

    private GsonUtil() {
        parser = new JsonParser();
        gson=new Gson();
    }
    //single instance
    public static GsonUtil getInstance() {
        if (instance == null) {
            synchronized (GsonUtil.class) {
                if (instance == null) {
                    instance=new GsonUtil();
                }
            }
        }
        return instance;
    }



}
