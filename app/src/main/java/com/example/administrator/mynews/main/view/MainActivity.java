package com.example.administrator.mynews.main.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.giflist.model.GifListListModel;
import com.example.administrator.mynews.giflist.view.GifListFragment;
import com.example.administrator.mynews.jokes.view.JokeFragment;
import com.example.administrator.mynews.main.presenter.MainPresenter;
import com.example.administrator.mynews.news.view.NewsFragment;
import com.example.administrator.mynews.relax.view.RelaxFragment;
import com.example.administrator.mynews.weather.view.WeatherFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


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

    private List<Fragment> fragments=new ArrayList<Fragment>();
    private WeatherFragmentView weatherFragment;
    private JokeFragment jokeFragment;
    private RelaxFragment relaxFragment;
    private NewsFragment newsFragment;
    private GifListFragment gifsFragment;

    private GifListListModel gifListListModel;
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


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                presenter.switch2Type(item.getItemId());
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
        startFragment();
        presenter.star();
    }

    private void startFragment() {
        fragments.add(new WeatherFragmentView());  //0 天气
        fragments.add(new JokeFragment());       //1 段子
        fragments.add(new NewsFragment());       //2 头条
        fragments.add(new RelaxFragment());      //3 轻松
        fragments.add(new GifListFragment());    //4 gif
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        for (Fragment fragment : fragments) {
            transaction.add(R.id.container, fragment);
        }
        transaction.commit();
    }

    @Override
    public void Switch2Weather() {
        toolbar.setTitle(R.string.weather);
        hideAllFragments();
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        transaction.show(fragments.get(0)).commit();
    }

    @Override
    public void Switch2Jokes() {
        toolbar.setTitle(R.string.jokes);
        hideAllFragments();
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        transaction.show(fragments.get(1)).commit();
    }

    @Override
    public void Switch2News() {
        toolbar.setTitle(R.string.news);
        hideAllFragments();
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        transaction.show(fragments.get(2)).commit();
    }


    @Override
    public void Switch2Relaxs() {
        toolbar.setTitle(R.string.relax);
        hideAllFragments();
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        transaction.show(fragments.get(3)).commit();
    }

    @Override
    public void Switch2Gifs() {
        toolbar.setTitle(R.string.gifs);
        hideAllFragments();
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment
        transaction.show(fragments.get(4)).commit();
    }


    /**
     * 切换页面时候先隐藏所有页面
     */
    public void hideAllFragments() {
        transaction = getSupportFragmentManager().beginTransaction();//开启Fragment事物管理Fragment

        for (Fragment fragment : fragments) {
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }
    //双击退出
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
