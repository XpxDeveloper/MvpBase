package com.xpxcoder.libcore.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity;
import com.xpxcoder.libcore.R;
import com.xpxcoder.libcore.dialog.LoadingProgressDialog;
import com.xpxcoder.libcore.utils.StatusBarUtils;
import com.xpxcoder.libcore.utils.ToastUtils;

import java.util.List;

import javax.inject.Inject;

/**************************************************************
 * @author: xpxCoder.
 * CreateTime: 2020-07-25 21:41(星期六)
 * Description:
 * ************************************************************/
public abstract class AbstractRxActivity extends RxAppCompatActivity implements IBaseView {

    protected final LifecycleProvider<ActivityEvent> mProvider = this;
    protected final String TAG = getClass().getSimpleName();

    @Inject
    protected RxPermissions mRxPermissions;
    @Inject
    protected Activity mActivity;
    @Inject
    protected Context mContext;
    @Inject
    protected LoadingProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context base) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            final Resources res = base.getResources();
            final Configuration config = res.getConfiguration();
            /* 1 设置正常字体大小的倍数 */
            config.fontScale = 1.0f;
            final Context newContext = base.createConfigurationContext(config);
            super.attachBaseContext(newContext);
        } else {
            super.attachBaseContext(base);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 显示加载进度dialog
     */
    public void showProgressDialog(String text) {
        if (progressDialog.isShowing()) {
            return;
        }
        progressDialog.show(text);
    }

    /**
     * 隐藏加载进度dialog
     */
    public void hideProgressDialog() {
        if (!progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    public void setSupportFragment(int contentId, Fragment fragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(contentId, fragment);
        beginTransaction.commitAllowingStateLoss();
    }

    public void switchSupportFragment(int containerId, Fragment from, Fragment to) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            beginTransaction.hide(from).add(containerId, to).commitAllowingStateLoss();
        } else {
            beginTransaction.hide(from).show(to).commitAllowingStateLoss();
        }
    }

    /**
     * 沉浸式状态栏填充布局
     *
     * @param view
     */
    public void setStatusBarFillView(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = StatusBarUtils.getStatusBarHeight(this);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 打开界面
     */
    public void startActivity(Class<?> dest) {
        Intent intent = new Intent(mContext, dest);
        mContext.startActivity(intent);
    }

    /**
     * 打开界面
     */
    public void startActivity(Class<?> dest, Bundle bundle) {
        Intent intent = new Intent(mContext, dest);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragments) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onStartLoad() {
        showProgressDialog(getString(R.string.loaded_wait_moment));
    }

    @Override
    public void onLoadSuccess() {
        hideProgressDialog();
    }

    @Override
    public void onLoadError() {
        hideProgressDialog();
    }

    @Override
    public void onHandleErrorCode(int code, String message) {
        ToastUtils.showShortToast(message);
    }
}
