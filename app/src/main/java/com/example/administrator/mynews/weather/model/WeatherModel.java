package com.example.administrator.mynews.weather.model;

import android.util.Log;

import com.example.administrator.mynews.beans.WeatherBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.example.administrator.mynews.utils.RetrofitUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * model是业务逻辑JavaBean；
 */
public class WeatherModel implements WeatherModelImpl {

    @Override
    public void loadWeatherDataFromAPI(String location, final LoadWeatherDataListener listener) {
        Call<ResponseBody> call = RetrofitUtil.weatherService.getWeather(location);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    List<WeatherBean> weatherBeans = GsonUtil.getWeathers(jsonString);
                    listener.onSuccess(weatherBeans);
                } catch (IOException e) {
                    listener.onFailure(e.getMessage());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

}