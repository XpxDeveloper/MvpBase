package com.xpxcoder.libcore.rx;


import com.trello.rxlifecycle4.LifecycleProvider;
import com.trello.rxlifecycle4.android.ActivityEvent;
import com.xpxcoder.libcore.base.IBasePresenter;
import com.xpxcoder.libcore.base.IBaseView;

import java.util.LinkedList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author: Mai_Xiao_Peng
 * email  : Mai_Xiao_Peng@163.com
 * time  : 2017/4/24
 */

public class RxPresenter<T extends IBaseView> implements IBasePresenter<T> {

    protected LifecycleProvider<ActivityEvent> mProvider = null;

    protected final String TAG = getClass().getSimpleName();

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected LinkedList<Object> mRxTask = new LinkedList<>();

    public RxPresenter() {
    }

    /**
     * 将Rxjava操作对象添加到集合中统一管理
     *
     * @param subscription
     */
    public void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 将添加到集合中的Rxjava操作对象进行统一资源释放，防止内存泄漏
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    /**
     * 对Activity的View进行绑定
     *
     * @param view
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 对Activity的View,生命周期进行绑定
     *
     * @param view
     */
    @Override
    public void attachView(T view, LifecycleProvider lifecycleProvider) {
        this.mView = view;
        this.mProvider = lifecycleProvider;
    }

    /**
     * 取消绑定View
     */
    @Override
    public void detachView() {
        mView = null;
        mProvider = null;
    }

    /**
     * 弹入任务
     *
     * @param task
     */
    public synchronized void pushTask(Object task) {
        mRxTask.push(task);
    }

    /**
     * 弹出任务
     *
     * @param task
     */
    public synchronized void popTask(Object task) {
        mRxTask.remove(task);
    }

//    /**
//     * 取消绑定View
//     */
//    @Override
//    public void detachView() {
//        mView = null;
//        unSubscribe();
//    }

}
