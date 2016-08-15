package com.example.administrator.mynews.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GifListBean {
        private String gallery_id;
        private String title;

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getGallery_id() {
            return gallery_id;
        }

        public void setGallery_id(String gallery_id) {
            this.gallery_id = gallery_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        private String updated;
        private String comment_count;
        private String cover_url;

}
