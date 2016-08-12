package com.example.administrator.mynews.detail.detailView;

import com.example.administrator.mynews.beans.DetailBean;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public interface DetailViewImpl {
    void showProgress();
    void hideProgress();
    void loadDetail(DetailBean detailBean);
}
