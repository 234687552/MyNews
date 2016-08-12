package com.example.administrator.mynews.weather.model;

import android.util.Log;

import com.example.administrator.mynews.beans.WeatherBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.utils.RetrofitUtil;
import com.example.administrator.mynews.weather.WeatherGson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * model是业务逻辑JavaBean；
 */
public class WeatherModel implements WeatherModelImpl {
    private static final String TAG = "WeatherModel";
    private WeatherGson weatherGson=new WeatherGson();
    private RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();

    private Call<ResponseBody> weatherCall;//天气CallBack
    private Call<ResponseBody> locationCall;//地址CallBack

    //天气获取service
    private interface WeatherService {
        @GET(APIUrl.WEATHER_URL)
        Call<ResponseBody> getWeather(@Query("city") String city);
    }
    //地址获取service
    private interface LocationService{
        @GET(APIUrl.LOCATION_URL)
        Call<ResponseBody> getLocation(@Query("ip") String ip);
    }

    /**
     * 根据地点获取天气；
     */
    @Override
    public void loadWeatherDataFromService(String location, final LoadWeatherDataListener listener) {
        weatherCall = retrofitUtil.createService(WeatherService.class).getWeather(location);
        Log.w(TAG, ""+weatherCall.request() );

        weatherCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    List<WeatherBean> weatherBeans = weatherGson.getWeathers(jsonString);
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

    /**
     * 根据网络获取地址
     */
    @Override
    public void loadLocationFromService(final LoadLocationDataListener listener) {
        locationCall = retrofitUtil.createService(LocationService.class).getLocation("myip");
        Log.w(TAG, ""+locationCall.request() );
        locationCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    String city =weatherGson.getLocation(jsonString);
                    if (city == null)
                        listener.onFailure("淘宝Ip获取失败");
                    else
                        listener.onSuccess(city);
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