package com.hacknife.sample.adapter.base.i;


public interface OnItemLongClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemLongClick(E t);
}
