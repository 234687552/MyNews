package com.example.administrator.mynews.jokes.view;

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
import com.example.administrator.mynews.beans.JokeBean;
import com.example.administrator.mynews.jokes.presenter.JokePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class JokeFragment extends Fragment implements JokeViewImpl {
    private static final String TAG = "JokeFragment";
    @InjectView(R.id.rv_jokes)
    RecyclerView rvJokes;
    @InjectView(R.id.jokeRefreshLayout)
    PullRefreshLayout jokeRefreshLayout;

    private JokesAdapter adapter;
    private List<JokeBean> jokeList = new ArrayList<JokeBean>();
    private JokePresenter jokePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jokePresenter = new JokePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jokes, container, false);
        ButterKnife.inject(this, view);
        rvJokes.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new JokesAdapter(getContext());
        adapter.setData(jokeList);
        rvJokes.setAdapter(adapter);

        jokePresenter.start();
        setOnListener();
        return view;
    }


    private void setOnListener() {
        jokeRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                jokePresenter.loadJokes();
            }
        });
    }


    @Override
    public void loadMoreJokes(List<JokeBean> list) {
    }

    @Override
    public void refreshJokes(List<JokeBean> list) {
        jokeList.clear();
        jokeList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        jokeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        jokeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            if(jokeList==null&&!(jokeList.size() >0)){
                jokePresenter.loadJokes();
            }
        }else
            jokeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
