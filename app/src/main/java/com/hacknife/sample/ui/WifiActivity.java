package com.hacknife.sample.ui;

import com.hacknife.refresh.core.api.Refresh;
import com.hacknife.refresh.core.listener.OnRefreshListener;
import com.hacknife.sample.adapter.WifiAdapter;
import com.hacknife.sample.ui.base.BaseActivity;
import com.hacknife.sample.ui.injector.modules.WifiModule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.hacknife.sample.R;
import com.hacknife.sample.ui.view.IWifiView;
import com.hacknife.sample.ui.viewmodel.WifiViewModel;
import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.ui.injector.components.DaggerWifiActivityComponent;
import com.hacknife.sample.ui.viewmodel.i.IWifiViewModel;
import com.hacknife.wifimanager.IWifi;
import com.hacknife.wifimanager.IWifiManager;
import com.hacknife.wifimanager.OnWifiChangeListener;
import com.hacknife.wifimanager.OnWifiConnectListener;
import com.hacknife.wifimanager.OnWifiStateChangeListener;
import com.hacknife.wifimanager.State;
import com.hacknife.wifimanager.WifiManager;

import java.util.List;

import javax.inject.Inject;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
@BindLayout(R.layout.activity_wifi)
public class WifiActivity extends BaseActivity<IWifiViewModel, WifiActivityBriefnessor> implements IWifiView, OnWifiChangeListener, OnWifiConnectListener, OnWifiStateChangeListener {
    IWifiManager manager;
    @Inject
    WifiAdapter adapter;

    @Override
    protected void injector() {
        DaggerWifiActivityComponent.builder()
                .wifiModule(new WifiModule(this, briefnessor))
                .build().inject(this);
    }

    @Override
    protected void initView() {
        manager = WifiManager.create(context());
        briefnessor.rc_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        briefnessor.rc_view.setAdapter(adapter);
        briefnessor.refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull Refresh refreshLayout) {
                manager.scanWifi();
                briefnessor.refresh.finishRefresh(500);
            }
        });
        briefnessor.refresh.setEnableLoadMore(false);
        manager = WifiManager.create(this);
        manager.setOnWifiChangeListener(this);
        manager.setOnWifiConnectListener(this);
        manager.setOnWifiStateChangeListener(this);

    }


    @Override
    public void onWifiChanged(List<IWifi> wifis) {
        adapter.bindData(wifis);
    }

    @Override
    public void onConnectChanged(boolean status) {

    }

    @Override
    public void onStateChanged(State state) {

    }


    @Override
    protected void onDestroy() {
        manager.destroy();
        super.onDestroy();
    }
}
