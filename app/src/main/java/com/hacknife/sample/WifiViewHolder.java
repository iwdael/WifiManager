package com.hacknife.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hacknife.wifimanager.IWifi;

public class WifiViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;

    public WifiViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        desc = itemView.findViewById(R.id.tv_desc);
    }

    public void bindData(IWifi wifi) {
        name.setText(wifi.name());
        desc.setText(wifi.description());
    }
}
