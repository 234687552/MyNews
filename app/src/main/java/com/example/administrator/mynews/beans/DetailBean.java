package com.example.administrator.mynews.beans;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class DetailBean {
    private String body;
    private List<img > img;
    private List<video > video;
    private String  title;

    private List<String > bodyLists;

    public List<String> getBodyLists() {
        return bodyLists;
    }

    public void setBodyLists(List<String> bodyLists) {
        this.bodyLists = bodyLists;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DetailBean.img> getImg() {
        return img;
    }

    public void setImg(List<DetailBean.img> img) {
        this.img = img;
    }

    public List<DetailBean.video> getVideo() {
        return video;
    }

    public void setVideo(List<DetailBean.video> video) {
        this.video = video;
    }

    public class img{
        private String ref;
        private String src;
        private String pixel;

        public String getPixel() {
            return pixel;
        }

        public void setPixel(String pixel) {
            this.pixel = pixel;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
    public class video{
        private String ref;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getUrl_mp4() {
            return url_mp4;
        }

        public void setUrl_mp4(String url_mp4) {
            this.url_mp4 = url_mp4;
        }

        private String url_mp4;
        private String cover;
    }


}
