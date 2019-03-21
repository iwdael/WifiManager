package com.hacknife.sample;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hacknife.refresh.core.RefreshLayout;
import com.hacknife.refresh.core.api.Refresh;
import com.hacknife.refresh.core.listener.OnRefreshListener;
import com.hacknife.wifimanager.IWifi;
import com.hacknife.wifimanager.IWifiManager;
import com.hacknife.wifimanager.OnWifiChangeListener;
import com.hacknife.wifimanager.OnWifiConnectListener;
import com.hacknife.wifimanager.OnWifiStateChangeListener;
import com.hacknife.wifimanager.State;
import com.hacknife.wifimanager.WifiManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWifiChangeListener, OnWifiConnectListener, OnWifiStateChangeListener {
    IWifiManager manager;
    WifiAdapter adapter;
    RecyclerView rc_view;
    RefreshLayout refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = findViewById(R.id.refresh);
        rc_view = findViewById(R.id.rc_view);
        adapter = new WifiAdapter();
        rc_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc_view.setAdapter(adapter);
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull Refresh refreshLayout) {
                manager.scanWifi();
                refresh.finishRefresh(500);
            }
        });
        refresh.setEnableLoadMore(false);
        manager = WifiManager.create(this);
        manager.setOnWifiChangeListener(this);
        manager.setOnWifiConnectListener(this);
        manager.setOnWifiStateChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        manager.destroy();
        super.onDestroy();
    }

    @Override
    public void onWifiChanged(List<IWifi> wifis) {
        adapter.bindData(wifis);
    }

    @Override
    public void onStatusChanged(boolean status) {

    }

    @Override
    public void onStateChanged(State state) {

    }
}
