package com.hacknife.sample.widget.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hacknife.briefness.Briefness;
import com.hacknife.briefness.Briefnessor;
import com.hacknife.sample.R;

public abstract class Dialog<T extends Briefnessor> extends android.app.Dialog {
    protected T briefnessor;

    public Dialog(Context context) {
        this(context, R.style.common_dialog_transparent_shadowed);
    }

    public Dialog(Context context, int themeResId) {
        super(context, R.style.common_dialog_transparent_shadowed);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        briefnessor = (T) Briefness.bind(this, LayoutInflater.from(getContext()));
        setContentView(briefnessor.inflate());
        initView();
    }

    protected void initView() {
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }
}
