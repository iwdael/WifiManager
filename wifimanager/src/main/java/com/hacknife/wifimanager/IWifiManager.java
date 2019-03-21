package com.hacknife.wifimanager;

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
}
