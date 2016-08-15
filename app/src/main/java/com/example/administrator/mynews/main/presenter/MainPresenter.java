package com.example.administrator.mynews.main.presenter;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.main.view.MainActivity;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class MainPresenter implements MainPresenterImpl{
    private MainActivity mainView;

    public MainPresenter(MainActivity mainView) {
        this.mainView = mainView;
    }

    @Override
    public void star() {
        mainView.Switch2News();
    }

    @Override
    public void switch2Type(int type) {
        switch (type){
            case R.id.navigation_item_jokes:
                mainView.Switch2Jokes();
                break;
            case R.id.navigation_item_news:
                mainView.Switch2News();
                break;
            case R.id.navigation_item_weather:
                mainView.Switch2Weather();
                break;
            case R.id.navigation_item_relaxs:
                mainView.Switch2Relaxs();
                break;
            case R.id.navigation_item_gifs:
                mainView.Switch2Gifs();
                break;
            default:
                mainView.Switch2News();
                break;
        }
    }
}
