package com.alai.news.utils;

import com.alai.news.R;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by Administrator on 2016/2/8 0008.
 */
public class AsyncImageLoaderUtil {
    public static void display(ImageLoader loader, NetworkImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("参数错误");
        }
        imageView.setDefaultImageResId(R.mipmap.ic_image_loading);
        imageView.setErrorImageResId(R.mipmap.ic_image_loadfail);
        imageView.setImageUrl(url, loader);
    }
}
