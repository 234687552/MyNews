package com.example.administrator.mynews.news.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.news.model.NewsModel;
import com.example.administrator.mynews.news.model.NewsModelImpl;
import com.example.administrator.mynews.news.view.NewsFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class NewsPresenter implements NeswPresenterImpl {
    private static final String TAG = "NewsPresenter";
    private NewsFragment newsView;
    private NewsModel newsModel = new NewsModel();

    public NewsPresenter(NewsFragment newsView) {
        this.newsView = newsView;
    }

    @Override
    public void loadNewsList(int startIndex) {
        newsModel.loadNewsList(startIndex, new NewsModelImpl.LoadNewsListListener() {
            @Override
            public void onSucceed(List<NewsBean> list) {

                newsView.refreshNewsList(list);
                newsView.hideProgress();
            }

            @Override
            public void onFail(String msg) {
                newsView.hideProgress();
                Log.w(TAG, "onFail: " + msg);
            }
        });
    }

    @Override
    public void start() {
        loadNewsList(0);
        newsView.showProgress();
    }

    @Override
    public void click2Type(NewsBean item) {
        Log.w(TAG, "click2Type: " + item.getSkipType());
        switch (item.getSkipType()) {
            case "special":
                newsView.click2Specials(item);
                break;
            case "photoset":
                newsView.click2Photosets(item);
                break;
            default:
                newsView.click2Detail(item);
                break;
        }
    }
}
