package com.example.administrator.mynews.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.weather.presenter.WeatherPresenter;
import com.example.administrator.mynews.weather.view.WeatherFragmentView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private WeatherFragmentView weatherFragment;
    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherFragment = new WeatherFragmentView();
        weatherPresenter = new WeatherPresenter(weatherFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, weatherFragment).commit();


    }

}
