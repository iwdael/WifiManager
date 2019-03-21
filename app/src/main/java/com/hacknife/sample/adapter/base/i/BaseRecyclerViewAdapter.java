package com.hacknife.sample.adapter.base.i;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public abstract class BaseRecyclerViewAdapter<V, T extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<T> {
    protected List<V> mData = new ArrayList<>();

    public List<V> getData() {
        return mData;
    }

    public void bindData(List<V> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.bindData(mData.get(position));
    }
}
