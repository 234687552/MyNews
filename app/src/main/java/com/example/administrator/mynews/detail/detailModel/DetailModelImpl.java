package com.example.administrator.mynews.detail.detailModel;

import com.example.administrator.mynews.beans.DetailBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public interface DetailModelImpl {
    interface LoadDetailListener{
        void onSucceed(DetailBean detailBean);
        void onFail(String msg);
    }
    void loadDetail(String docid,LoadDetailListener listener);
}
