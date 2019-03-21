package com.hacknife.sample.ui.base;

import android.app.Activity;
import android.content.Context;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : briefness
 */
public abstract class BaseModel<VM extends IBaseViewModel> implements IBaseModel {
    protected VM viewModel;

    public BaseModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public Context applicationContext() {
        return viewModel.applicationContext();
    }

    @Override
    public Activity context() {
        return viewModel.context();
    }
}
