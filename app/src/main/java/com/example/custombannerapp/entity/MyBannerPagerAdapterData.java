package com.example.custombannerapp.entity;

import com.example.custombannerapp.widget.entity.CustomBannerPagerAdapterData;

import java.util.List;

/**
 * Created by XHD on 2020/11/26
 */
public class MyBannerPagerAdapterData extends CustomBannerPagerAdapterData {
    private String str_title;
    private List<News> newsList;

    public MyBannerPagerAdapterData(String str_title, List<News> newsList) {
        this.str_title = str_title;
        this.newsList = newsList;
    }

    public MyBannerPagerAdapterData(int item_type, String str_title, List<News> newsList) {
        super(item_type);
        this.str_title = str_title;
        this.newsList = newsList;
    }

    public String getStr_title() {
        return str_title;
    }

    public void setStr_title(String str_title) {
        this.str_title = str_title;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
