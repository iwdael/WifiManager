package com.hacknife.sample.widget;

import android.content.Context;

import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.R;
import com.hacknife.sample.widget.base.Dialog;

@BindLayout(R.layout.dialog_connect)
public class ConnectDialog extends Dialog<ConnectDialogBriefnessor> {
    public ConnectDialog(Context context) {
        super(context);
    }

    public void onCancelClick() {
        dismiss();
    }

    public interface OnConnectDialogListener {
        void onConfirm(String password);
    }

    OnConnectDialogListener onConnectDialogListener;

    public ConnectDialog setOnConnectDialogListener(OnConnectDialogListener onConnectDialogListener) {
        this.onConnectDialogListener = onConnectDialogListener;
        return this;
    }

    public void onConfirmClick(String password) {
        dismiss();
        if (onConnectDialogListener != null)
            onConnectDialogListener.onConfirm(password);
    }
}
