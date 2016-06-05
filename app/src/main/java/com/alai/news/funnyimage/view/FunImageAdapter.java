package com.alai.news.funnyimage.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alai.news.R;
import com.alai.news.beans.FunImageBean;
import com.alai.news.utils.ToolsUtil;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FunImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<FunImageBean> mData;
    private int mMaxWidth;
    private int mMaxHeight;

    public FunImageAdapter(Context context) {
        this.mContext = context;
        mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
        mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                ToolsUtil.dip2px(mContext, 96);
    }

    public void setData(List<FunImageBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fun_image_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FunImageBean bean = mData.get(position);
        if(bean == null) {
            return;
        }
        ((ItemViewHolder) holder).mTitle.setText(bean.getTitle());
        float scale = (float)bean.getWidth() / (float) mMaxWidth;
        int height = (int)(bean.getHeight() / scale);
        if(height > mMaxHeight) {
            height = mMaxHeight;
        }

        ((ItemViewHolder)holder).mImge.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
        Glide.with(mContext).load(bean.getThumburl()).
                placeholder(R.mipmap.ic_image_loading).
                error(R.mipmap.ic_image_loadfail).crossFade().
                into(((ItemViewHolder) holder).mImge);
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mImge;
        public ItemViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.tvTitle);
            mImge = (ImageView) view.findViewById(R.id.ivImage);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
