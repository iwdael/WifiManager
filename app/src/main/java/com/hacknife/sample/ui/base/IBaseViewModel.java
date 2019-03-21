package com.hacknife.sample.ui.base;

import android.app.Activity;
import android.content.Context;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : briefness
 */
public interface IBaseViewModel {

    Context applicationContext();

    Activity context();

    void dettachView();
}
