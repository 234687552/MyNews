package com.example.administrator.mynews.giflist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.giflist.presenter.GifListPresenter;
import com.example.administrator.mynews.photosets.view.PhotoSetActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GifListFragment extends Fragment implements GifListViewImpl {
    private static final String TAG = "GifListFragment";
    @InjectView(R.id.rv_gifs)
    RecyclerView rvGifs;
    @InjectView(R.id.gifRefreshLayout)
    PullRefreshLayout gifRefreshLayout;

    private GifsAdapter adapter;
    private GifListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new GifsAdapter(getContext());
        presenter=new GifListPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifs, container, false);
        ButterKnife.inject(this, view);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rvGifs.setLayoutManager(manager);
        rvGifs.setAdapter(adapter);
        rvGifs.setItemAnimator(null);
        presenter.start();
        setTouchListener();
        return view;
    }

    private void setTouchListener() {
        adapter.setClickListener(new GifsAdapter.ClickListener() {
            @Override
            public void onClick(String gallery_id) {
                presenter.click2PhotoSets(gallery_id);
            }
        });
    }


    @Override
    public void loadGifList(final List<GifListBean> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMore(List<GifListBean> list) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void click2PhotoSets(String gallery_id) {
        Intent intent=new Intent(getActivity(), PhotoSetActivity.class);
        intent.putExtra("gallery_id",gallery_id);
        getActivity().startActivity(intent);
        Log.w(TAG, "click2PhotoSets: "+gallery_id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden&&!(adapter.getItemCount() >0)){
            presenter.start();
        }
    }
}
