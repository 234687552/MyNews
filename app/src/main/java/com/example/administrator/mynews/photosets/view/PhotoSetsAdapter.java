package com.example.administrator.mynews.photosets.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class PhotoSetsAdapter extends PagerAdapter {
    interface ClickListener {
        void onClick();
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private static final String TAG = "PhotoSetsAdapter";
    private PhotoSetsBean photoSetsBean;
    private List<GifBean> gifBeans;
    private Context context;
    private List<ImageView> imgViews;
    private ClickListener listener;

    public PhotoSetsAdapter(Context context) {
        this.context = context;
        imgViews = new ArrayList<ImageView>();
    }

    public void setData(PhotoSetsBean photoSetsBean, List<GifBean> gifBeans) {
        this.photoSetsBean = photoSetsBean;
        this.gifBeans = gifBeans;
    }

    @Override
    public int getCount() {
        return photoSetsBean == null ? (gifBeans == null ? 0 : gifBeans.size())
                : photoSetsBean.getPhotos().size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgView;
        int width=container.getWidth();
        if (imgViews.size() <= position) {
            imgView = new ImageView(context);
            imgViews.add(imgView);
        } else {
            imgView = imgViews.get(position);
        }
        String url = photoSetsBean == null ? gifBeans.get(position).getUrl()
                : photoSetsBean.getPhotos().get(position).getImgurl();

        if (url.contains("gif")) {
            int height=width*gifBeans.get(position).getFile_height()/gifBeans.get(position).getFile_width();
            Log.w(TAG, "url: "+url );
            Log.w(TAG, "instantiateItem: "+width+","+height );
            Glide.with(context).load(url)
                    .asGif()
                    .into(imgView);
        } else {
            Glide.with(context).load(url)
                    .asBitmap()
                    .into(imgView);
        }
        container.addView(imgView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
        return imgView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgViews.get(position));
    }

}
