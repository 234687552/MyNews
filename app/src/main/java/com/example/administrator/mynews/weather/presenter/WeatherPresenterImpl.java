package com.example.administrator.mynews.weather.presenter;

/**
 * Impl是规定者身份
 * 规定跟随者只能暴露以下方法，不可自己public一个
 * 以下是规定weatherPresenter给别人知道自己只能refreshWeatherdata();
 */
public interface WeatherPresenterImpl {
    void refreshWeatherData();
    void start();

}
