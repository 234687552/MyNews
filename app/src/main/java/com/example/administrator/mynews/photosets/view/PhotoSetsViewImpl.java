package com.example.administrator.mynews.photosets.view;

import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface PhotoSetsViewImpl {
    void showProgress();
    void hideProgress();
    void loadPhotoSets(PhotoSetsBean photoSetsBean,List<GifBean> gifBeans);
    void setNote(int position);
}
