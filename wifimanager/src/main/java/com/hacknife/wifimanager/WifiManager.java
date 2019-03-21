package com.hacknife.wifimanager;

import android.content.Context;
import android.net.wifi.WifiConfiguration;

import java.util.List;

public class WifiManager extends BaseWifiManager {

    private WifiManager(Context context) {
        super(context);
    }

    public static IWifiManager create(Context context) {
        return new WifiManager(context);
    }

    @Override
    public boolean isOpened() {
        return manager.isWifiEnabled();
    }

    @Override
    public void openWifi() {
        if (!manager.isWifiEnabled())
            manager.setWifiEnabled(true);
    }

    @Override
    public void closeWifi() {
        if (manager.isWifiEnabled())
            manager.setWifiEnabled(false);
    }

    @Override
    public void scanWifi() {
        manager.startScan();
    }

    @Override
    public boolean disConnectWifi() {
        return manager.disconnect();
    }

    @Override
    public boolean connectEncryptWifi(IWifi wifi, String password) {
        if (manager.getConnectionInfo() != null && wifi.SSID().equals(manager.getConnectionInfo().getSSID()))
            return true;
        int networkId = WifiHelper.configOrCreateWifi(manager, wifi, password);
        return manager.enableNetwork(networkId, true);
    }

    @Override
    public boolean connectSavedWifi(IWifi wifi) {
        return manager.enableNetwork(wifi.networkId(), true);
    }

    @Override
    public boolean connectOpenWifi(IWifi wifi) {
        return connectEncryptWifi(wifi, null);
    }

    @Override
    public boolean removeWifi(IWifi wifi) {
        return WifiHelper.deleteWifiConfiguration(manager, wifi);
    }

    @Override
    public List<IWifi> getWifi() {
        return null;
    }
}
