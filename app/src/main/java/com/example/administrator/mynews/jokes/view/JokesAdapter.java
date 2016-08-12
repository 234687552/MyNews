package com.example.administrator.mynews.jokes.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.JokeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {
    private Context context;
    private List<JokeBean > dataList;
    public JokesAdapter(Context context) {
        this.context=context;
    }
    public void setData(List<JokeBean> list){
        dataList=list;
    }

    @Override
    public JokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        JokesViewHolder holder=new JokesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_jokes,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(JokesViewHolder holder, int position) {
        holder.tvContent.setText(dataList.get(position).getDigest());
        holder.tvUpTimes.setText(""+dataList.get(position).getUpTimes());
        holder.tvDownTimes.setText(""+dataList.get(position).getDownTimes());
        holder.tvReplyCount.setText("" + dataList.get(position).getReplyCount());
        if (dataList.get(position).getImg()!=null){
            holder.ivJokeImg.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(dataList.get(position).getImg())
                    .asBitmap()
                    .into(holder.ivJokeImg);
        }else
            holder.ivJokeImg.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return dataList==null?0:dataList.size();
    }

    class JokesViewHolder extends RecyclerView.ViewHolder{
        private TextView tvContent;
        private TextView tvUpTimes;
        private TextView tvDownTimes;
        private TextView tvReplyCount;
        private ImageView ivJokeImg;

        public JokesViewHolder(View itemView) {
            super(itemView);
            tvContent= (TextView) itemView.findViewById(R.id.tv_joke_content);
            tvUpTimes= (TextView) itemView.findViewById(R.id.tv_joke_good);
            tvDownTimes= (TextView) itemView.findViewById(R.id.tv_joke_bad);
            tvReplyCount= (TextView) itemView.findViewById(R.id.tv_joke_reply);
            ivJokeImg= (ImageView) itemView.findViewById(R.id.iv_joke_img);
        }
    }
}
