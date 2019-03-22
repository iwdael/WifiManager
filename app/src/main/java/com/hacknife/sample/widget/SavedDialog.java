package com.hacknife.sample.widget;

import android.content.Context;

import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.BriefnessInjector;
import com.hacknife.sample.R;
import com.hacknife.sample.widget.base.Dialog;

@BindLayout(R.layout.dialog_saved)
public class SavedDialog extends Dialog<SavedDialogBriefnessor> {
    String content;

    public SavedDialog(Context context) {
        super(context);
    }

    public void onCancelClick() {
        dismiss();
        if (onClearDialogListener != null)
            onClearDialogListener.onConfirm();
    }

    public interface OnConnectDialogListener {
        void onConfirm();
    }

    public interface OnClearDialogListener {
        void onConfirm();
    }

    OnConnectDialogListener onConnectDialogListener;
    OnClearDialogListener onClearDialogListener;

    public SavedDialog content(String content) {
        this.content = content;
        return this;
    }

    @Override
    protected void initView() {
        BriefnessInjector.injector(briefnessor.tv_content, content);
    }

    public SavedDialog setOnConnectDialogListener(OnConnectDialogListener onConnectDialogListener) {
        this.onConnectDialogListener = onConnectDialogListener;
        return this;
    }

    public SavedDialog setOnClearDialogListener(OnClearDialogListener onClearDialogListener) {
        this.onClearDialogListener = onClearDialogListener;
        return this;
    }

    public void onConfirmClick() {
        dismiss();
        if (onConnectDialogListener != null)
            onConnectDialogListener.onConfirm();
    }
}
