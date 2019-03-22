package com.hacknife.sample.widget;

import android.content.Context;

import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.BriefnessInjector;
import com.hacknife.sample.R;
import com.hacknife.sample.widget.base.Dialog;


@BindLayout(R.layout.dialog_no_pass)
public class NoPassDialog extends Dialog<NoPassDialogBriefnessor> {
    String content;

    public NoPassDialog(Context context) {
        super(context);
    }


    public void onCancelClick() {
        dismiss();
    }

    public interface OnConnectDialogListener {
        void onConfirm();
    }

    public NoPassDialog content(String content) {
        this.content = content;
        return this;
    }

    @Override
    protected void initView() {
        BriefnessInjector.injector(briefnessor.tv_content, content);
    }

    OnConnectDialogListener onConnectDialogListener;

    public NoPassDialog setOnConnectDialogListener(OnConnectDialogListener onConnectDialogListener) {
        this.onConnectDialogListener = onConnectDialogListener;
        return this;
    }

    public void onConfirmClick() {
        dismiss();
        if (onConnectDialogListener != null)
            onConnectDialogListener.onConfirm();
    }
}
