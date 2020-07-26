package com.xpxcoder.mvpbase.base;

import android.os.Bundle;

import com.xpxcoder.libbase.bean.wrap.CommonIntentWrap;
import com.xpxcoder.libbase.constants.IntentKey;
import com.xpxcoder.libcore.base.AbstractBaseFragment;
import com.xpxcoder.libcore.rx.RxPresenter;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 21:53(星期日)
 * Description:
 * ************************************************************/
public abstract class AppBaseFragment<T extends RxPresenter> extends AbstractBaseFragment<T> {
    protected CommonIntentWrap commonIntentWrap;

    @Override
    public void beforeInject() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            commonIntentWrap = bundle.getParcelable(IntentKey.KEY_COMMON_INTENT);
        }
    }
}
