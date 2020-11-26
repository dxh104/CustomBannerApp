package com.example.custombannerapp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custombannerapp.R;
import com.example.custombannerapp.entity.MyBannerPagerAdapterData;
import com.example.custombannerapp.widget.adapter.CustomBannerPagerAdapter;

import java.util.List;

/**
 * Created by XHD on 2020/11/27
 */
public class MyBannerPagerAdapter extends CustomBannerPagerAdapter<MyBannerPagerAdapterData> {
    /**
     * @param context 上下文
     * @param datas
     */
    public MyBannerPagerAdapter(Context context, List<MyBannerPagerAdapterData> datas) {
        super(context, datas);
    }

    private void initView(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
    }

    private void initView1(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        rvNews = (RecyclerView) view.findViewById(R.id.rv_news);
    }

    private RecyclerView rvNews;
    private TextView tvTitle;
    int layout;

    @Override
    public int getCreateViewLayout(List<MyBannerPagerAdapterData> datas, int position) {
        if (datas.get(position).getItem_type() == R.layout.item_banner_layout1)
            layout = R.layout.item_banner_layout1;
        else
            layout = R.layout.item_banner_layout2;
        return layout;
    }

    @Override
    public void onBindView(View view, MyBannerPagerAdapterData data) {
        if (layout == R.layout.item_banner_layout1) {
            initView1(view);
        } else {
            initView(view);
        }
    }

    @Override
    public void onBindData(View view, MyBannerPagerAdapterData data, int position) {
        if (layout == R.layout.item_banner_layout1) {
            rvNews.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rvNews.setLayoutManager(linearLayoutManager);
            rvNews.setAdapter(new NewsAdapter(data.getNewsList()));
            tvTitle.setText(data.getStr_title());
        } else {
            tvTitle.setText(data.getStr_title());
        }
    }

    @Override
    public void setListener(final View view, final List<MyBannerPagerAdapterData> datas) {
        //view.getTag()会自动更新 值为当前view对应适配器数据下标

        if (layout == R.layout.item_banner_layout1) {
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positon = (Integer) view.getTag();
                    Toast.makeText(mContext, datas.get(positon).getStr_title(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer positon = (Integer) view.getTag();
                    Toast.makeText(mContext, datas.get((Integer) view.getTag()).getStr_title(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
