package com.hacknife.sample.ui.model;

import com.hacknife.sample.ui.base.BaseModel;
import com.hacknife.sample.ui.model.i.IWifiModel;
import com.hacknife.sample.ui.viewmodel.i.IWifiViewModel;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
public class WifiModel extends BaseModel<IWifiViewModel> implements IWifiModel {

    public WifiModel(IWifiViewModel viewmodel) {
        super(viewmodel);
    }
}