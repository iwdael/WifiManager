package com.hacknife.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hacknife.wifimanager.IWifi;

import java.util.ArrayList;
import java.util.List;

public class WifiAdapter extends RecyclerView.Adapter<WifiViewHolder> {
    List<IWifi> wifis;

    public WifiAdapter() {
        wifis = new ArrayList<>();
    }

    @Override
    public WifiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WifiViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wifi, viewGroup));
    }

    @Override
    public void onBindViewHolder(WifiViewHolder holder, int i) {
        holder.bindData(wifis.get(i));
    }

    @Override
    public int getItemCount() {
        return wifis.size();
    }

    public void bindData(List<IWifi> wifis) {
        this.wifis.clear();
        this.wifis.addAll(wifis);
        notifyDataSetChanged();
    }
}
