package com.alai.news.video.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.NewsBean;
import com.alai.news.storage.BitmapCache;
import com.alai.news.utils.AsyncImageLoaderUtil;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/5.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_ITEM = 0;
    private final static int TYPE_FOOTER = 1;

    private List<NewsBean> mNewsBeanList;
    private OnItemClickListener mOnItemClickListener;
    private ImageLoader mImageLoader;

    public VideoAdapter() {
        mImageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
    }

    public void setData(List<NewsBean> data) {
        mNewsBeanList = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.news_normal_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewsBean bean = mNewsBeanList.get(position);
            if (bean == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(bean.getTitle());
            ((ItemViewHolder) holder).mDigest.setText(bean.getDigest());
            AsyncImageLoaderUtil.display(mImageLoader, ((ItemViewHolder) holder).mImage, bean.getImgsrc());
        }
    }

    @Override
    public int getItemCount() {
        if (mNewsBeanList == null || mNewsBeanList.size() == 0) {
            return 1;
        }
        return mNewsBeanList.size() + 1;
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public NewsBean getItem(int position) {
        return mNewsBeanList == null ? null : mNewsBeanList.get(position);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public NetworkImageView mImage;
        public TextView mTitle;
        public TextView mDigest;

        public ItemViewHolder(View view) {
            super(view);
            mImage = (NetworkImageView) view.findViewById(R.id.ivNewsItem);
            mTitle = (TextView) view.findViewById(R.id.tvTitle);
            mDigest = (TextView) view.findViewById(R.id.tvDigest);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
