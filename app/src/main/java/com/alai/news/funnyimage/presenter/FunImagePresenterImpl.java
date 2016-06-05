package com.alai.news.funnyimage.presenter;

import com.alai.news.beans.FunImageBean;
import com.alai.news.funnyimage.model.FunImageModel;
import com.alai.news.funnyimage.model.FunImageModelImpl;
import com.alai.news.funnyimage.view.FunImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class FunImagePresenterImpl implements FunImagePresenter, FunImageModel.OnLoadImageListFinishListener {
    private final static String TAG = "FunImagePresenterImpl";
    private FunImageView mView;
    private FunImageModel mModel;

    public FunImagePresenterImpl(FunImageView view) {
        mModel = new FunImageModelImpl();
        mView = view;
    }

    @Override
    public void loadImageList() {
        mView.onShowLoadingProgress();
        mModel.loadImageList(this);
    }

    @Override
    public void onSuccess(List<FunImageBean> list) {
        mView.onHideLoadingProgress();
        mView.onShowFunImageListData(list);
    }

    @Override
    public void onFailure() {
        mView.onHideLoadingProgress();
        mView.onShowLoadfailMsg();
    }

    @Override
    public void destroy() {
        mView = null;
    }
}
