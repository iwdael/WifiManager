package com.hacknife.sample.ui.injector.components;

import com.hacknife.sample.ui.injector.modules.WifiModule;
import com.hacknife.sample.ui.WifiActivity;

import dagger.Component;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
@Component(modules = WifiModule.class)
public interface WifiActivityComponent {
    void inject(WifiActivity activity);
}