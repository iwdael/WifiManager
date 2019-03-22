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
public abstract class BaseRecyclerViewHolder<E, B extends Briefnessor> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected B briefnessor;
    protected OnRecyclerListener<E> listener;
    protected int position;
    protected E entity;
    protected int size;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        briefnessor = (B) Briefness.bind(this, itemView);
    }

    public void bindData(E e, int position, int size, OnRecyclerListener<E> listener) {
        this.listener = listener;
        this.position = position;
        this.size = size;
        if (e == null) return;
        this.entity = e;
        bindData(e);
        if (listener != null) listener.callback(callback(e));
    }

    protected int callback(E e) {
        return -1;
    }

    public abstract void bindData(E t);

    @Override
    public void onClick(View v) {
        if (listener != null) listener.onClick(entity, position, v);
    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) return listener.onLongClick(entity, position, v);
        return false;
    }
}
