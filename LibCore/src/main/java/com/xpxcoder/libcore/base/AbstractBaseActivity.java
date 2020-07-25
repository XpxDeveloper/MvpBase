package com.xpxcoder.libcore.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.xpxcoder.libcore.AbstractCoreApp;
import com.xpxcoder.libcore.rx.RxBus;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.rx.RxPresenter;
import com.xpxcoder.libcore.rx.RxUtils;
import com.xpxcoder.libcore.utils.StatusBarCompat;
import com.xpxcoder.libcore.utils.StatusBarUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**************************************************************
 * @author: xpxCoder.
 * CreateTime: 2020-07-25 23:04(星期六)
 * Description:
 * ************************************************************/
public abstract class AbstractBaseActivity<T extends RxPresenter> extends AbstractActivity {

    @Inject
    protected T mPresenter;

    private Unbinder mBind;

    protected boolean isTranslucentStatusBar = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        someAndroidModelFit();
        AbstractCoreApp.addActivity(this);
        beforeSetContentView();
        setContentView(getLayoutId());
        if (isTranslucentStatusBar) {
            StatusBarCompat.translucentStatusBar(this, isTranslucentStatusBar);
        }

        StatusBarUtil.darkMode(this, isDarkMode());
        mBind = ButterKnife.bind(this);
        beforeInject(savedInstanceState);
        inject();
        registerDefaultEvent();
        if (mPresenter != null) {
            mPresenter.attachView(this, mProvider);
        }
        initView();
        initData();
    }

    private void someAndroidModelFit() {
//        正常情况下，点击 Home 键回到桌面，App 进程没有被杀掉，再次进入 APP 应该都是回到之前显示的页面。
//        但是在某些不知名的情况下，也可能是某些手机会有这个问题。点击了 APP图标 是重新打开 APP，但是从近期任务栏里面打开是回到之前已经打开的页面。
//        华为p30 opp6都存在这个问题
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent
                    .ACTION_MAIN)) {
                finish();
                return;
            }
        }
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
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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

    /**
     * 处理默认订阅事件
     *
     * @param event
     */
    public abstract void handleDefaultEvent(RxEvent event);

    public void setTranslucentStatusBar(boolean translucentStatusBar) {
        isTranslucentStatusBar = translucentStatusBar;
    }

    protected void beforeInject(Bundle savedInstanceState) {

    }
}

