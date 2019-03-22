package com.hacknife.sample.adapter.base.i;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public abstract class BaseRecyclerViewAdapter<E, T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<T> {
    protected List<E> data = new ArrayList<>();
    protected OnItemClickListener<E> onItemClickListener;
    protected OnItemClickListener2<E> onItemClickListener2;
    protected OnItemLongClickListener<E> onItemLongClickListener;
    protected OnItemLongClickListener2<E> onItemLongClickListener2;
    protected OnRecyclerListener<E> onRecyclerListener;
    protected int lastItem = -1;
    protected int minCount = 0;
    protected int maxCount = Integer.MAX_VALUE;


    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        if (onRecyclerViewListener == null) return;
        if (onRecyclerViewListener instanceof OnItemClickListener) {
            this.onItemClickListener = (OnItemClickListener<E>) onRecyclerViewListener;
        } else if (onRecyclerViewListener instanceof OnItemClickListener2) {
            this.onItemClickListener2 = (OnItemClickListener2<E>) onRecyclerViewListener;
        } else if (onRecyclerViewListener instanceof OnItemLongClickListener) {
            this.onItemLongClickListener = (OnItemLongClickListener<E>) onRecyclerViewListener;
        } else if (onRecyclerViewListener instanceof OnItemLongClickListener2) {
            this.onItemLongClickListener2 = (OnItemLongClickListener2<E>) onRecyclerViewListener;
        }
        if (this.onRecyclerListener == null)
            this.onRecyclerListener = new OnRecyclerListener<E>() {
                @Override
                public void onClick(E e, int position, View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(e);
                    if (onItemClickListener2 != null) {
                        if (onItemClickListener2.onItemClick(e, lastItem, position, v))
                            lastItem = position;
                    }
                }

                @Override
                public boolean onLongClick(E e, int position, View v) {
                    if (onItemLongClickListener != null)
                        onItemLongClickListener.onItemLongClick(e);
                    if (onItemLongClickListener2 != null) {
                        if (onItemLongClickListener2.onItemLongClick(e, lastItem, position, v)) {
                            lastItem = position;
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public void callback(int position) {
                    if (lastItem == -1)
                        lastItem = position;
                }
            };
    }

    public void insert(List<E> es, int position) {
        if (es != null && es.size() > 0) {
            data.addAll(position, es);
            notifyItemRangeChanged(0, data.size());
        }
    }

    public void insert(List<E> es) {
        if (es != null && es.size() > 0) {
            data.addAll(es);
            notifyItemRangeChanged(data.size() - es.size(), es.size());
        }
    }

    public void insert(E e) {
        if (e != null) {
            data.add(e);
            notifyItemInserted(data.size() - 1);
        }
    }

    public void insert(E e, int position) {
        if (data != null) {
            notifyItemInserted(position);
            notifyItemRangeChanged(position, data.size() - position);
        }
    }

    public List<E> data() {
        return data;
    }

    public void bindData(List<E> data) {
        if (data != null) {
            data.clear();
            data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        lastItem = -1;
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size() < minCount ? minCount : (data.size() > maxCount ? maxCount : data.size());
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.bindData(data.get(position), position, data.size(), onRecyclerListener);
    }


}
