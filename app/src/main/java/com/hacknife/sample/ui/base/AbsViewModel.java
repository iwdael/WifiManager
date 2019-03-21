package com.hacknife.sample.ui.base;

import com.hacknife.briefness.Briefnessor;

import java.lang.ref.WeakReference;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : briefness
 */
public abstract class AbsViewModel<T> implements IBaseViewModel {
    protected WeakReference<T> mViewRef;
    protected WeakReference<Briefnessor> mBriefnessorRef;

    protected void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected void attachBriefnessor(Briefnessor briefnessor) {
        mBriefnessorRef = new WeakReference<Briefnessor>(briefnessor);
    }

    protected T getView() {
        return mViewRef.get();
    }

    protected Briefnessor getBriefnessor() {
        return mBriefnessorRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null & mBriefnessorRef != null & mBriefnessorRef.get() != null;
    }

    public void dettachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        if (mBriefnessorRef != null) {
            mBriefnessorRef.clear();
            mBriefnessorRef = null;
        }
    }
}
