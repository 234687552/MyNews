package com.example.administrator.mynews.giflist.model;

import android.util.Log;

import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.giflist.GifListGson;
import com.example.administrator.mynews.utils.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GifListListModel implements GifListModelImpl {
    private static final String TAG = "GifListListModel";
    private List<GifListBean>gifList=new ArrayList<GifListBean>();
    private GifListGson gson=new GifListGson();
    private Call<ResponseBody> call;
    @Override
    public void loadGifList(final LoadGifListener listener) {
        call= RetrofitUtil.getInstance().createService(GifService.class).getGifList();
        Log.w(TAG, "loadGifList: "+call.request() );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String jsonString = null;
                try {
                    jsonString = new String(response.body().bytes());
                    jsonString=jsonString.substring(jsonString.indexOf("(")+1,jsonString.length()-1);
                    gifList= gson.getGifList(jsonString);
                    listener.onSucceed(gifList);
                } catch (IOException e) {
                    Log.w(TAG, "fail: "+e.getMessage() );
                    listener.onFail(e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.w(TAG, "onResponse: "+t.getMessage() );
                listener.onFail(t.getMessage());
            }
        });

    }


    //获取service
    private interface GifService {
        @GET(APIUrl.GIFLIST_URL_JSON)
        Call<ResponseBody> getGifList();

    }
}

//    private Document doc;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    doc= Jsoup.connect(APIUrl.GIF_URL).timeout(10000).get();
//                    if (doc==null){
//                        listener.onFail("获取失败");
//                    }
//                    Element content=doc.getElementById("content");
//                    Elements list =content.select("div.i-list").first().getElementById("pic-list").select("li.box");
//
////                    Log.w(TAG, "张数: "+list.get(1).select("span.fr").text());
////                    Log.w(TAG, "imgUrl: "+list.get(1).select("img").attr("src"));
////                    Log.w(TAG, "正文: "+list.get(1).select("em").text());
////                    Log.w(TAG, "目标: " + list.get(1).select("a").attr("href"));
//                    List<GifListBean> gifBeans=new ArrayList<GifListBean>();
//                    for (int i = 1; i < list.size(); i++) {
//                        GifListBean gifListBean =new GifListBean();
//                        gifListBean.setContent(list.get(i).select("em").text());
//                        gifListBean.setCoverUrl(list.get(i).select("img").attr("src"));
//                        Pattern p = Pattern.compile("[^0-9]");//正则表达式
//                        String pocId=p.matcher(list.get(1).select("a").attr("href")).replaceAll("").trim();
//                        gifListBean.setPocid(pocId);
//                        gifListBean.setSum(list.get(i).select("span.fr").text());
//                        gifBeans.add(gifListBean);
//                    }
//                    listener.onSucceed(gifBeans);
//                } catch (IOException e) {
//                    listener.onFail(e.getMessage());
//                }
//            }
//        }).start();