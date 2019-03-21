package com.hacknife.sample.adapter;

import com.hacknife.sample.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.sample.adapter.base.i.BaseRecyclerViewAdapter;
import com.hacknife.sample.adapter.base.WifiViewHolder;
import com.hacknife.wifimanager.IWifi;

import javax.inject.Inject;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public class WifiAdapter extends BaseRecyclerViewAdapter<IWifi, WifiViewHolder> {
    @Inject
    public WifiAdapter() {
    }

    @Override
    public WifiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WifiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wifi, null));
    }

}
