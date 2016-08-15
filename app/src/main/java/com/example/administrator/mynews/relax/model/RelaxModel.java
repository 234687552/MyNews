package com.example.administrator.mynews.relax.model;

import android.util.Log;

import com.example.administrator.mynews.beans.RelaxBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.relax.RelaxGson;
import com.example.administrator.mynews.utils.RetrofitUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class RelaxModel implements RelaxModelImpl{
    private static final String TAG = "RelaxModel";
    private Call<ResponseBody> call;
    private RelaxGson relaxGson=new RelaxGson();

    @Override
    public void loadArtiList(int starIndex, final LoadArtilistListener listener) {
        call=RetrofitUtil.getInstance().createService(RelaxService.class).getArtiList(starIndex);
        Log.w(TAG, "loadArtiList: "+call.request() );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String jsonString = new String(response.body().bytes());
                    jsonString=jsonString.substring(jsonString.indexOf("(")+1,jsonString.length()-1);
                    List<RelaxBean> relaxBeans=relaxGson.getArtiList(jsonString);
                    listener.onSucceed(relaxBeans);
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

    //轻松一刻获取service
    private interface RelaxService {
        @GET(APIUrl.RELAX_URL+"{starindex}-20.html")
        Call<ResponseBody> getArtiList(@Path("starindex") int pagesize);
    }
}
