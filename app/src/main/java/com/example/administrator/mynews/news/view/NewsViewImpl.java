package com.example.administrator.mynews.news.view;

import com.example.administrator.mynews.beans.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface NewsViewImpl {
    void hideProgress();
    void showProgress();
    void refreshNewsList(List<NewsBean> list);
    void loadMoreNewsList();
    void click2Detail(NewsBean item);
    void click2Photosets(NewsBean item);
    void click2Specials(NewsBean item);
}
