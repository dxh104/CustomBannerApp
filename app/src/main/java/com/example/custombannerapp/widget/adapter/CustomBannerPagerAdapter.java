package com.example.custombannerapp.widget.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.custombannerapp.widget.entity.CustomBannerPagerAdapterData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by XHD on 2020/11/15 该适配器每多一种布局类型会多生成最多4个控件
 */
public abstract class CustomBannerPagerAdapter<T extends CustomBannerPagerAdapterData> extends PagerAdapter {
    private HashMap<String, View> mViews = new HashMap<>();
    private List<T> mDatas = new ArrayList<>();
    public Context mContext;
    private int viewPagerItemCount = Integer.MAX_VALUE / 50;//20亿/50=4000w条目左右，不能设置太大，不然viewpager内部设置页面scrollTo会阻塞


    /**
     * @param context 上下文
     * @param mDatas  数据
     */
    public CustomBannerPagerAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas.addAll(datas);
    }


    /**
     * 为给定位置创建页面
     *
     * @param 容器显示页面的包含视图。
     * @param 定位要实例化的页面位置。
     * @return 返回一个表示新页面的对象。
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mDatas.size();
        if (position < 0) {
            position = mDatas.size() + position;
        }
        int createViewLayout = getCreateViewLayout(mDatas, position);
        HashMap<String, View> viewHashMap = generateView(createViewLayout);//添加一次view先生成4个同类型view
        View view = null;
        //检查view是否已被添加
        for (int i = 0; i < 4; i++) {
            view = viewHashMap.get(createViewLayout + "-" + i);
            ViewParent vp = view.getParent();
            if (vp == null) {//未被添加
                onBindView(view, mDatas.get(position));
                onBindData(view, mDatas.get(position), position);//绑定数据
                if (view.getTag() == null) {//view没设置过tag，说明还没设置过监听事件
                    setListener(view, mDatas);
                }
                view.setTag(position);//更新view对应下标
                container.addView(view);
                break;
            }
        }

        return view;
    }

    /**
     * 删除给定位置的页面。
     *
     * @param container将从中删除页面的包含视图。
     * @param position要删除的页面位置。
     * @param object返回的对象
     */

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mDatas.size();
        if (position < 0) {
            position = mDatas.size() + position;
        }
        onViewRecycle((View) object, mDatas.get(position), position);
        container.removeView((View) object);//此处如果移除view，那么container最多只会缓存3个view
    }

    private HashMap<String, View> generateView(@LayoutRes int id) {
        for (int i = 0; i < 4; i++) {
            if (mViews.get(id + "-" + i) == null) {//同一种布局准备4个view准备复用
                mViews.put(id + "-" + i, LayoutInflater.from(mContext).inflate(id, null));
            }
        }
        return mViews;
    }

    /**
     * 必须实现，其判断View是否是强转而来，可以直接对其进行比较
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * 告诉viewpager一共有多少个Item
     */
    @Override
    public int getCount() {
        return viewPagerItemCount;
    }

    /**
     * @param datas    数据
     * @param position 当前创建的页面,对应数据下标
     * @return
     */
    public abstract @LayoutRes
    int getCreateViewLayout(List<T> datas, int position);

    public abstract void onBindView(View view, T data);//绑定view

    /**
     * @param view     当前创建的页面
     * @param data     对应的数据
     * @param position data数据下标
     */
    public abstract void onBindData(View view, T data, int position);//绑定数据

    public abstract void setListener(View view, List<T> datas);//设置监听事件

    public abstract void onViewRecycle(View view, T data, int position);//可以在回收view时做一些置空操作，回收一些图片之类的操作

    public List<T> getmDatas() {
        return mDatas;
    }
}
