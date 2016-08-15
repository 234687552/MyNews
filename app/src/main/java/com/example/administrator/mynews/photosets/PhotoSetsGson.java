package com.example.administrator.mynews.photosets;

import android.util.Log;

import com.example.administrator.mynews.beans.DetailBean;
import com.example.administrator.mynews.beans.GifBean;
import com.example.administrator.mynews.beans.GifListBean;
import com.example.administrator.mynews.beans.PhotoSetsBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class PhotoSetsGson {
    private static final String TAG = "PhotoSetsGson";
    private GsonUtil gsonUtil=GsonUtil.getInstance();
    public PhotoSetsBean getPhotos(String json){
        PhotoSetsBean photoBean;
        JsonObject jsonObj = gsonUtil.parser.parse(json).getAsJsonObject();
        photoBean=gsonUtil.gson.fromJson(jsonObj,PhotoSetsBean.class);
        return photoBean;
    }
    public List<GifBean> getGifs(String json){
        List<GifBean> gifBeans=new ArrayList<GifBean>();
        JsonObject jsonObj = gsonUtil.parser.parse(json).getAsJsonObject();
        JsonArray array=jsonObj.get("picInfo").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            JsonObject object=jsonElement.getAsJsonObject();
            GifBean gifBean=gsonUtil.gson.fromJson(object,GifBean.class);
            gifBeans.add(gifBean);
        }
        return gifBeans;
    }
}
