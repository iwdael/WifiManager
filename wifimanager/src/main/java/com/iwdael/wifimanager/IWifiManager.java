package com.iwdael.wifimanager;

import java.util.List;

public interface IWifiManager {

    boolean isOpened();

    void openWifi();

    void closeWifi();

    void scanWifi();

    boolean disConnectWifi();

    boolean connectEncryptWifi(IWifi wifi, String password);

    boolean connectSavedWifi(IWifi wifi);

    boolean connectOpenWifi(IWifi wifi);

    boolean removeWifi(IWifi wifi);

    List<IWifi> getWifi();

    void setOnWifiConnectListener(OnWifiConnectListener onWifiConnectListener);

    void setOnWifiStateChangeListener(OnWifiStateChangeListener onWifiStateChangeListener);

    void setOnWifiChangeListener(OnWifiChangeListener onWifiChangeListener);


    void destroy();
}
