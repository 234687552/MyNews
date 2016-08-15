package com.example.administrator.mynews.photosets.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;
import com.example.administrator.mynews.photosets.presenter.PhotoSetsPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class PhotoSetActivity extends Activity implements PhotoSetsViewImpl {
    private static final String TAG = "PhotoSetActivity";
    @InjectView(R.id.vp_photosets)
    ViewPager vpPhotosets;
    @InjectView(R.id.tv_photosets_title)
    TextView tvPhotosetsTitle;
    @InjectView(R.id.tv_photosets_index)
    TextView tvPhotosetsIndex;
    @InjectView(R.id.tv_photosets_note)
    TextView tvPhotosetsNote;
    @InjectView(R.id.layout_photosets_note)
    LinearLayout layoutPhotosetsNote;


    private PhotoSetsAdapter adapter;
    private String photosetID;
    private String gifID;
    private PhotoSetsPresenter presenter;


    private PhotoSetsBean photoBean;
    List<GifBean> gifBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photosets);
        ButterKnife.inject(this);
        adapter = new PhotoSetsAdapter(this);

        vpPhotosets.setAdapter(adapter);
        presenter = new PhotoSetsPresenter(this);
        photosetID = getIntent().getStringExtra("photosetID");
        gifID=getIntent().getStringExtra("gallery_id");
        if (photosetID != null) {
            presenter.start(photosetID,"photoSets");
        }else{
            presenter.start(gifID,"gif");
        }

        setTouchListener();
    }

    private void setTouchListener() {
        vpPhotosets.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.w(TAG, "onPageSelected: " + position);
                presenter.changeNote(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter.setClickListener(new PhotoSetsAdapter.ClickListener() {
            @Override
            public void onClick() {
                layoutPhotosetsNote.setVisibility(layoutPhotosetsNote.getVisibility() == View.GONE ?
                        View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void loadPhotoSets(PhotoSetsBean photoSetsBean,List<GifBean> gifBeans) {

        if (photosetID != null){
            photoBean=photoSetsBean;
            adapter.setData(photoSetsBean,null);
        }else {
            this.gifBeans=gifBeans;
            adapter.setData(null,gifBeans);
        }
        adapter.notifyDataSetChanged();
        setNote(0);
    }

    @Override
    public void setNote(int position) {
        if (photosetID != null){
            tvPhotosetsTitle.setText(photoBean.getSetname());
            tvPhotosetsIndex.setText(String.valueOf(position+1)+"/"+photoBean.getPhotos().size());
            tvPhotosetsNote.setText(photoBean.getPhotos().get(position).getNote());
        }
        else{
            tvPhotosetsTitle.setText(gifBeans.get(position).getAdd_intro());
            tvPhotosetsIndex.setText(String.valueOf(position+1)+"/"+gifBeans.size());
            tvPhotosetsNote.setVisibility(View.GONE);
        }
    }

}
