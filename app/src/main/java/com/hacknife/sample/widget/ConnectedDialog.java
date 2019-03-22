package com.hacknife.sample.widget;

import android.content.Context;

import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.R;
import com.hacknife.sample.widget.base.Dialog;

@BindLayout(R.layout.dialog_connected)
public class ConnectedDialog<ConnectedDialogBriefnessor> extends Dialog {
    public ConnectedDialog(Context context) {
        super(context);
    }

    public void onCancelClick() {
        dismiss();
    }

    public interface OnConnectedDialogListener {
        void onConfirm(String password);
    }

    OnConnectedDialogListener onConnectedDialogListener;

    public ConnectedDialog setOnConnectedDialogListener(OnConnectedDialogListener onConnectDialogListener) {
        this.onConnectedDialogListener = onConnectDialogListener;
        return this;
    }

    public void onConfirmClick(String password) {
        if (onConnectedDialogListener != null)
            onConnectedDialogListener.onConfirm(password);
    }
}
