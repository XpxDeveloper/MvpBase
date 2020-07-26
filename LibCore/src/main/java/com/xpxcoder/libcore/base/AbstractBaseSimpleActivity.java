package com.xpxcoder.libcore.base;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.xpxcoder.libcore.AbstractCoreApp;
import com.xpxcoder.libcore.rx.RxBus;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.rx.RxUtils;
import com.xpxcoder.libcore.utils.StatusBarCompat;
import com.xpxcoder.libcore.utils.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 10:26(星期日)
 * Description:
 * ************************************************************/
public abstract class AbstractBaseSimpleActivity extends AbstractRxActivity {

    private Unbinder mBind;

    protected boolean isTranslucentStatusBar = true;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        beforeSetContentView();
        setContentView(getLayoutId());
        if (isTranslucentStatusBar) {
            StatusBarCompat.translucentStatusBar(this, isTranslucentStatusBar);
        }
        StatusBarUtil.darkMode(this, isDarkMode());
        mBind = ButterKnife.bind(this);
        AbstractCoreApp.addActivity(this);
        beforeInject(savedInstanceState);
        inject();
        registerDefaultEvent();
        initView();
        initData();
    }

    protected void beforeSetContentView() {
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mBind.unbind();
        AbstractCoreApp.removeActivity(this);
        super.onDestroy();
    }

    /**
     * 設置是否深色樣式
     *
     * @return
     */
    protected boolean isDarkMode() {
        return false;
    }

    /**
     * 注册rxbus订阅事件
     */
    public void registerDefaultEvent() {
        RxBus.getInstance().toFlowable(RxEvent.class).compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle())
                .subscribe(event -> handleDefaultEvent(event));
    }

    /**
     * getLayoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * inject
     */
    protected abstract void inject();

    /**
     * initView
     */
    protected abstract void initView();

    /**
     * initData
     */
    protected abstract void initData();

    protected void beforeInject(Bundle savedInstanceState) {

    }

    /**
     * 处理默认订阅事件
     *
     * @param event
     */
    public abstract void handleDefaultEvent(RxEvent event);

    public void setTranslucentStatusBar(boolean translucentStatusBar) {
        isTranslucentStatusBar = translucentStatusBar;
    }

}

