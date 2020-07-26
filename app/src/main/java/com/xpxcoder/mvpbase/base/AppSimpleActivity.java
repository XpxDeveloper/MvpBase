package com.xpxcoder.mvpbase.base;

import android.os.Bundle;
import android.os.Handler;

import com.xpxcoder.libbase.bean.wrap.CommonIntentWrap;
import com.xpxcoder.libbase.constants.IntentKey;
import com.xpxcoder.libcore.base.AbstractBaseSimpleActivity;
import com.xpxcoder.libcore.rx.RxEvent;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 21:57(星期日)
 * Description:
 * ************************************************************/
public abstract class AppSimpleActivity extends AbstractBaseSimpleActivity {

    private Handler handler = new Handler();

    @Inject
    protected Retrofit retrofit;
    protected CommonIntentWrap commonIntentWrap;


    @Override
    protected void beforeInject(Bundle savedInstanceState) {
        super.beforeInject(savedInstanceState);
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
