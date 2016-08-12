package com.example.administrator.mynews.relax.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.example.administrator.mynews.R;
import com.example.administrator.mynews.beans.RelaxBean;
import com.example.administrator.mynews.detail.detailView.DetailActivity;
import com.example.administrator.mynews.relax.presenter.RelaxPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class RelaxFragment extends Fragment implements RelaxViewImpl {

    @InjectView(R.id.rv_relax)
    RecyclerView rvRelax;
    @InjectView(R.id.relaxRefreshLayout)
    PullRefreshLayout relaxRefreshLayout;
    private RelaxPresenter relaxPresenter;
    private RelaxAdapter adapter;
    private List<RelaxBean> relaxList = new ArrayList<RelaxBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        relaxPresenter = new RelaxPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relax, container, false);
        ButterKnife.inject(this, view);
        rvRelax.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RelaxAdapter(getContext());
        adapter.setData(relaxList);
        rvRelax.setAdapter(adapter);
        relaxPresenter.start();
        setOnListener();
        return view;
    }
    private void setOnListener() {
        relaxRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                relaxPresenter.loadRelaxList(0);
            }
        });
        adapter.setOnItemClickListener(new RelaxAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                click2Detail(relaxList.get(position).getDocid());
            }
        });
    }

    @Override
    public void hideProgress() {
        relaxRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showProgress() {
        relaxRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshArtiList(List<RelaxBean> list) {
        relaxList.clear();
        relaxList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreArtiList() {

    }

    @Override
    public void click2Detail(String docid) {
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("docid",docid);
        getActivity().startActivity(intent);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            if(relaxList==null&&!(relaxList.size() >0)){
                relaxPresenter.loadRelaxList(0);
            }
        }else{
            relaxRefreshLayout.setRefreshing(false);
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
