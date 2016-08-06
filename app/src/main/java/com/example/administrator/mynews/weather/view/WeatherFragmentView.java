package com.example.administrator.mynews.weather.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.WeatherBean;
import com.example.administrator.mynews.weather.presenter.WeatherPresenterImpl;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class WeatherFragmentView extends Fragment implements WeatherViewImpl {
    private static final String TAG = "WeatherFragmentView";
    WeatherPresenterImpl presenterImpl;
    @InjectView(R.id.city)
    TextView city;
    @InjectView(R.id.today)
    TextView today;
    @InjectView(R.id.weatherImage)
    ImageView weatherImage;
    @InjectView(R.id.weather)
    TextView weather;
    @InjectView(R.id.weatherTemp)
    TextView weatherTemp;
    @InjectView(R.id.wind)
    TextView wind;
    @InjectView(R.id.weather_forecast)
    LinearLayout weatherForecast;
    @InjectView(R.id.weather_layout)
    LinearLayout weatherLayout;
    @InjectView(R.id.progress)
    ProgressBar progress;
    @InjectView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    public WeatherFragmentView() {
    }

    /**
     * 等待Presenter来绑定
     * @param presenterImpl
     */
    @Override
    public void setPresenterImpl(Object presenterImpl) {
        this.presenterImpl = (WeatherPresenterImpl) presenterImpl;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, view);
        presenterImpl.refreshWeatherData();
        return view;
    }

    @Override
    public void showProgressAndData() {
        progress.setVisibility(View.VISIBLE);
        weatherLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressAndData() {
        progress.setVisibility(View.INVISIBLE);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setToday(WeatherBean weatherBean) {
//        String cityName=weatherBean.getCity();
        String date=weatherBean.getDate();
        String wind=weatherBean.getWind();
        String temperature=weatherBean.getLowTemperature()+"℃ ~ "+weatherBean.getHighTemperature()+"℃";
        String type=weatherBean.getWeatherType();
        int imgResource=weatherBean.getImgResource();
        this.city.setText("广州");
        this.today.setText(date);
        this.wind.setText(wind);
        this.weatherTemp.setText(temperature);
        this.weather.setText(type);
        this.weatherImage.setImageResource(imgResource);
    }

    @Override
    public void setForecast(List<WeatherBean> weatherBeans) {
        for (WeatherBean weatherBean : weatherBeans) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.item_weather,null,false);
            TextView dateTV = (TextView) view.findViewById(R.id.date);
            ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView todayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
            TextView todayWindTV = (TextView) view.findViewById(R.id.wind);
            TextView todayWeatherTV = (TextView) view.findViewById(R.id.weather);

            dateTV.setText(weatherBean.getDate());
            todayTemperatureTV.setText(weatherBean.getLowTemperature()+"℃ ~ "+weatherBean.getHighTemperature()+"℃");
            todayWindTV.setText(weatherBean.getWind());
            todayWeatherTV.setText(weatherBean.getWeatherType());
            todayWeatherImage.setImageResource(weatherBean.getImgResource());
            weatherForecast.addView(view);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
