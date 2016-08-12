package com.example.administrator.mynews.detail;

import android.util.Log;

import com.example.administrator.mynews.beans.DetailBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DetailGson {
    private static final String TAG = "DetailGson";
    private GsonUtil gsonUtil=GsonUtil.getInstance();
    public DetailBean getDetail(String json,String docid){
        Log.w(TAG, "getDetail: "+json );
        DetailBean detailBean=new DetailBean();
        JsonObject jsonObj = gsonUtil.parser.parse(json).getAsJsonObject().get(docid).getAsJsonObject();
        detailBean=gsonUtil.gson.fromJson(jsonObj,DetailBean.class);
        //处理从网站返回的信息.
        List<String> bodyLists=new ArrayList<String >();
        String[] strings=detailBean.getBody().split("-->|<!--");
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length() !=0&&
                    !strings[i].contains("@@")&&
                    !strings[i].contains("splitpage")){
                bodyLists.add(strings[i]);
            }
        }
        detailBean.setBodyLists(bodyLists);
        return detailBean;
    }
}
