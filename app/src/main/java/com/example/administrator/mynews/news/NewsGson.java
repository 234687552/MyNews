package com.example.administrator.mynews.news;

import android.util.Log;

import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.beans.NewsBean;
import com.example.administrator.mynews.utils.GsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class NewsGson {
    private static final String TAG = "NewsGson";

    public List<NewsBean> getNewsList(String json) {
        List<NewsBean> newsBeans = new ArrayList<NewsBean>();
        JsonObject jsonObj = GsonUtil.getInstance().parser.parse(json).getAsJsonObject();
        JsonArray array = jsonObj.get("T1348647909107").getAsJsonArray();
        for (JsonElement jsonElement : array) {
            JsonObject object = jsonElement.getAsJsonObject();
            NewsBean newsBean = GsonUtil.getInstance().gson.fromJson(object, NewsBean.class);
            Log.w(TAG, "getNewsList: " + newsBean.getDigest());
           newsBeans.add(newsBean);
        }
        return newsBeans;
    }
}
/*
    {
            "postid": "BU8RNK1C00014PRF",
            "url_3w": "http://news.163.com/16/0812/09/BU8RNK1C00014PRF.html",
            "votecount": 1754,
            "replyCount": 3281,
            "skipID": "S1451880983492",
            "ltitle": "习近平论反腐:全力清除党内\"最大威胁\"",
            "digest": "不忘“初心”，就要时时告诫“我们党作为执政党，面临的最大威胁就是腐败。”七一重要讲话，习近平深刻阐述了“不忘初心、继续前进”的八方面要求，将反腐斗争再次提上新高",
            "skipType": "special",
            "url": "http://3g.163.com/news/16/0812/09/BU8RNK1C00014PRF.html",
            "specialID": "S1451880983492",
            "docid": "BU8RNK1C00014PRF",
            "title": "习近平论反腐:全力清除党内\"最大威胁\"",
            "source": "新华网$",
            "priority": 310,
            "lmodify": "2016-08-12 10:42:52",
            "boardid": "news_guonei8_bbs",
            "subtitle": "",
            "imgsrc": "http://cms-bucket.nosdn.127.net/b8cfec0101324f87a95dc483f6f7ab8720160812102449.png",
            "ptime": "2016-08-12 09:54:57"
        },
 */