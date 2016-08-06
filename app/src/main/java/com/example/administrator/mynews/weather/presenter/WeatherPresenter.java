package com.example.administrator.mynews.weather.presenter;

import android.util.Log;
import android.widget.Toast;

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
        weatherView.setPresenterImpl(this);//绑定view和Presenter一起.
        weatherModel=new WeatherModel();
    }

    /**
     * 刷新天气
     */
    @Override
    public void refreshWeatherData() {
        weatherModel.loadWeatherDataFromAPI("广州", new WeatherModelImpl.LoadWeatherDataListener() {
            @Override
            public void onSuccess(List<WeatherBean> weatherBeans) {
                Log.w(TAG, "onSuccess: " );
                if (weatherBeans != null && weatherBeans.size() > 0) {
                    weatherView.setToday(weatherBeans.remove(0));
                    weatherView.setForecast(weatherBeans);
                    weatherView.hideProgressAndData();
                }
            }

            @Override
            public void onFailure(String msg) {
                weatherView.showProgressAndData();
                Log.w(TAG, "onFailure: " );
            }
        });
    }

    /**
     * 一开始就应该做的事：刷新天气
     */
    @Override
    public void start() {
        weatherView.showProgressAndData();
        refreshWeatherData();
    }

}
