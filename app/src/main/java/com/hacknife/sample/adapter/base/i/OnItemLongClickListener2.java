package com.hacknife.sample.adapter.base.i;

import android.view.View;

public interface OnItemLongClickListener2<E> extends OnRecyclerViewListener<E> {
    boolean onItemLongClick(E t, int last, int current, View view);
}
