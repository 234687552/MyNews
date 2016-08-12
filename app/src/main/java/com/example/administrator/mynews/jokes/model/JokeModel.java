package com.example.administrator.mynews.jokes.model;

import android.util.Log;

import com.example.administrator.mynews.beans.JokeBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.jokes.JokesGson;
import com.example.administrator.mynews.utils.RetrofitUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class JokeModel implements JokeModelImpl {
    private static final String TAG = "JokeModel";
    private static final int PAGESIZE=20;
    private JokesGson jokesGson=new JokesGson();
    private RetrofitUtil retrofit=RetrofitUtil.getInstance();
    private Call<ResponseBody> jokeCall;

    @Override
    public void loadJokeInfoFromService(String type, final LoadJokeInfoListener listener) {
        jokeCall=retrofit.createService(JokeService.class).getJokeInfo(type,PAGESIZE);
        Log.w(TAG, ""+jokeCall.request() );
        jokeCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    List<JokeBean> jokeBeans= jokesGson.getJokes(jsonString);
                    listener.onSucceed(jokeBeans);
                } catch (IOException e) {
                    listener.onFail(e.getMessage());
                    Log.w(TAG, "onFail: "+e.getMessage() );
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFail(t.getMessage());
                Log.w(TAG, "onFail: "+t.getMessage() );
            }
        });
    }

    //笑话获取service
    private interface JokeService {
        @GET(APIUrl.JOKE_URL)
        Call<ResponseBody> getJokeInfo(@Query("channel") String channel,@Query("size") int size);

    }
}
