package com.hacknife.sample;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hacknife.refresh.core.RefreshLayout;
import com.hacknife.refresh.core.api.DefaultRefreshFooterCreator;
import com.hacknife.refresh.core.api.DefaultRefreshHeaderCreator;
import com.hacknife.refresh.core.api.Refresh;
import com.hacknife.refresh.core.api.RefreshFooter;
import com.hacknife.refresh.core.api.RefreshHeader;
import com.hacknife.refresh.core.footer.ClassicsFooter;
import com.hacknife.refresh.core.header.ClassicsHeader;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull Refresh layout) {
                return new ClassicsFooter(context);
            }
        });
        RefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull Refresh layout) {
                return new ClassicsHeader(context);
            }
        });
    }
}
