package com.xpxcoder.libcore.base;

import com.trello.rxlifecycle4.LifecycleProvider;

/**************************************************************
 * @author: xpxCoder.
 * CreateTime: 2020-07-25 23:09(星期六)
 * Description:
 * ************************************************************/
public interface IBasePresenter<T extends IBaseView> {

    /**
     * attachView
     *
     * @param view
     */
    void attachView(T view);


    /**
     * attachView
     *
     * @param view
     * @param lifecycleProvider
     */
    void attachView(T view, LifecycleProvider lifecycleProvider);

    /**
     * detachView
     */
    void detachView();

}

