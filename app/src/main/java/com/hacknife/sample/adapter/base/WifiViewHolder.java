package com.hacknife.sample.adapter.base;


import android.view.View;

import com.hacknife.briefness.BindLayout;
import com.hacknife.sample.R;
import com.hacknife.sample.adapter.base.i.BaseRecyclerViewHolder;
import com.hacknife.wifimanager.IWifi;

/**
 * author  : hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : MVVM
 */
@BindLayout(R.layout.item_wifi)
public class WifiViewHolder extends BaseRecyclerViewHolder<IWifi, WifiViewHolderBriefnessor> {


    public WifiViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(IWifi entity) {
        briefnessor.tv_name.setText(entity.name());
        briefnessor.tv_desc.setText(entity.description2());
    }


}