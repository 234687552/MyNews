package com.example.administrator.mynews.weather.presenter;

import android.util.Log;

import com.example.administrator.mynews.beans.WeatherBean;
import com.example.administrator.mynews.weather.model.WeatherModel;
import com.example.administrator.mynews.weather.model.WeatherModelImpl;
import com.example.administrator.mynews.weather.view.WeatherFragmentView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class WeatherPresenter implements WeatherPresenterImpl {
    private static final String TAG = "WeatherPresenter";

    private WeatherFragmentView weatherView;
    private WeatherModel weatherModel;
    public WeatherPresenter(WeatherFragmentView weatherView) {
        this.weatherView=weatherView;
        weatherModel=new WeatherModel();
    }

    /**
     * 刷新天气
     */
    @Override
    public void refreshWeatherData() {

        weatherModel.loadLocationFromService(new WeatherModelImpl.LoadLocationDataListener() {
            @Override
            public void onSuccess(String city) {
                Log.w(TAG, "loadLocationFromService: " + city);
                if (weatherModel == null) {
                    weatherModel = new WeatherModel();
                }
                weatherModel.loadWeatherDataFromService(city, new WeatherModelImpl.LoadWeatherDataListener() {
                    @Override
                    public void onSuccess(List<WeatherBean> weatherBeans) {
                        Log.w(TAG, "loadWeatherDataFromService: succeed");
                        if (weatherBeans != null &&weatherView!=null&& weatherBeans.size() > 0) {
                            weatherView.setToday(weatherBeans.remove(0));
                            weatherView.setForecast(weatherBeans);
                            weatherView.hideProgressShowData();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        weatherView.hideProgressShowData();
                        Log.w(TAG, "onFailure: " + msg);
                    }
                });
            }

            @Override
            public void onFailure(String msg) {
                weatherView.hideProgressShowData();
                Log.w(TAG, "onFailure: " + msg);
            }
        });
    }


    /**
     * 一开始就应该做的事：刷新天气
     */
    @Override
    public void start() {
        weatherView.showProgressHideData();
        refreshWeatherData();
    }

}
