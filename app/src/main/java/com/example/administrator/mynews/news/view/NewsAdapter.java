package com.example.administrator.mynews.news.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.beans.RelaxBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private static final String TAG = "NewsAdapter";
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    private OnItemClickListener listener;

    private Context context;
    private List<NewsBean> dataList;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsBean> list) {
        dataList = list;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsViewHolder holder = new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.tvNewsTitlle.setText(dataList.get(position).getTitle());
        holder.tvNewsReply.setText(dataList.get(position).getReplyCount());
        String type=dataList.get(position).getSkipType();
        holder.tvNewsType.setText("");
        if (type.length()>0) {
            holder.tvNewsType.setText(type.equals("special")?"专题":"图集");
        }
        Glide.with(context)
                .load(dataList.get(position).getImgsrc())
                .asBitmap()
                .into(holder.ivNewsImg);

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        private CardView newsCardView;
        private ImageView ivNewsImg;
        private TextView tvNewsTitlle;
        private TextView tvNewsReply;
        private TextView tvNewsType;



        public NewsViewHolder(View itemView) {
            super(itemView);
            tvNewsType = (TextView) itemView.findViewById(R.id.tv_news_type);
            newsCardView = (CardView) itemView.findViewById(R.id.news_card_view);
            ivNewsImg = (ImageView) itemView.findViewById(R.id.iv_news_img);
            tvNewsTitlle = (TextView) itemView.findViewById(R.id.tv_news_titlle);
            tvNewsReply = (TextView) itemView.findViewById(R.id.tv_news_reply);


            newsCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
