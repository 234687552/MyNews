package com.example.administrator.mynews.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class PhotoSetsBean {
    private String imgsum;
    private String setname;

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    private List<photo> photos;

    public List<photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<photo> photos) {
        this.photos = photos;
    }

    public String getImgsum() {
        return imgsum;
    }

    public void setImgsum(String imgsum) {
        this.imgsum = imgsum;
    }


    public class photo{
        private String imgurl;
        private String note;

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }
}
