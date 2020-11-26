package com.example.custombannerapp.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.custombannerapp.R;
import com.example.custombannerapp.widget.adapter.CustomBannerPagerAdapter;

/**
 * Created by XHD on 2020/11/26
 */
public class CustomBanner<T extends CustomBannerPagerAdapter> extends RelativeLayout {
    private Context mContext;
    private ViewPager mViewPager;

    public CustomBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(attrs);//初始化配置
        initView();//初始化控件
    }

    private void initView() {
        mViewPager = new ViewPager(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        mViewPager.setLayoutParams(layoutParams);
        this.addView(mViewPager);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CustomBanner);
        typedArray.recycle();
    }

    public void setCustomBannerPagerAdapter(T adapter) {
        if (adapter.getmDatas().size() == 0)
            return;
        mViewPager.setAdapter(adapter);
        selectPages(0, false);
    }


    private boolean downPage;
    private int threadTime;
    private boolean isExistThread;
    private boolean stopAnimal;

    //开启翻页动画（millisecond不宜过长）  启动动画后一定记得，activity销毁时，停止动画
    public void startTurnPageAnimal(boolean isNextPage, int millisecond) {
        threadTime = millisecond;
        stopAnimal = false;//不停止动画
        downPage = isNextPage;//控制翻页
        if (isExistThread) {//存在线程不在开启新线程
            return;
        }
        isExistThread = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stopAnimal) {
                    try {
                        Thread.sleep(threadTime);
                        if (!stopAnimal) {//防止线程睡眠过程中停止动画，还会翻页
                            //需要在主线程中翻页
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (downPage) {
                                        if (mViewPager.getCurrentItem() == Integer.MAX_VALUE - 1) {//当前页在最后一页，停止翻下页
                                            stopAnimal = true;
                                        } else {//翻下页
                                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                                        }
                                    } else {
                                        if (mViewPager.getCurrentItem() == 0) {//当前页在第一页，停止翻上页
                                            stopAnimal = true;
                                        } else {//翻上页
                                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                                        }
                                    }
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isExistThread = false;//线程结束
            }
        }).start();

    }

    //启动动画后一定记得，activity销毁时，停止动画
    public void stopTurnPageAnimal() {
        stopAnimal = true;//停止动画
    }

    //position 数据下标 对应的页面   0代表viewpager中间页，position不能超过数据size，外部不建议使用选择页会阻塞UI
    private void selectPages(int position, boolean smoothScroll) {
        CustomBannerPagerAdapter adapter = (CustomBannerPagerAdapter) mViewPager.getAdapter();
        if (adapter != null) {
            int part = Integer.MAX_VALUE / adapter.getmDatas().size();//段数
            mViewPager.setCurrentItem(adapter.getmDatas().size() * (part / 2) + position, smoothScroll);
        }
    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }
}
