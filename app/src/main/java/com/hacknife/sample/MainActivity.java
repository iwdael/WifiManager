package com.hacknife.sample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    @Override
    public void onStatusChanged(boolean status) {

    }

    @Override
    public void onStateChanged(State state) {

    }
}
