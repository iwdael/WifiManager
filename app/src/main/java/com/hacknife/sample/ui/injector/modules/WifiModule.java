package com.hacknife.sample.ui.injector.modules;

import com.hacknife.sample.ui.WifiActivityBriefnessor;

import com.hacknife.sample.ui.viewmodel.WifiViewModel;
import com.hacknife.sample.ui.viewmodel.i.IWifiViewModel;
import com.hacknife.sample.ui.view.IWifiView;

import dagger.Module;
import dagger.Provides;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
@Module
public class WifiModule {
    private IWifiView view;
    private WifiActivityBriefnessor briefnessor;

    public WifiModule(IWifiView view, WifiActivityBriefnessor briefnessor) {
        this.view = view;
        this.briefnessor = briefnessor;
    }

    @Provides
    IWifiViewModel provideViewModel() {
        return new WifiViewModel(view, briefnessor);
    }
}
