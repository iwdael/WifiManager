package com.hacknife.sample.adapter.base.i;

import android.view.View;

@Deprecated
public interface OnRecyclerListener<E> {
    void onClick(E e, int position, View v);

    boolean onLongClick(E e, int position, View v);

    void callback(int position);
}
