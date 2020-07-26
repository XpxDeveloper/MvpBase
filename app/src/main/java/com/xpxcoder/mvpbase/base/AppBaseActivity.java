package com.xpxcoder.mvpbase.base;

import android.os.Bundle;

import com.xpxcoder.libbase.bean.wrap.CommonIntentWrap;
import com.xpxcoder.libbase.constants.IntentKey;
import com.xpxcoder.libcore.base.AbstractBaseActivity;
import com.xpxcoder.libcore.rx.RxEvent;
import com.xpxcoder.libcore.rx.RxPresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 10:49(星期日)
 * Description:
 * ************************************************************/
public abstract class AppBaseActivity<T extends RxPresenter> extends AbstractBaseActivity<T> {

    @Inject
    public Retrofit retrofit;

    protected CommonIntentWrap commonIntentWrap;


    @Override
    protected void beforeInject(Bundle savedInstanceState) {
        commonIntentWrap = getIntent().getParcelableExtra(IntentKey.KEY_COMMON_INTENT);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 处理默认订阅事件
     *
     * @param event
     */
    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

}
