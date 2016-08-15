package com.example.administrator.mynews.giflist.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.beans.JokeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class GifsAdapter extends RecyclerView.Adapter<GifsAdapter.GifsViewHolder> {
    interface  ClickListener{
        void onClick(String gallery_id);
    }
    public void setClickListener(ClickListener listener){
        this.listener=listener;
    }
    private static final String TAG = "GifsAdapter";
    private Context context;
    private List<GifListBean> dataList;
    private int width;
    private ClickListener listener;
    public GifsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<GifListBean> list) {
        dataList = list;
    }

    @Override
    public GifsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        width = parent.getWidth()/2;

        GifsViewHolder holder = new GifsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gifs, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final GifsViewHolder holder, final int position) {
        holder.tvGifContent.setText(dataList.get(position).getTitle());
        holder.layoutGifsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(dataList.get(position).getGallery_id());
            }
        });
        Glide.with(context)
                .load(dataList.get(position).getCover_url())
                .asBitmap()
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Glide.with(context)
                                .load(dataList.get(position).getCover_url())
                                .asBitmap()
                                .override(width, width * resource.getHeight() / resource.getWidth())
                                .into(holder.ivGifImg);
                        return false;
                    }
                }).into(holder.ivGifImg);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class GifsViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGifImg;
        private TextView tvGifContent;
        private CardView layoutGifsList;


        public GifsViewHolder(View itemView) {
            super(itemView);
            ivGifImg = (ImageView) itemView.findViewById(R.id.iv_gif_img);
            tvGifContent = (TextView) itemView.findViewById(R.id.tv_gif_content);
            layoutGifsList = (CardView) itemView.findViewById(R.id.layout_gifs_list);
        }
    }
}
