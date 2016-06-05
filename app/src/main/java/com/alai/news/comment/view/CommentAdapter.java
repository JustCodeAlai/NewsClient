package com.alai.news.comment.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alai.news.R;
import com.alai.news.beans.CommentBean;
import com.android.volley.toolbox.NetworkImageView;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by chenguochao on 2016/5/9.
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "CommentAdapter";

    private List<CommentBean> mData;

    public void setData(List<CommentBean> data) {
        mData = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.comment_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            CommentBean bean = mData.get(position);
            Log.e(TAG, "the comment is " + bean.getContent());
            if (bean == null) {
                return;
            }
            ((ItemViewHolder) holder).mTvUserName.setText(bean.getUser().getName());
            ((ItemViewHolder) holder).mTvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bean.getTime()));
            ((ItemViewHolder) holder).mTvContent.setText(bean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            Log.e(TAG, "comment size is 0");
            return 0;
        }
        Log.e(TAG, "comment size is " + mData.size());
        return mData.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView mTvUserName;
        public TextView mTvTime;
        public TextView mTvContent;

        public ItemViewHolder(View view) {
            super(view);
            mTvUserName = (TextView) view.findViewById(R.id.tvUserName);
            mTvTime = (TextView) view.findViewById(R.id.tvTime);
            mTvContent = (TextView) view.findViewById(R.id.tvContent);
        }
    }
}
