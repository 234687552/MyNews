package com.example.administrator.mynews.news.model;

import android.util.Log;

import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.news.NewsGson;
import com.example.administrator.mynews.utils.RetrofitUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class NewsModel implements NewsModelImpl{
    private static final String TAG = "NewsModel";
    private Call<ResponseBody> call;
    private NewsGson newsGson=new NewsGson();
    @Override
    public void loadNewsList(int starIndex, final LoadNewsListListener listener) {
        call= RetrofitUtil.getInstance().createService(NewsService.class).getArtiList(starIndex);
        Log.w(TAG, "loadArtiList: " + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    List<NewsBean> newsBeans=newsGson.getNewsList(jsonString);
                    listener.onSucceed(newsBeans);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG, "onFailure: " );
            }
        });
    }


    //news获取service
    private interface NewsService {
        @GET(APIUrl.HEAD_LINE_URL+"{starindex}-20.html")
        Call<ResponseBody> getArtiList(@Path("starindex") int pagesize);
    }

}
