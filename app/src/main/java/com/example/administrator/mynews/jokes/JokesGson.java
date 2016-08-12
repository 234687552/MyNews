package com.example.administrator.mynews.jokes;

import android.util.Log;

import com.example.administrator.mynews.beans.JokeBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class JokesGson {
    private static final String TAG = "JokesGson";

    public List<JokeBean> getJokes(String json) {
        List<JokeBean> jokeBeans = new ArrayList<JokeBean>();
        JsonObject jsonObj = GsonUtil.getInstance().parser.parse(json).getAsJsonObject();
        JsonArray array = jsonObj.get("段子").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            JsonObject object = jsonElement.getAsJsonObject();
            JokeBean jokeBean = GsonUtil.getInstance().gson.fromJson(object, JokeBean.class);
            jokeBeans.add(jokeBean);
        }
        Collections.sort(jokeBeans, new Comparator<JokeBean>() {
            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(JokeBean lhs, JokeBean rhs) {

                return lhs.getUpTimes()<rhs.getUpTimes()?1:lhs.getUpTimes()==rhs.getUpTimes()?0:-1;
            }
        });
        return jokeBeans;
    }
}


/*
{
    "段子": [
        {
            "boardid": "comment_bbs",
            "clkNum": 0,
            "digest": "明天在家睡觉，不瞎出去溜达了，因为我上夜班上一条下一条，你懂的收藏本页面",
            "docid": "BU0N5JT290015JT3",
            "downTimes": 40,
            "imgType": 0,
            "picCount": 0,
            "program": "HY",
            "prompt": "成功为您推荐20条新内容",
            "recType": 0,
            "replyCount": 0,
            "replyid": "BU0N5JT290015JT3",
            "source": "糗事百科",
            "title": "明天在家睡觉，不瞎出去溜达了，因为我上夜班",
            "upTimes": 7
        },
        {
            "boardid": "comment_bbs",
            "clkNum": 0,
            "digest": "我不去其它楼层，我就在这里等你修好了看你饮用",
            "docid": "BTKVQ8BN9001Q8BO",
            "downTimes": 14,
            "img": "http://dmr.nosdn.127.net/vmlo27Atp-KMEcV2027C_g==/6896093022329132198.jpg",
            "imgType": 0,
            "imgsrc": "http://dmr.nosdn.127.net/vmlo27Atp-KMEcV2027C_g==/6896093022329132198.jpg",
            "picCount": 0,
            "pixel": "452*750",
            "program": "HY",
            "recType": 0,
            "replyCount": 0,
            "replyid": "BTKVQ8BN9001Q8BO",
            "source": "糗事百科",
            "title": "我不去其它楼层，我就在这里等你修好了看你饮用",
            "upTimes": 55
        },
            ]
 }
 */
