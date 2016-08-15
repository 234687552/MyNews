package com.example.administrator.mynews.giflist;

import android.util.Log;

import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class GifListGson {
    private static final String TAG = "GifListGson";
    public List<GifListBean> getGifList(String json){
        List<GifListBean> gifListBeans=new ArrayList<GifListBean>();
        Log.w(TAG, "getGifList: "+json );
        JsonObject jsonObj = GsonUtil.getInstance().parser.parse(json).getAsJsonObject();
        JsonArray array = jsonObj.get("gallerys").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            GifListBean gifListBean;
            JsonObject object=jsonElement.getAsJsonObject();
            gifListBean=GsonUtil.getInstance().gson.fromJson(object,GifListBean.class);
            gifListBeans.add(gifListBean);
        }
        return gifListBeans;
    }
}
