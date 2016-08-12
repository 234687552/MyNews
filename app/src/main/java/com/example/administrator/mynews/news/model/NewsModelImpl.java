package com.example.administrator.mynews.news.model;

import com.example.administrator.mynews.beans.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface NewsModelImpl {
    interface LoadNewsListListener {
        void onSucceed(List<NewsBean> list);
        void onFail(String msg);
    }
    void loadNewsList(int starIndex,LoadNewsListListener listener);
}
