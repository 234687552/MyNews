package com.example.administrator.mynews.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class GifBean {
    private int file_width;
    private int file_height;
    private String add_intro;
    private String url;

    public String getAdd_intro() {
        return add_intro;
    }

    public void setAdd_intro(String add_intro) {
        this.add_intro = add_intro;
    }

    public int getFile_height() {
        return file_height;
    }

    public void setFile_height(int file_height) {
        this.file_height = file_height;
    }

    public int getFile_width() {
        return file_width;
    }

    public void setFile_width(int file_width) {
        this.file_width = file_width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
