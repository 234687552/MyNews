package com.example.administrator.mynews.relax.model;

import com.example.administrator.mynews.beans.RelaxBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public interface RelaxModelImpl {
    interface LoadArtilistListener{
        void onSucceed(List<RelaxBean> list);
        void onFail(String msg);
    }
    void loadArtiList(int starIndex,LoadArtilistListener listener);
}
