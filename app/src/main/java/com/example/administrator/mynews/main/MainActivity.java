package com.example.administrator.mynews.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.weather.presenter.WeatherPresenter;
import com.example.administrator.mynews.weather.view.WeatherFragmentView;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.drawer_layout)
    LeftDrawerLayout drawerLayout;

    private WeatherFragmentView weatherFragment;
    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        weatherFragment = new WeatherFragmentView();
        weatherPresenter = new WeatherPresenter(weatherFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, weatherFragment).commit();


        MyMenuFragment mMenuFragment = (MyMenuFragment) getSupportFragmentManager().findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }
        FlowingView mFlowingView= (FlowingView) findViewById(R.id.flow_view);
        drawerLayout.setFluidView(mFlowingView);
        drawerLayout.setMenuFragment(mMenuFragment);

    }

}
