package com.hacknife.sample.adapter.base.i;

import android.view.View;

public interface OnItemClickListener2<E> extends OnRecyclerViewListener<E> {
    boolean onItemClick(E t, int last, int current, View view);
}
