package com.xpxcoder.libcore.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.components.RxFragment;
import com.xpxcoder.libcore.rx.RxBus;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.rx.RxPresenter;
import com.xpxcoder.libcore.rx.RxUtils;
import com.xpxcoder.libcore.utils.StatusBarUtil;
import com.xpxcoder.libcore.utils.StatusBarUtils;
import com.xpxcoder.libcore.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.rxjava3.annotations.Nullable;

/**
 * @author: Mai_Xiao_Peng
 * email  : Mai_Xiao_Peng@163.com
 * time  : 2017/4/20
 * desc : 懒加载fragment 配合FragmentStatePagerAdapter 使用
 */

public abstract class AbstractBaseFragment<T extends RxPresenter> extends RxFragment implements IBaseView {

    protected final LifecycleProvider<FragmentEvent> mProvider
            = this;

    protected final String TAG = getClass().getSimpleName();

    protected View mView;

    @Inject
    public Context mContext;
    @Inject
    public RxPermissions mRxPermissions;
    @Inject
    public Activity mActivity;
    @Inject
    public T mPresenter;
    private Unbinder mUnBind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        beforeInject();
        initInject();
        registerDefaultEvent();
        mView = View.inflate(mContext, getLayoutId(), null);
        StatusBarUtil.darkMode(getActivity(), isDarkMode());
        mUnBind = ButterKnife.bind(this, mView);
        if (mPresenter != null) {
            mPresenter.attachView(this, mProvider);
        }
        initView();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initBundle(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnBind.unbind();
        super.onDestroyView();
    }

    /**
     * getLayoutId
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     *
     */
    public abstract void initInject();

    /**
     * beforeInject
     */
    public abstract void beforeInject();

    /**
     * initView
     */
    public abstract void initView();

    /**
     * 設置是否深色樣式
     *
     * @return
     */
    protected boolean isDarkMode() {
        return false;
    }

    public abstract void initData();

    protected void initBundle(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onStartLoad() {
        ((AbstractBaseActivity) getActivity()).onStartLoad();
    }

    @Override
    public void onLoadSuccess() {
        ((AbstractBaseActivity) getActivity()).onLoadSuccess();
    }

    @Override
    public void onLoadError() {
        ((AbstractBaseActivity) getActivity()).onLoadError();
    }

    @Override
    public void onHandleErrorCode(int code, String message) {
        ToastUtils.showShortToast(message);
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
     * 处理默认订阅事件
     *
     * @param event
     */
    public void handleDefaultEvent(RxEvent event) {

    }

    /**
     * 沉浸式状态栏填充布局
     *
     * @param view
     */
    public void setStatusBarFillView(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = StatusBarUtils.getStatusBarHeight(getActivity());
        view.setLayoutParams(layoutParams);
    }

    public void setSupportFragment(int contentId, Fragment fragment) {
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(contentId, fragment);
        beginTransaction.commitAllowingStateLoss();
    }
}
