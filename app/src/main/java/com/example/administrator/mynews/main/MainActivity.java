package com.example.administrator.mynews.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.detail.detailView.DetailActivity;
import com.example.administrator.mynews.jokes.view.JokeFragment;
import com.example.administrator.mynews.main.presenter.MainPresenter;
import com.example.administrator.mynews.main.view.MainViewImpl;
import com.example.administrator.mynews.relax.view.RelaxFragment;
import com.example.administrator.mynews.utils.RetrofitUtil;
import com.example.administrator.mynews.weather.view.WeatherFragmentView;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity implements MainViewImpl {
    private static final String TAG = "MainActivity";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.navigation)
    NavigationView navigation;
    // Fragment事务
    FragmentTransaction transaction;
    //toolbar的展开按钮
    private ActionBarDrawerToggle drawerToggle;

    private MainPresenter presenter;

    private WeatherFragmentView weatherFragment;
    private JokeFragment jokeFragment;
    private RelaxFragment relaxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);


        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_toggle_open, R.string.drawer_toggle_close);//new一个展开按钮，后两个参数没什么用
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);//绑定展开按钮与drawerlayout一起

        presenter = new MainPresenter(this);
        presenter.star();

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                presenter.switch2Type(item.getItemId());
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void Switch2Weather() {
        toolbar.setTitle(R.string.weather);
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        hideAllFragments(transaction);
        if (weatherFragment == null) {
            weatherFragment = new WeatherFragmentView();
        }
        if (!weatherFragment.isAdded()) {
            transaction.add(R.id.container, weatherFragment);
        } else {
            transaction.show(weatherFragment);
        }
        transaction.commit();
    }

    @Override
    public void Switch2Jokes() {
        toolbar.setTitle(R.string.jokes);
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        hideAllFragments(transaction);
        if (jokeFragment == null) {
            jokeFragment = new JokeFragment();
        }
        if (!jokeFragment.isAdded()) {
            transaction.add(R.id.container, jokeFragment);
        } else {
            transaction.show(jokeFragment);
        }
        transaction.commit();
    }

    @Override
    public void Switch2News() {
    }


    @Override
    public void Switch2Relaxs() {
        toolbar.setTitle(R.string.relax);
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        hideAllFragments(transaction);
        if (relaxFragment == null) {
            relaxFragment = new RelaxFragment();
        }
        if (!relaxFragment.isAdded()) {
            transaction.add(R.id.container, relaxFragment);
        } else {
            transaction.show(relaxFragment);
        }
        transaction.commit();
    }


    /**
     * 切换页面时候先隐藏所有页面
     */
    public void hideAllFragments(FragmentTransaction transaction) {
        if (jokeFragment != null) {
            transaction.hide(jokeFragment);
        }
        if (weatherFragment != null) {
            transaction.hide(weatherFragment);
        }
        if (relaxFragment != null) {
            transaction.hide(relaxFragment);
        }
    }
}
