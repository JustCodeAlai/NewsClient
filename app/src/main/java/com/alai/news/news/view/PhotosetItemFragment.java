package com.alai.news.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alai.news.R;
import com.alai.news.beans.PhotoBean;
import com.bumptech.glide.Glide;

/**
 * Created by chenguochao on 2016/5/8.
 */
public class PhotosetItemFragment extends Fragment {
    private static final String TAG = "PhotosetItemFragment";
    private PhotoBean mPhotoInfo;
    private ImageView mImage;
    private TextView mTitle;

    public static PhotosetItemFragment newInstance(PhotoBean photoInfo) {
        PhotosetItemFragment fragment = new PhotosetItemFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("photoInfo", photoInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoInfo = (PhotoBean) getArguments().getSerializable("photoInfo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_photoset_item, null);
        mImage = (ImageView) view.findViewById(R.id.ivImage);
        mTitle = (TextView) view.findViewById(R.id.tvTitle);
        Glide.with(this).load(mPhotoInfo.getImgsrc()).
                placeholder(R.mipmap.ic_image_loading).
                error(R.mipmap.ic_image_loadfail).
                crossFade().into(mImage);
        mTitle.setText(mPhotoInfo.getTitle());
        return view;
    }
}
