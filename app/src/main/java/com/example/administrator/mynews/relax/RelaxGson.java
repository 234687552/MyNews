package com.example.administrator.mynews.relax;

import android.util.Log;

import com.example.administrator.mynews.beans.RelaxBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class RelaxGson {
    private static final String TAG = "RelaxGson";

    public List<RelaxBean> getArtiList(String json) {
        List<RelaxBean> relaxBeans = new ArrayList<RelaxBean>();
        JsonObject jsonObj = GsonUtil.getInstance().parser.parse(json).getAsJsonObject();
        JsonArray array = jsonObj.get("BD21K0DLwangning").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            JsonObject object = jsonElement.getAsJsonObject();
            RelaxBean relaxBean = GsonUtil.getInstance().gson.fromJson(object, RelaxBean.class);
            if (relaxBean.getTitle().indexOf("语音") == -1)
                relaxBeans.add(relaxBean);
        }
        return relaxBeans;
    }
}
/*
{
    "BD21K0DLwangning": [
        {
            "docid": "BU4M5R7700964LQ9",
            "title": "每日轻松一刻：这届运动员不按套路出牌",
            "priority": 60,
            "olympicType": 0,
            "stitle": "",
            "imgsrc": "http://cms-bucket.nosdn.127.net/5e5aba2eb4854129b4a7238180024cf520160810185900.jpeg",
            "hasImg": 1,
            "digest": "你们老公宁泽涛真的是一个不按套路出牌的耿直宝宝！昨晚宁泽涛游",
            "commentCount": 4261,
            "ptime": "2016-08-10 19:00:54",
            "url": "http://3g.163.com/ntes/16/0810/19/BU4M5R7700964LQ9.html",
            "imgsrc3gtype": "1"
        },
        {
        }
                        ]
 }
 */