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
import androidx.annotation.Nullable;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle4.components.RxFragment;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.utils.StatusBarUtils;
import com.xpxcoder.libcore.utils.ToastUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 10:28(星期日)
 * Description:
 * ************************************************************/
public abstract class AbstractBaseSimpleFragment extends RxFragment implements IBaseView {

    protected final String TAG = getClass().getSimpleName();
    protected View mView;
    @Inject
    public Context mContext;
    @Inject
    public RxPermissions mRxPermissions;
    @Inject
    public Activity mActivity;

    private Unbinder mUnBind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        beforeInject();
        initInject();
        mView = View.inflate(mContext, getLayoutId(), null);
        mUnBind = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
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
     * initInject
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
     * initData
     */
    public abstract void initData();

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

