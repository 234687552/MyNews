package com.example.administrator.mynews.weather.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.WeatherBean;
import com.example.administrator.mynews.utils.NetInfoUtil;
import com.example.administrator.mynews.weather.presenter.WeatherPresenter;
import com.example.administrator.mynews.weather.presenter.WeatherPresenterImpl;
import com.yalantis.phoenix.PullToRefreshView;

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

    @InjectView(R.id.swipe_layout)
    PullToRefreshView swipeLayout;

    public WeatherFragmentView() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterImpl = new WeatherPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, view);
        presenterImpl.start();
        swipeLayout.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterImpl.refreshWeatherData();
            }
        });
        return view;
    }

    @Override
    public void showProgressHideData() {
        swipeLayout.setRefreshing(true);
        weatherLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressShowData() {
        swipeLayout.setRefreshing(false);
        weatherLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void setToday(WeatherBean weatherBean) {
        String cityName = weatherBean.getCity();
        String date = weatherBean.getDate();
        String wind = weatherBean.getWind();
        String temperature = weatherBean.getLowTemperature().substring(2) + "~" + weatherBean.getHighTemperature().substring(2);
        String type = weatherBean.getWeatherType();
        int imgResource = weatherBean.getImgResource();
        this.city.setText(cityName);
        this.today.setText(date);
        this.wind.setText(wind);
        this.weatherTemp.setText(temperature);
        this.weather.setText(type);
        this.weatherImage.setImageResource(imgResource);
    }

    @Override
    public void setForecast(List<WeatherBean> weatherBeans) {
        weatherForecast.removeAllViews();
        for (WeatherBean weatherBean : weatherBeans) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_weather, null, false);

            TextView dateTV = (TextView) view.findViewById(R.id.date);
            ImageView todayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
            TextView todayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
            TextView todayWindTV = (TextView) view.findViewById(R.id.wind);
            TextView todayWeatherTV = (TextView) view.findViewById(R.id.weather);

            dateTV.setText(weatherBean.getDate());
            todayTemperatureTV.setText(weatherBean.getLowTemperature().substring(2) + "~" + weatherBean.getHighTemperature().substring(2));
            todayWindTV.setText(weatherBean.getWind());
            todayWeatherTV.setText(weatherBean.getWeatherType());
            todayWeatherImage.setImageResource(weatherBean.getImgResource());
            weatherForecast.addView(view);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden){
            swipeLayout.setRefreshing(false);
        }else {
            if (weatherLayout.getVisibility()!=View.VISIBLE){
                swipeLayout.setRefreshing(true);
                presenterImpl.refreshWeatherData();
            }
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
