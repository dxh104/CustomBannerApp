package com.example.custombannerapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.custombannerapp.adapter.MyBannerPagerAdapter;
import com.example.custombannerapp.entity.MyBannerPagerAdapterData;
import com.example.custombannerapp.entity.News;
import com.example.custombannerapp.widget.CustomBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomBanner customBanner;
    private Button btnStartNextAnimal;
    private Button btnStartLastAnimal;
    private Button btnStopAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        List<MyBannerPagerAdapterData> myBannerPagerAdapterData = getMyBannerPagerAdapterData();
        customBanner.setCustomBannerPagerAdapter(new MyBannerPagerAdapter(this, myBannerPagerAdapterData));
    }

    @NonNull
    private List<MyBannerPagerAdapterData> getMyBannerPagerAdapterData() {
        List<MyBannerPagerAdapterData> myBannerPagerAdapterData = new ArrayList<>();
        List<News> news = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            news.add(new News("新闻标题" + i, "内容" + i));
        }
        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout1, "页面1", news));

        news = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            news.add(new News("新闻标题" + i, "内容" + i));
        }
        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout1, "页面2", news));

        news = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            news.add(new News("新闻标题" + i, "内容" + i));
        }
        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout1, "页面3", news));

        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout2, "页面4", null));
        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout2, "页面5", null));
        myBannerPagerAdapterData.add(new MyBannerPagerAdapterData(R.layout.item_banner_layout2, "页面6", null));
        return myBannerPagerAdapterData;
    }

    private void initView() {
        customBanner = (CustomBanner) findViewById(R.id.customBanner);
        btnStartNextAnimal = (Button) findViewById(R.id.btn_startNextAnimal);
        btnStartLastAnimal = (Button) findViewById(R.id.btn_startLastAnimal);
        btnStopAnimal = (Button) findViewById(R.id.btn_stopAnimal);
        btnStartNextAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBanner.startTurnPageAnimal(true, 1000);
            }
        });
        btnStartLastAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBanner.startTurnPageAnimal(false, 1000);

            }
        });
        btnStopAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBanner.stopTurnPageAnimal();
            }
        });
    }

    @Override
    protected void onDestroy() {
        customBanner.stopTurnPageAnimal();
        super.onDestroy();

    }
}
