package com.example.administrator.mynews.photosets.model;


import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface PhotoSetsModelImpl {
    interface LoadPhotoInfoListener{
        void onSucceed(PhotoSetsBean photoSetsBean);
        void onFail(String msg);
    }
    interface LoadGifInfoListener{
        void onSucceed(List<GifBean> gifBeans);
        void onFail(String msg);
    }
    void  loadPhotoInfo(String photosetID,LoadPhotoInfoListener listener);
    void loadGifs(String gid,LoadGifInfoListener listener);
}
