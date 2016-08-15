package com.example.administrator.mynews.photosets.presenter;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public interface PhotoSetsPresenterImpl {
    void start(String photoID,String type);
    void loadPhotoInfo();
    void changeNote(int position);

}
