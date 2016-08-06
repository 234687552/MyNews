package com.example.administrator.mynews.utils;

import com.example.administrator.mynews.beans.WeatherBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class RetrofitUtil {

    //eg: http://wthrcdn.etouch.cn/weather_mini?city=广州
    private static final String BASEURL="http://wthrcdn.etouch.cn/";

    public interface WeatherService {
       @GET("/weather_mini")
        Call<ResponseBody> getWeather(@Query("city") String cityname);
    }

    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASEURL)
            .build();

    static public WeatherService weatherService=retrofit.create(WeatherService.class);





}
