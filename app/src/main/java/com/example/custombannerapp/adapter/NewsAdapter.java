package com.example.custombannerapp.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.custombannerapp.R;
import com.example.custombannerapp.base.BaseRecycleAdapter;
import com.example.custombannerapp.base.BaseViewHolder;
import com.example.custombannerapp.entity.News;

import java.util.List;

/**
 * Created by XHD on 2020/11/27
 */
public class NewsAdapter extends BaseRecycleAdapter<News> {
    private TextView tvNewsTitle;
    private TextView tvNewsContent;
    private ImageView ivNewsTitle;

    public NewsAdapter(List<News> mDatas) {
        super(mDatas);
    }

    @Override
    protected int setItemViewType(List<News> mDatas, int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_news;
    }

    @Override
    protected void initView(BaseViewHolder holder) {
        tvNewsTitle = (TextView) holder.getView(R.id.tv_newsTitle);
        tvNewsContent = (TextView) holder.getView(R.id.tv_newsContent);
        ivNewsTitle = (ImageView) holder.getView(R.id.iv_newsTitle);
    }

    @Override
    protected void setListener(final BaseViewHolder holder, final List<News> mDatas) {
        tvNewsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + mDatas.get(holder.getLayoutPosition()).getNewsTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, News news) {
        tvNewsTitle.setText(news.getNewsTitle());
        tvNewsContent.setText(news.getNewsContent());
        ivNewsTitle.setImageDrawable(mContext.getDrawable(R.mipmap.ic_launcher));//此处可以glide加载网络图片，本地图片一类的
    }

    //如果recycleView用到复用，也可以做一些回收操作
    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
    }

}
