package com.hacknife.sample.ui.viewmodel;

import com.hacknife.sample.ui.WifiActivityBriefnessor;

import com.hacknife.sample.ui.base.BaseViewModel;
import com.hacknife.sample.ui.model.WifiModel;
import com.hacknife.sample.ui.view.IWifiView;
import com.hacknife.sample.ui.viewmodel.i.IWifiViewModel;
import com.hacknife.sample.ui.model.i.IWifiModel;


/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public class WifiViewModel extends BaseViewModel<IWifiView, IWifiModel, WifiActivityBriefnessor> implements IWifiViewModel {

    public WifiViewModel(IWifiView view, WifiActivityBriefnessor briefnessor) {
        super(view, briefnessor);
    }

    @Override
    protected IWifiModel createModel() {
        return new WifiModel(this);
    }


}