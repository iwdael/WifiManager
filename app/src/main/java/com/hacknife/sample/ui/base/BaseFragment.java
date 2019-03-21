package com.hacknife.sample.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.hacknife.briefness.Briefness;
import com.hacknife.briefness.Briefnessor;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : briefness
 */
public abstract class BaseFragment<T extends IBaseViewModel, B extends Briefnessor> extends Fragment implements IBaseView {
    @Inject
    protected T viewModel;
    protected B briefnessor;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(attachLayoutRes(), null);
        briefnessor = (B) Briefness.bind(this, view);
        injector();
        briefnessor.bindViewModel(viewModel);
        initView();
        return view;
    }

    protected abstract void injector();


    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null)
            viewModel.dettachView();
    }


    protected abstract int attachLayoutRes();

    protected void initView() {
    }

    @Override
    public Context applicationContext() {
        return getActivity().getApplication();
    }

    @Override
    public Activity context() {
        return getActivity();
    }
}
