package com.example.administrator.mynews.detail.detailView;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.DetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_IMAGE = 0;
    private static final int ITEM_TYPE_TEXT = 1;

    private static final String TAG = "DetailAdapter";
    private Context context;
    private DetailBean detailBean;
    private List<String> dataList = new ArrayList<String>();
    private int width=10;

    public DetailAdapter(Context context) {
        this.context = context;
    }

    public void setData(DetailBean detailBean) {
        this.detailBean = detailBean;
        dataList.clear();
        dataList.addAll(detailBean.getBodyLists());

    }

    private String[] getPixel(String imgRef){
        for (int i = 0; i < detailBean.getImg().size(); i++) {
            if (detailBean.getImg().get(i).getRef().contains(imgRef)) {
                return detailBean.getImg().get(i).getPixel().split("\\*");
            }
        }
        return null;
    }
    private String getImgUrl(String imgRef) {
        for (int i = 0; i < detailBean.getImg().size(); i++) {
            if (detailBean.getImg().get(i).getRef().contains(imgRef)) {
                return detailBean.getImg().get(i).getSrc();
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).contains("IMG#") ? ITEM_TYPE_IMAGE : ITEM_TYPE_TEXT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        width=parent.getWidth();
        if (viewType == ITEM_TYPE_IMAGE) {
            return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_img, parent, false));
        } else {
            return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageViewHolder) {
            String[] pixel=getPixel(dataList.get(position));
            ImageView imageView = ((ImageViewHolder) holder).ivDetailItem;
            String url=getImgUrl(dataList.get(position));
            int imgWidth=width;
            int imgHeight=width*Integer.valueOf(pixel[1])/Integer.valueOf(pixel[0]);
            if (url.contains("gif")){
                Glide.with(context)
                        .load(url)
                        .asGif()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .override(imgWidth, imgHeight)
                        .into(imageView);
            }
            else {
                Glide.with(context)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .override(imgWidth, imgHeight)
                        .into(imageView);
            }

        } else if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).tvDetailItem.setText(Html.fromHtml(dataList.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDetailItem;

        public TextViewHolder(View itemView) {
            super(itemView);
            tvDetailItem = (TextView) itemView.findViewById(R.id.tv_detail_item);
        }

    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDetailItem;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ivDetailItem = (ImageView) itemView.findViewById(R.id.iv_detail_item);

        }

    }
}
