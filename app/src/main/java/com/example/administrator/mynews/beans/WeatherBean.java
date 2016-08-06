package com.example.administrator.mynews.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class WeatherBean {
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
    private String highTemperature;
    private String lowTemperature;
    private String wind;
    private String weatherType;
    private int imgResource;
    private String date;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }


}
