package com.alai.news.news.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alai.news.MyApplication;
import com.alai.news.R;
import com.alai.news.beans.NewsBean;
import com.alai.news.storage.BitmapCache;
import com.alai.news.utils.AsyncImageLoaderUtil;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1 0001.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static String TAG = "NewsAdapter";

    private final static int TYPE_NORMAL = 0;
    private final static int TYPE_PHOTOSET = 1;
    private final static int TYPE_FOOTER = 2;

    private List<NewsBean> mNewsBeanList;
    private OnItemClickListener mOnItemClickListener;
    private ImageLoader mImageLoader;

    public NewsAdapter() {
        mImageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitmapCache());
        mNewsBeanList = new ArrayList<>();
    }

    public void setData(List<NewsBean> data) {
        mNewsBeanList = data;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "the size is " + mNewsBeanList.size());
        Log.e(TAG, "the getItemCount() is " + getItemCount());
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        if (mNewsBeanList.get(position).getSkipType() != null && mNewsBeanList.get(position).getSkipType().equals("photoset")) {
            return TYPE_PHOTOSET;
        }
        return TYPE_NORMAL;
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.news_normal_item, parent, false);
            return new NormalItemViewHolder(view);
        } else if (viewType == TYPE_PHOTOSET) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.new_photoset_item, parent, false);
            return new PhotosetItemViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalItemViewHolder) {
            NewsBean bean = mNewsBeanList.get(position);
            if (bean == null) {
                return;
            }
            ((NormalItemViewHolder) holder).mTitle.setText(bean.getTitle());
            ((NormalItemViewHolder) holder).mDigest.setText(bean.getDigest());
            AsyncImageLoaderUtil.display(mImageLoader, ((NormalItemViewHolder) holder).mImage, bean.getImgsrc());
        } else if (holder instanceof PhotosetItemViewHolder) {
            NewsBean bean = mNewsBeanList.get(position);
            if (bean == null) {
                return;
            }
            ((PhotosetItemViewHolder) holder).mTitle.setText(bean.getTitle());
            Glide.with(MyApplication.getContext()).load(bean.getImgsrc()).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(((PhotosetItemViewHolder) holder).mImage1);
            Glide.with(MyApplication.getContext()).load(bean.getImgsrc2()).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(((PhotosetItemViewHolder) holder).mImage2);
            Glide.with(MyApplication.getContext()).load(bean.getImgsrc3()).
                    placeholder(R.mipmap.ic_image_loading).
                    error(R.mipmap.ic_image_loadfail).
                    crossFade().into(((PhotosetItemViewHolder) holder).mImage3);
            /*AsyncImageLoaderUtil.display(mImageLoader, ((PhotosetItemViewHolder) holder).mImage1, bean.getImgsrc());
            AsyncImageLoaderUtil.display(mImageLoader, ((PhotosetItemViewHolder) holder).mImage2, bean.getImgsrc2());
            AsyncImageLoaderUtil.display(mImageLoader, ((PhotosetItemViewHolder) holder).mImage3, bean.getImgsrc3());*/
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

    private class NormalItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public NetworkImageView mImage;
        public TextView mTitle;
        public TextView mDigest;

        public NormalItemViewHolder(View view) {
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

    private class PhotosetItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mImage1;
        public ImageView mImage2;
        public ImageView mImage3;
        public TextView mTitle;

        public PhotosetItemViewHolder(View view) {
            super(view);
            mImage1 = (ImageView) view.findViewById(R.id.ivNewsItem1);
            mImage2 = (ImageView) view.findViewById(R.id.ivNewsItem2);
            mImage3 = (ImageView) view.findViewById(R.id.ivNewsItem3);
            mTitle = (TextView) view.findViewById(R.id.tvTitle);
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
