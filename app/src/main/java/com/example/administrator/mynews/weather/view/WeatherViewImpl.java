package com.example.administrator.mynews.weather.view;

import com.example.administrator.mynews.beans.WeatherBean;

import java.util.List;

/**
 * Impl是规定者身份
 * 规定跟随者只能暴露以下方法，不可自己public一个
 */
public interface WeatherViewImpl {
    void showProgressHideData();
    void hideProgressShowData();

    void setToday(WeatherBean weatherBean);

    void setForecast(List<WeatherBean> weatherBeans);

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