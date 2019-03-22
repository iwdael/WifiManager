package com.hacknife.sample.adapter.base.i;

import android.view.View;

public interface OnItemClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemClick(E t);
}
