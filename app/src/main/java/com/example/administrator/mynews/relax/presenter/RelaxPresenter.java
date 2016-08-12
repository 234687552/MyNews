package com.example.administrator.mynews.relax.presenter;

import android.util.Log;

import com.example.administrator.mynews.beans.RelaxBean;
import com.example.administrator.mynews.relax.model.RelaxModel;
import com.example.administrator.mynews.relax.model.RelaxModelImpl;
import com.example.administrator.mynews.relax.view.RelaxFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class RelaxPresenter implements RelaxPresenterImpl{
    private static final String TAG = "RelaxPresenter";
    private RelaxFragment relaxView;
    private RelaxModel relaxModel;
    public RelaxPresenter(RelaxFragment relaxView) {
        this.relaxView = relaxView;
        relaxModel=new RelaxModel();
    }


    @Override
    public void loadRelaxList(int startIndex) {
        relaxModel.loadArtiList(startIndex, new RelaxModelImpl.LoadArtilistListener() {
            @Override
            public void onSucceed(List<RelaxBean> list) {
                relaxView.refreshArtiList(list);
                relaxView.hideProgress();
            }

            @Override
            public void onFail(String msg) {
                relaxView.hideProgress();
                Log.w(TAG, "onFail: "+msg );
            }
        });
    }

    @Override
    public void start() {
        loadRelaxList(0);
        relaxView.showProgress();
    }
}
