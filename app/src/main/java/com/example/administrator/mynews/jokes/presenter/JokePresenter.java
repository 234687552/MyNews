package com.example.administrator.mynews.jokes.presenter;

import com.example.administrator.mynews.beans.JokeBean;
import com.example.administrator.mynews.jokes.model.JokeModel;
import com.example.administrator.mynews.jokes.model.JokeModelImpl;
import com.example.administrator.mynews.jokes.view.JokeFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class JokePresenter  implements  JokePresenterImpl{
    private JokeModel jokeModel;

    public JokePresenter(JokeFragment jokeView) {
        this.jokeView = jokeView;
        jokeModel=new JokeModel();
    }

    private JokeFragment jokeView;

    @Override
    public void loadJokes() {
        jokeModel.loadJokeInfoFromService("T1419316284722", new JokeModelImpl.LoadJokeInfoListener() {
            @Override
            public void onSucceed(List<JokeBean> jokeBeans) {
                jokeView.refreshJokes(jokeBeans);
                jokeView.hideProgress();
            }
            @Override
            public void onFail(String msg) {
                jokeView.hideProgress();
            }
        });
    }

    @Override
    public void start() {
        jokeView.showProgress();
        loadJokes();
    }
}
