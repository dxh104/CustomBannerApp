package com.example.custombannerapp.widget.entity;

/**
 * Created by XHD on 2020/11/26
 */
public abstract class CustomBannerPagerAdapterData {

    private int item_type;

    public CustomBannerPagerAdapterData() {
    }


    public CustomBannerPagerAdapterData(int item_type) {
        this.item_type = item_type;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
