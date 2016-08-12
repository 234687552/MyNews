package com.example.administrator.mynews.jokes.model;

import com.example.administrator.mynews.beans.JokeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public interface JokeModelImpl {
    interface LoadJokeInfoListener{
        void onSucceed(List<JokeBean> jokeBeans);
        void onFail(String msg);
    }
    void loadJokeInfoFromService(String type,LoadJokeInfoListener listener );
}
