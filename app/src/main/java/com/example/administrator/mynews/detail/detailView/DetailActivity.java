package com.example.administrator.mynews.detail.detailView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.DetailBean;
import com.example.administrator.mynews.detail.detailPresenter.DetailPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DetailActivity extends Activity implements DetailViewImpl {
    @InjectView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @InjectView(R.id.rv_detail_content)
    RecyclerView rvDetailContent;

    private List<String> list = new ArrayList<String>();
    private DetailAdapter adapter;

    private DetailPresenter detailPresenter;
    private String docid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        docid=getIntent().getStringExtra("docid");
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);

        detailPresenter = new DetailPresenter(this);
        detailPresenter.start(docid);
        rvDetailContent.setLayoutManager(new LinearLayoutManager(this));
        adapter=new DetailAdapter(this);
        rvDetailContent.setAdapter(adapter);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadDetail(DetailBean detailBean) {
        tvDetailTitle.setText(detailBean.getTitle());
        adapter.setData(detailBean);
        adapter.notifyDataSetChanged();
    }
}
