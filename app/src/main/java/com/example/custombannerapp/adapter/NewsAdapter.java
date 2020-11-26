package com.example.custombannerapp.adapter;

import android.view.View;
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
    }
}
