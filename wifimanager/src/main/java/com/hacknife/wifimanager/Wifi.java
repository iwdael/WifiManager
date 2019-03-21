package com.hacknife.wifimanager;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;

import java.util.List;

public class Wifi implements IWifi {
    protected String name;
    protected String SSID;
    protected boolean isEncrypt;
    protected boolean isSaved;
    protected boolean isConnected;
    protected String encryption;
    protected String description;
    protected String capabilities;
    protected String ip;
    protected String state;
    protected int level;


    public static IWifi create(ScanResult result, List<WifiConfiguration> configurations, String connectedSSID, int ipAddress) {
        if (TextUtils.isEmpty(result.SSID))
            return null;
        Wifi wifi = new Wifi();
        wifi.name = result.SSID;
        wifi.SSID = "\"" + result.SSID + "\"";
        wifi.isConnected = wifi.SSID.equals(connectedSSID);
        wifi.capabilities = result.capabilities;
        wifi.isEncrypt = true;
        wifi.encryption = "";
        wifi.level = result.level;
        wifi.ip = wifi.isConnected ? String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff)) : "";
        if (wifi.capabilities.toUpperCase().contains("WPA2-PSK") && wifi.capabilities.toUpperCase().contains("WPA-PSK")) {
            wifi.encryption = "WPA/WPA2";
        } else if (wifi.capabilities.toUpperCase().contains("WPA-PSK")) {
            wifi.encryption = "WPA";
        } else if (wifi.capabilities.toUpperCase().contains("WPA2-PSK")) {
            wifi.encryption = "WPA2";
        } else {
            wifi.isEncrypt = false;
        }
        wifi.description = wifi.encryption;
        for (WifiConfiguration configuration : configurations) {
            if (configuration.SSID.equals(wifi.SSID)) {
                wifi.isSaved = true;
                break;
            }
        }
        if (wifi.isSaved) {
            wifi.description = "已保存";
        }
        if (wifi.isConnected) {
            wifi.description = "已连接";
        }
        return wifi;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean isEncrypt() {
        return isEncrypt;
    }

    @Override
    public boolean isSaved() {
        return isSaved;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String encryption() {
        return encryption;
    }

    @Override
    public int level() {
        return level;
    }

    @Override
    public String description() {
        return state == null ? description : state;
    }

    @Override
    public String ip() {
        return ip;
    }

    @Override
    public String description2() {
        return isConnected ? String.format("%s(%s)", description(), ip) : description();
    }

    @Override
    public void state(String state) {
        this.state = state;
    }

    @Override
    public String SSID() {
        return SSID;
    }

    @Override
    public String capabilities() {
        return capabilities;
    }

    @Override
    public IWifi merge(IWifi merge) {
        isSaved = merge.isSaved();
        isConnected = merge.isConnected();
        ip = merge.ip();
        state = merge.state();
        level = merge.level();
        return this;
    }

    @Override
    public String state() {
        return state;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null || (!(obj instanceof Wifi))) return false;
        return ((Wifi) obj).SSID.equals(this.SSID);
    }


}
