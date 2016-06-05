package com.alai.news.comment.presenter;

import com.alai.news.beans.CommentBean;
import com.alai.news.beans.NewsBean;
import com.alai.news.beans.UserBean;
import com.alai.news.comment.model.CommentModel;
import com.alai.news.comment.view.CommentActivity;

import java.util.List;

/**
 * Created by chenguochao on 2016/5/9.
 */
public class CommentPresenter implements CommentModel.OnLoadCommentsFinishListener {

    private CommentModel mModel;
    private CommentActivity mView;

    public CommentPresenter(CommentActivity view) {
        mView = view;
        mModel = new CommentModel();
    }
    public void loadComments(NewsBean bean) {
        mView.onShowLoadingProgress();
        mModel.loadComments(bean, this);
    }

    @Override
    public void onLoadSuccess(List<CommentBean> comments) {
        mView.onHideLoadingProgress();
        if (comments == null || comments.size() == 0) {
            mView.onShowNoCommentMsg();
        } else {
            mView.onShowComments(comments);
        }
    }

    @Override
    public void onLoadFailure() {
        mView.onHideLoadingProgress();
        mView.onShowLoadFailMsg();
    }

}
