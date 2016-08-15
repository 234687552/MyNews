package com.example.administrator.mynews.photosets.model;

import android.util.Log;

import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;
import com.example.administrator.mynews.constants.APIUrl;
import com.example.administrator.mynews.photosets.PhotoSetsGson;
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
 * Created by Administrator on 2016/8/12 0012.
 */

//http://c.3g.163.com/photo/api/jsonp/set/   0031/88861.json?callback=photosetinfo
//http://tu.duowan.com/index.php?r=show/getByGallery/&gid=127350

public class PhotoSetsModel implements PhotoSetsModelImpl {
    private static final String TAG = "PhotoSetsModel";
    private Call<ResponseBody> call;
    private PhotoSetsGson photoGson = new PhotoSetsGson();

    @Override
    public void loadPhotoInfo(String photosetID, final LoadPhotoInfoListener listener) {
        call = RetrofitUtil.getInstance().createService(PhotosetsService.class).getPhotoList(photosetID);
        Log.w(TAG, "loadPhotoInfo: " + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    jsonString = jsonString.substring(jsonString.indexOf("(") + 1, jsonString.length() - 1);
                    PhotoSetsBean photoBean = photoGson.getPhotos(jsonString);
                    listener.onSucceed(photoBean);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadGifs(String gid, final LoadGifInfoListener listener) {
        call = RetrofitUtil.getInstance().createService(GifsService.class).getGifs(gid);
        Log.w(TAG, "loadGifs: "+call.request() );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String jsonString = new String(response.body().bytes());
                    List<GifBean> gifBeans=photoGson.getGifs(jsonString);
                    listener.onSucceed(gifBeans);
                } catch (IOException e) {
                    listener.onFail(e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    //photosets获取service
    private interface PhotosetsService {
        @GET(APIUrl.PHOTOSET_URL + "{photosetID}.json?callback=photosetinfo")
        Call<ResponseBody> getPhotoList(@Path("photosetID") String photosetID);
    }
    //gif获取service
    private interface GifsService{
        @GET(APIUrl.GIF_URL )
        Call<ResponseBody> getGifs(@Query("gid") String gid);
    }
}
