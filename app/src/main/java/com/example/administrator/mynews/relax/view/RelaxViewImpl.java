package com.example.administrator.mynews.relax.view;

import com.example.administrator.mynews.beans.RelaxBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public interface RelaxViewImpl {
    void hideProgress();
    void showProgress();
    void refreshArtiList(List<RelaxBean> list);
    void loadMoreArtiList();
    void click2Detail(String docid);
}
