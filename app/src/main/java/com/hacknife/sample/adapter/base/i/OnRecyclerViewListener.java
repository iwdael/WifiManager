package com.hacknife.sample.adapter.base.i;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public interface OnRecyclerViewListener<T> {
    void onClickPostion(int postion, T t);

    void onLongClickPostion(int postion, T t);
}
