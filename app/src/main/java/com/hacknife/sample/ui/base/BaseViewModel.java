package com.hacknife.sample.ui.base;

import android.app.Activity;
import android.content.Context;

import com.hacknife.briefness.Briefnessor;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : briefness
 */
public abstract class BaseViewModel<V extends IBaseView, M extends IBaseModel, B extends Briefnessor> extends AbsViewModel<V> implements IBaseViewModel {

    protected V view;
    protected M model;
    protected B briefnessor;

    public BaseViewModel(V view, B briefnessor) {
        this.attachView(view);
        this.attachBriefnessor(briefnessor);
        this.view = getView();
        this.briefnessor = (B) getBriefnessor();
        model = createModel();
    }

    protected abstract M createModel();

    @Override
    public Context applicationContext() {
        return view.applicationContext();
    }

    @Override
    public Activity context() {
        return view.context();
    }
}
