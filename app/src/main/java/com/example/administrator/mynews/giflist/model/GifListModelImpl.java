package com.example.administrator.mynews.giflist.model;

import com.example.administrator.mynews.beans.GifListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public interface GifListModelImpl {
    interface LoadGifListener{
        void onSucceed(List<GifListBean> list);
        void onFail(String msg);
    }
    void loadGifList(LoadGifListener listener);
}
