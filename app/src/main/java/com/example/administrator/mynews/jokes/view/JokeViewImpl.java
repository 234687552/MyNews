package com.example.administrator.mynews.jokes.view;

import com.example.administrator.mynews.beans.JokeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public interface JokeViewImpl {
    void loadMoreJokes(List<JokeBean> list);
    void refreshJokes(List<JokeBean> list);
    void showProgress();
    void hideProgress();


}
