package com.hacknife.wifimanager;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import java.util.List;

public class WifiHelper {
    public static final String WEP = "WEP";
    public static final String PSK = "PSK";
    public static final String EAP = "EAP";
    public static final String WPA = "WPA";


    public static int configOrCreateWifi(WifiManager manager, IWifi wifi, String password) {
        List<WifiConfiguration> configurations = manager.getConfiguredNetworks();
        for (WifiConfiguration configuration : configurations) {
            if (configuration.SSID.equals(wifi.SSID()))
                return configuration.networkId;
        }
        WifiConfiguration configuration = createWifiConfiguration(wifi, password);
        return saveWifiConfiguration(manager, configuration);
    }

    public static boolean deleteWifiConfiguration(WifiManager manager, IWifi wifi) {
        List<WifiConfiguration> configurations = manager.getConfiguredNetworks();
        for (WifiConfiguration configuration : configurations) {
            if (configuration.SSID.equals(wifi.SSID())) {
                boolean ret = manager.removeNetwork(configuration.networkId);
                ret = ret & manager.saveConfiguration();
                return ret;
            }
        }
        return false;
    }

    private static WifiConfiguration createWifiConfiguration(IWifi wifi, String password) {
        WifiConfiguration configuration = new WifiConfiguration();
        if (password == null) {
            configuration.hiddenSSID = false;
            configuration.status = WifiConfiguration.Status.ENABLED;
            if (wifi.capabilities().contains(WEP)) {
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                configuration.SSID = wifi.SSID();
                configuration.wepTxKeyIndex = 0;
                configuration.wepKeys[0] = "";
            } else if (wifi.capabilities().contains(PSK)) {
                configuration.SSID = wifi.SSID();
                configuration.preSharedKey = "";
            } else if (wifi.capabilities().contains(EAP)) {
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
                configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                configuration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                configuration.SSID = wifi.SSID();
                configuration.preSharedKey = "";
            } else {
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                configuration.SSID = wifi.SSID();
                configuration.preSharedKey = null;
            }
        } else {
            configuration.allowedAuthAlgorithms.clear();
            configuration.allowedGroupCiphers.clear();
            configuration.allowedKeyManagement.clear();
            configuration.allowedProtocols.clear();
            configuration.allowedPairwiseCiphers.clear();
            configuration.SSID = wifi.SSID();
            if (wifi.capabilities().contains(WEP)) {
                configuration.hiddenSSID = true;
                configuration.preSharedKey = "\"" + password + "\n";
                configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                configuration.wepTxKeyIndex = 0;
            } else if (wifi.capabilities().contains(WPA)) {
                configuration.hiddenSSID = true;
                configuration.preSharedKey = "\"" + password + "\n";
                configuration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                configuration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                configuration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            } else {
                configuration.wepKeys[0] = "";
                configuration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                configuration.wepTxKeyIndex = 0;
            }
        }

        return configuration;
    }


    private static int saveWifiConfiguration(WifiManager manager, WifiConfiguration configuration) {
        int networkId = manager.addNetwork(configuration);
        manager.saveConfiguration();
        return networkId;
    }
}
