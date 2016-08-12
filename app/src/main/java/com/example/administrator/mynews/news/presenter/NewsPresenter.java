package com.example.administrator.mynews.news.presenter;

import com.example.administrator.mynews.news.view.NewsFragment;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class NewsPresenter implements NeswPresenterImpl {
    private static final String TAG = "NewsPresenter";
    @Override
    public void loadNewsList(int startIndex) {
        // TODO: 2016/8/12 0012
    }

    @Override
    public void start() {
        loadNewsList(0);
    }
}
