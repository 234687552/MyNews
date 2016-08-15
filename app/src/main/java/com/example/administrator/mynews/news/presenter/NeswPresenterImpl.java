package com.example.administrator.mynews.news.presenter;

import com.example.administrator.mynews.beans.NewsBean;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface NeswPresenterImpl {
    void loadNewsList(int startIndex);
    void start();
    void click2Type(NewsBean item);
}
