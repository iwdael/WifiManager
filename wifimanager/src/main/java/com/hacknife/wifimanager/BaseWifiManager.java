package com.hacknife.wifimanager;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWifiManager implements IWifiManager {
    WifiManager manager;
    List<IWifi> wifis;

    BaseWifiManager(Context context) {
        manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifis = new ArrayList<>();
    }
}
