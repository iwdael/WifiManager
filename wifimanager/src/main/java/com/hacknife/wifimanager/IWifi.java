package com.hacknife.wifimanager;

public interface IWifi {


    String name();

    @Deprecated
    int networkId();

    @Deprecated
    String SSID();

    @Deprecated
    String capabilities();
}
