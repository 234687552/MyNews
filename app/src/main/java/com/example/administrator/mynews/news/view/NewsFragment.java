package com.example.administrator.mynews.news.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.detail.detailView.DetailActivity;
import com.example.administrator.mynews.news.presenter.NewsPresenter;
import com.example.administrator.mynews.photosets.view.PhotoSetActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class NewsFragment extends Fragment implements NewsViewImpl {

    private static final String TAG = "NewsFragment";
    @InjectView(R.id.rv_news)
    RecyclerView rvNews;
    @InjectView(R.id.newsRefreshLayout)
    PullRefreshLayout newsRefreshLayout;

    private NewsPresenter newsPresenter;
    private NewsAdapter adapter;
    private List<NewsBean> newsList=new ArrayList<NewsBean>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsPresenter = new NewsPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.inject(this, view);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new NewsAdapter(getContext());
        adapter.setData(newsList);
        rvNews.setAdapter(adapter);
        newsPresenter.start();
        setOnListener();
        return view;
    }
    private void setOnListener() {
        newsRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsPresenter.loadNewsList(0);
            }
        });
       adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {
            newsPresenter.click2Type(newsList.get(position));
           }
       });
    }
    @Override
    public void hideProgress() {
        newsRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        newsRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshNewsList(List<NewsBean> list) {
        newsList.clear();
        newsList.addAll(list);
        adapter.setData(newsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreNewsList() {

    }

    @Override
    public void click2Detail(NewsBean item) {
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("docid",item.getPostid());
        getActivity().startActivity(intent);
    }

    @Override
    public void click2Photosets(NewsBean item) {
        Intent intent=new Intent(getActivity(), PhotoSetActivity.class);
        String[] photosetID=item.getPhotosetID().split("\\|");
        intent.putExtra("photosetID",
                photosetID[0].substring(photosetID[0].length()-4,photosetID[0].length())+"/"+photosetID[1]);
        getActivity().startActivity(intent);
    }

    @Override
    public void click2Specials(NewsBean item) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
