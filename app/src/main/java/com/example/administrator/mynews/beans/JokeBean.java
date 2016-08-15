package com.example.administrator.mynews.beans;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class JokeBean {
    private String img;
    private String digest;
    private int downTimes;
    private int upTimes;
    private int replyCount;
    private String pixel="";

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public int getUpTimes() {
        return upTimes;
    }

    public void setUpTimes(int upTimes) {
        this.upTimes = upTimes;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getDownTimes() {
        return downTimes;
    }

    public void setDownTimes(int downTimes) {
        this.downTimes = downTimes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }
}



/*
 {
            "img": "http://dmr.nosdn.127.net/0JIf8LsKQWWrHWNG3D9_1A==/6896093022336572013.jpg",
            "digest": "一哥们儿婚前特邋遢，经常不刮胡子。 昨天结婚了，今天来上班时，胡子刮得贼啦干净。 于是打趣：“还是有老婆好吧，都知道打扮了。” 谁知他说：“本不想刮的，可你嫂子说了，不刮扎大腿。” 我：“……”",
            "downTimes": 579,
            "replyCount": 28,
            "upTimes": 2303
            "pixel": "500*462"

            "docid": "BTUDU7VV9001U800",
            "replyid": "BTUDU7VV9001U800",
        },
 */
