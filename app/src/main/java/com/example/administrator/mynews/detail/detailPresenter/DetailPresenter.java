package com.example.administrator.mynews.detail.detailPresenter;

import com.example.administrator.mynews.beans.DetailBean;
import com.example.administrator.mynews.detail.detailModel.DetailModel;
import com.example.administrator.mynews.detail.detailModel.DetailModelImpl;
import com.example.administrator.mynews.detail.detailView.DetailActivity;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DetailPresenter implements DetailPresenterImpl {
    private static final String TAG = "DetailPresenter";
    private DetailActivity detailView;
    private DetailModel detailModel;
    private String docid;

    public DetailPresenter(DetailActivity detailView) {
        this.detailView = detailView;
        detailModel=new DetailModel();
    }


    @Override
    public void start(String docid) {
        this.docid = docid;
        detailView.showProgress();
        loadDetail();
    }

    @Override
    public void loadDetail() {
        detailModel.loadDetail(docid, new DetailModelImpl.LoadDetailListener() {
            @Override
            public void onSucceed(DetailBean detailBean) {
                detailView.loadDetail(detailBean);
                detailView.hideProgress();
            }

            @Override
            public void onFail(String msg) {
                detailView.hideProgress();
            }
        });
    }
}
