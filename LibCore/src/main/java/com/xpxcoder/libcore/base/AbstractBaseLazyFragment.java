package com.xpxcoder.libcore.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.android.FragmentEvent;
import com.trello.rxlifecycle4.components.RxFragment;
import com.xpxcoder.libcore.rx.RxBus;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.rx.RxPresenter;
import com.xpxcoder.libcore.rx.RxUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 10:22(星期日)
 * Description:
 * ************************************************************/
public abstract class AbstractBaseLazyFragment<T extends RxPresenter> extends RxFragment implements IBaseView {

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

    private boolean isViewCreated;
    private boolean isVisible;
    private boolean isDataLoaded;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        initInject();
        registerDefaultEvent();
        mView = View.inflate(mContext, getLayoutId(), null);
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
        isViewCreated = true;
        lazyLoad();
    }

    /**
     * fangment懒加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnBind.unbind();
        super.onDestroyView();
    }

    protected void lazyLoad() {
        if (isVisible && isViewCreated && !isDataLoaded) {
            isDataLoaded = true;
            initData();
        }
    }

    /**
     * getLayoutId
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * initInject
     */
    public abstract void initInject();

    /**
     * initView
     */
    public abstract void initView();

    /**
     * initData
     */
    public abstract void initData();

    @Override
    public void onStartLoad() {
        ((AbstractRxActivity) getActivity()).onStartLoad();
    }

    @Override
    public void onLoadSuccess() {
        ((AbstractRxActivity) getActivity()).onLoadSuccess();
    }

    @Override
    public void onLoadError() {
        ((AbstractRxActivity) getActivity()).onLoadError();
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

}

