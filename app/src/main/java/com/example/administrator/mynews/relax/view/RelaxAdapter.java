package com.example.administrator.mynews.relax.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.RelaxBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class RelaxAdapter extends RecyclerView.Adapter<RelaxAdapter.RelaxViewHolder> {
    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    private OnItemClickListener listener;

    private Context context;
    private List<RelaxBean> dataList;

    public RelaxAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<RelaxBean> list) {
        dataList = list;
    }

    @Override
    public RelaxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelaxViewHolder holder = new RelaxViewHolder(LayoutInflater.from(context).inflate(R.layout.item_relax, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RelaxViewHolder holder, int position) {
        holder.tvRelaxDigest.setText(dataList.get(position).getDigest());
        holder.tvRelaxTitle.setText(dataList.get(position).getTitle());
        holder.tvRelaxReply.setText(dataList.get(position).getCommentCount());
        Glide.with(context)
                .load(dataList.get(position).getImgsrc())
                .asBitmap()
                .into(holder.ivRelaxImg);

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class RelaxViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivRelaxImg;
        private TextView tvRelaxTitle;
        private TextView tvRelaxDigest;
        private CardView relaxCardView;
        private TextView tvRelaxReply;



        public RelaxViewHolder(View itemView) {
            super(itemView);
            ivRelaxImg = (ImageView) itemView.findViewById(R.id.iv_relax_img);
            tvRelaxTitle = (TextView) itemView.findViewById(R.id.tv_relax_titlle);
            tvRelaxDigest = (TextView) itemView.findViewById(R.id.tv_relax_digest);
            relaxCardView = (CardView) itemView.findViewById(R.id.relax_card_view);
            tvRelaxReply = (TextView) itemView.findViewById(R.id.tv_relax_reply);

            relaxCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }
}
