package com.hacknife.sample.adapter.base.i;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hacknife.briefness.Briefness;
import com.hacknife.briefness.Briefnessor;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public abstract class BaseRecyclerViewHolder<T, B extends Briefnessor> extends RecyclerView.ViewHolder {
    protected B briefnessor;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        briefnessor = (B) Briefness.bind(this, itemView);
    }

    public abstract void bindData(T t);
}
