package com.example.administrator.mynews.detail.detailModel;

import android.util.Log;

import com.example.administrator.mynews.beans.DetailBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.detail.DetailGson;
import com.example.administrator.mynews.utils.RetrofitUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DetailModel implements DetailModelImpl {
    private static final String TAG = "DetailModel";
    private RetrofitUtil retrofitUtil=RetrofitUtil.getInstance();
    private DetailGson detailGson=new DetailGson();
    @Override
    public void loadDetail(final String docid, final LoadDetailListener listener) {
        Call<ResponseBody> call=retrofitUtil.createService(DetailService.class).getDetail(docid);
        Log.w(TAG, call.request()+"");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    jsonString=jsonString.substring(jsonString.indexOf("(")+1,jsonString.length()-1);
                    DetailBean detailBean=detailGson.getDetail(jsonString,docid);
                    listener.onSucceed(detailBean);
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
    //http://3g.163.com/touch/article/BTQ4VLSK00964LQ9/full.html
    private interface DetailService{
        @GET(APIUrl.DETAIL_URL+"{docid}/full.html")
        Call<ResponseBody> getDetail(@Path("docid") String docid);

    }
}
