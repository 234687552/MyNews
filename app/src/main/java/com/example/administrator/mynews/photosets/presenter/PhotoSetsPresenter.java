package com.example.administrator.mynews.photosets.presenter;

import android.util.Log;

import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;
import com.example.administrator.mynews.photosets.model.PhotoSetsModel;
import com.example.administrator.mynews.photosets.model.PhotoSetsModelImpl;
import com.example.administrator.mynews.photosets.view.PhotoSetActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class PhotoSetsPresenter implements PhotoSetsPresenterImpl {
    private static final String TAG = "PhotoSetsPresenter";
    private PhotoSetActivity photoView;
    private String photosetID;
    private String gifID;
    private String type;
    private PhotoSetsModel photoSetsModel = new PhotoSetsModel();

    public PhotoSetsPresenter(PhotoSetActivity photoView) {
        this.photoView = photoView;
    }

    @Override
    public void start(String photosetID, String type) {

        photoView.showProgress();
        if (type.equals("gif")) {
            this.gifID = photosetID;
        } else {
            this.photosetID = photosetID;
        }
        this.type = type;
        loadPhotoInfo();

    }

    @Override
    public void loadPhotoInfo() {
        if (!type.equals("gif")) {
            photoSetsModel.loadPhotoInfo(photosetID, new PhotoSetsModelImpl.LoadPhotoInfoListener() {

                @Override
                public void onSucceed(PhotoSetsBean photoSetsBean) {
                    photoView.loadPhotoSets(photoSetsBean,null);
                    photoView.hideProgress();
                }

                @Override
                public void onFail(String msg) {
                    photoView.hideProgress();
                    Log.e(TAG, "onFail: " + msg);
                }
            });
        } else {
            photoSetsModel.loadGifs(gifID, new PhotoSetsModelImpl.LoadGifInfoListener() {
                @Override
                public void onSucceed(List<GifBean> gifBeans) {
                    photoView.loadPhotoSets(null, gifBeans);
                    photoView.hideProgress();
                }

                @Override
                public void onFail(String msg) {
                    photoView.hideProgress();
                    Log.e(TAG, "onFail: " + msg);
                }
            });
        }
    }

    @Override
    public void changeNote(int position) {
        photoView.setNote(position);
    }

}
