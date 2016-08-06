package com.example.administrator.mynews.weather.model;

import com.example.administrator.mynews.beans.WeatherBean;

import java.util.List;

/**
 *  Impl是规定者身份
 * 规定跟随者只能暴露以下方法，不可自己public一个
 */
public interface WeatherModelImpl {
    interface LoadWeatherDataListener{
        void onSuccess(List<WeatherBean> weatherBeans);
        void onFailure(String msg);
    }

    void loadWeatherDataFromAPI(String location,LoadWeatherDataListener listener);
}
