package com.example.administrator.mynews.giflist.presenter;

import android.util.Log;

import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.giflist.model.GifListListModel;
import com.example.administrator.mynews.giflist.model.GifListModelImpl;
import com.example.administrator.mynews.giflist.view.GifListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GifListPresenter implements GifListPresenterImpl{
    private static final String TAG = "GifListPresenter";
    private GifListFragment gifListView;
    private GifListListModel gifListListModel=new GifListListModel();

    public GifListPresenter(GifListFragment gifListView) {
        this.gifListView = gifListView;
    }

    @Override
    public void start() {
        loadGifList();
        gifListView.showProgress();
    }

    @Override
    public void loadGifList() {
        gifListListModel.loadGifList(new GifListModelImpl.LoadGifListener() {
            @Override
            public void onSucceed(List<GifListBean> list) {
                gifListView.loadGifList(list);
                gifListView.hideProgress();
            }

            @Override
            public void onFail(String msg) {
                Log.w(TAG, "onFail: "+msg );
                gifListView.hideProgress();
            }
        });
    }

    @Override
    public void click2PhotoSets(String gifId) {
        gifListView.click2PhotoSets(gifId);
    }
}
