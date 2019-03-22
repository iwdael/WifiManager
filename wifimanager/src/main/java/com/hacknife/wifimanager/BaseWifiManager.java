package com.hacknife.wifimanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseWifiManager implements IWifiManager {

    static final int WIFI_STATE_DISABLED = 1;
    static final int WIFI_STATE_DISABLING = 2;
    static final int WIFI_STATE_ENABLING = 3;
    static final int WIFI_STATE_ENABLED = 4;
    static final int WIFI_STATE_UNKNOWN = 5;
    static final int WIFI_STATE_MODIFY = 6;
    static final int WIFI_STATE_CONNECTED = 7;
    static final int WIFI_STATE_UNCONNECTED = 8;


    WifiManager manager;
    List<IWifi> wifis;
    OnWifiChangeListener onWifiChangeListener;
    OnWifiConnectListener onWifiConnectListener;
    OnWifiStateChangeListener onWifiStateChangeListener;
    WifiReceiver wifiReceiver;
    Context context;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WIFI_STATE_DISABLED:
                    if (onWifiStateChangeListener != null)
                        onWifiStateChangeListener.onStateChanged(State.DISABLED);
                    break;
                case WIFI_STATE_DISABLING:
                    if (onWifiStateChangeListener != null)
                        onWifiStateChangeListener.onStateChanged(State.DISABLING);
                    break;
                case WIFI_STATE_ENABLING:
                    if (onWifiStateChangeListener != null)
                        onWifiStateChangeListener.onStateChanged(State.ENABLING);
                    break;
                case WIFI_STATE_ENABLED:
                    if (onWifiStateChangeListener != null)
                        onWifiStateChangeListener.onStateChanged(State.ENABLED);
                    break;
                case WIFI_STATE_UNKNOWN:
                    if (onWifiStateChangeListener != null)
                        onWifiStateChangeListener.onStateChanged(State.UNKNOWN);
                    break;
                case WIFI_STATE_MODIFY:
                    if (onWifiChangeListener != null)
                        onWifiChangeListener.onWifiChanged(wifis);
                    break;
                case WIFI_STATE_CONNECTED:
                    if (onWifiConnectListener != null)
                        onWifiConnectListener.onConnectChanged(true);
                    break;
                case WIFI_STATE_UNCONNECTED:
                    if (onWifiConnectListener != null)
                        onWifiConnectListener.onConnectChanged(false);
                    break;
            }
        }
    };

    BaseWifiManager(Context context) {
        this.context = context;
        manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifis = new ArrayList<>();
        wifiReceiver = new WifiReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        context.registerReceiver(wifiReceiver, filter);
    }

    @Override
    public void destroy() {
        context.unregisterReceiver(wifiReceiver);
        handler.removeCallbacksAndMessages(null);
        manager = null;
        wifis = null;
        context = null;
    }

    @Override
    public void setOnWifiChangeListener(OnWifiChangeListener onWifiChangeListener) {
        this.onWifiChangeListener = onWifiChangeListener;
    }

    @Override
    public void setOnWifiConnectListener(OnWifiConnectListener onWifiConnectListener) {
        this.onWifiConnectListener = onWifiConnectListener;
    }

    @Override
    public void setOnWifiStateChangeListener(OnWifiStateChangeListener onWifiStateChangeListener) {
        this.onWifiStateChangeListener = onWifiStateChangeListener;
    }

    public class WifiReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) return;
            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                int state = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                int what = 0;
                switch (state) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        what = WIFI_STATE_DISABLED;
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        what = WIFI_STATE_DISABLING;
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        what = WIFI_STATE_ENABLING;
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        scanWifi();
                        what = WIFI_STATE_ENABLED;
                        break;
                    case WifiManager.WIFI_STATE_UNKNOWN:
                        what = WIFI_STATE_UNKNOWN;
                        break;
                }
                handler.sendEmptyMessage(what);
            } else if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    boolean isUpdated = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
                    if (isUpdated)
                        modifyWifi();
                } else {
                    modifyWifi();
                }
            } else if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info == null) return;
                NetworkInfo.DetailedState state = info.getDetailedState();
                if (state == null) return;
                String SSID = info.getExtraInfo();
                if (TextUtils.isEmpty(SSID)) return;
                if (state == NetworkInfo.DetailedState.IDLE) {
                } else if (state == NetworkInfo.DetailedState.SCANNING) {
                } else if (state == NetworkInfo.DetailedState.AUTHENTICATING) {
                    modifyWifi(SSID, "身份验证中...");
                } else if (state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    modifyWifi(SSID, "获取地址信息...");
                } else if (state == NetworkInfo.DetailedState.CONNECTED) {
                    modifyWifi(SSID, "已连接");
                    handler.sendEmptyMessage(WIFI_STATE_CONNECTED);
                } else if (state == NetworkInfo.DetailedState.SUSPENDED) {
                    modifyWifi(SSID, "连接中断");
                } else if (state == NetworkInfo.DetailedState.DISCONNECTING) {
                    modifyWifi(SSID, "断开中...");
                } else if (state == NetworkInfo.DetailedState.DISCONNECTED) {
//                    modifyWifi(SSID, "已断开");
                    modifyWifi();
                    handler.sendEmptyMessage(WIFI_STATE_UNCONNECTED);
                } else if (state == NetworkInfo.DetailedState.FAILED) {
                    modifyWifi(SSID, "连接失败");
                } else if (state == NetworkInfo.DetailedState.BLOCKED) {
                    modifyWifi(SSID, "wifi无效");
                } else if (state == NetworkInfo.DetailedState.VERIFYING_POOR_LINK) {
                    modifyWifi(SSID, "信号差");
                } else if (state == NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK) {
                    modifyWifi(SSID, "强制登陆门户");
                }
            } else if (action.equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info == null) return;
                NetworkInfo.DetailedState state = info.getDetailedState();
                if (state == null) return;
                String SSID = info.getExtraInfo();
                if (TextUtils.isEmpty(SSID)) return;
                int code = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1);
                if (code == WifiManager.ERROR_AUTHENTICATING) {
                    modifyWifi(SSID, "密码错误");
                } else {
                    modifyWifi(SSID, "身份验证出现问题");
                }
            }
        }
    }

    protected void modifyWifi() {
        synchronized (wifis) {
            List<ScanResult> results = manager.getScanResults();
            List<IWifi> wifiList = new LinkedList<>();
            List<IWifi> mergeList = new ArrayList<>();
            List<WifiConfiguration> configurations = manager.getConfiguredNetworks();
            String connectedSSID = manager.getConnectionInfo().getSSID();
            int ipAddress = manager.getConnectionInfo().getIpAddress();
            for (ScanResult result : results) {
                IWifi mergeObj = Wifi.create(result, configurations, connectedSSID, ipAddress);
                if (mergeObj == null) continue;
                mergeList.add(mergeObj);
            }
            mergeList = WifiHelper.removeDuplicate(mergeList);
            for (IWifi merge : mergeList) {
                boolean isMerge = false;
                for (IWifi wifi : wifis) {
                    if (wifi.equals(merge)) {
                        wifiList.add(wifi.merge(merge));
                        isMerge = true;
                    }
                }
                if (!isMerge)
                    wifiList.add(merge);
            }
            wifis.clear();
            wifis.addAll(wifiList);
            handler.sendEmptyMessage(WIFI_STATE_MODIFY);
        }
    }

    protected void modifyWifi(String SSID, String state) {
        synchronized (wifis) {
            List<IWifi> wifiList = new ArrayList<>();
            for (IWifi wifi : wifis) {
                if (SSID.equals(wifi.SSID())) {
                    wifi.state(state);
                    wifiList.add(0, wifi);
                } else {
                    wifi.state(null);
                    wifiList.add(wifi);
                }
            }
            handler.sendEmptyMessage(WIFI_STATE_MODIFY);
        }
    }
}
