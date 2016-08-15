package com.example.administrator.mynews.giflist.view;

import com.example.administrator.mynews.beans.GifListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public interface GifListViewImpl {
    void loadGifList(List<GifListBean> list);
    void loadMore(List<GifListBean> list);
    void showProgress();
    void hideProgress();
    void click2PhotoSets(String gallery_id);
}
