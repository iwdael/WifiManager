package com.iwdael.wifimanager;

import android.net.MacAddress;
import android.net.wifi.ScanResult;

public interface IWifi {

    String name();

    boolean isEncrypt();

    boolean isSaved();

    boolean isConnected();

    String encryption();

    int level();

    String description();

    String ip();

    String description2();

    void state(String state);

    @Deprecated
    String SSID();

    @Deprecated
    String capabilities();

    @Deprecated
    IWifi merge(IWifi merge);

    String state();

    ScanResult getScanResult();
}
