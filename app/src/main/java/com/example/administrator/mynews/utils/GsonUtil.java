package com.example.administrator.mynews.utils;

import android.util.Log;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.WeatherBean;
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

    public static List<WeatherBean> getWeathers(String json) {
        List<WeatherBean> weatherBeans = new ArrayList<WeatherBean>();
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
        if (jsonObj.get("status").getAsInt() == 1000) {
            JsonArray jsonArray = jsonObj.getAsJsonObject("data").getAsJsonArray("forecast");
            for (int i = 0; i < jsonArray.size(); i++) {
                WeatherBean weatherBean = getWeatherBeanFromJson(jsonArray.get(i).getAsJsonObject());
                weatherBeans.add(weatherBean);
            }
        }
        return weatherBeans;
    }

    /**
     * forecast": [
     * {
     * "fengxiang": "北风",
     * "fengli": "5-6级",
     * "high": "高温 24℃",
     * "type": "晴",
     * "low": "低温 11℃",
     * "date": "3日星期六"
     * },
     * {
     * "fengxiang": "北风",
     * "fengli": "4-5级",
     * "high": "高温 19℃",
     * "type": "晴",
     * "low": "低温 8℃",
     * "date": "4日星期日"
     * }]
     */
    private static WeatherBean getWeatherBeanFromJson(JsonObject jsonObject) {
        String wind = jsonObject.get("fengxiang").getAsString();
        String highTem = jsonObject.get("high").getAsString();
        String lowTem = jsonObject.get("low").getAsString();
        String weatherType = jsonObject.get("type").getAsString();
        String date = jsonObject.get("date").getAsString();
        WeatherBean weatherBean = new WeatherBean();
        weatherBean.setDate(date);
        weatherBean.setHighTemperature(highTem);
        weatherBean.setLowTemperature(lowTem);
        weatherBean.setWind(wind);
        weatherBean.setWeatherType(weatherType);
        weatherBean.setImgResource(getImgResource(weatherType));
        return weatherBean;
    }

    private static int getImgResource(String weatherType) {
        /**
         * 表达式内容为整数、整数表达式（int\Integer）、枚举常量
         * byte short char可转整型
         * java1.7 开始支持String；
         */
        switch (weatherType) {
            case "多云":
            case "多云转雨":
            case "多云转晴":
                return R.drawable.biz_plugin_weather_duoyun;
            case "中雨":
            case "中到大雨":
                return R.drawable.biz_plugin_weather_zhongyu;
            case "雷阵雨":
                return R.drawable.biz_plugin_weather_leizhenyu;
            case "阵雨":
            case "阵雨转多云":
                return R.drawable.biz_plugin_weather_zhenyu;
            case "暴雪":
                return R.drawable.biz_plugin_weather_baoxue;
            case "暴雨":
                return R.drawable.biz_plugin_weather_baoyu;
            case "大暴雨":
                return R.drawable.biz_plugin_weather_dabaoyu;
            case "大雪":
                return R.drawable.biz_plugin_weather_daxue;
            case "大雨":
            case "大雨转中雨":
                return R.drawable.biz_plugin_weather_dayu;
            case "雷阵雨冰雹":
                return R.drawable.biz_plugin_weather_leizhenyubingbao;
            case "晴":
                return R.drawable.biz_plugin_weather_qing;
            case "沙尘暴":
                return R.drawable.biz_plugin_weather_shachenbao;
            case "特大暴雨":
                return R.drawable.biz_plugin_weather_tedabaoyu;
            case "雾":
            case "雾霾":
                return R.drawable.biz_plugin_weather_wu;
            case "小雪":
                return R.drawable.biz_plugin_weather_xiaoxue;
            case "小雨":
                return R.drawable.biz_plugin_weather_xiaoyu;
            case "阴":
                return R.drawable.biz_plugin_weather_yin;
            case "雨夹雪":
                return R.drawable.biz_plugin_weather_yujiaxue;
            case "阵雪":
                return R.drawable.biz_plugin_weather_zhenxue;
            case "中雪":
                return R.drawable.biz_plugin_weather_zhongxue;
            default:
                return R.drawable.biz_plugin_weather_duoyun;
        }
    }
}
