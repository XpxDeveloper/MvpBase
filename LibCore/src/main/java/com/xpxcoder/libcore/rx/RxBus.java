package com.xpxcoder.libcore.rx;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;

/**
 * @author: Mai_Xiao_Peng
 * email: Mai_Xiao_Peng@163.com
 * time: 2017/5/5
 * desc:负责维护组件中的通信
 */
public class RxBus {

    private final FlowableProcessor<Object> bus;

    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
        bus.onBackpressureBuffer(Integer.MAX_VALUE);
    }

    public static RxBus getInstance() {
        return RxBusHolder.INSTANCE;
    }

    private static class RxBusHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    /**
     * 发出事件序列
     *
     * @param object
     */
    public void post(Object object) {
        bus.onNext(object);
    }

    /**
     * 过滤事件序列
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    /**
     * 默认过滤事件序列并消费
     *
     * @param eventType
     * @param consumer
     * @param <T>
     * @return
     */
    public <T> Disposable toDefaultFlowable(Class<T> eventType, Consumer<T> consumer) {
        return bus.ofType(eventType).compose(RxUtils.rxSchedulerHelper()).subscribe(consumer);
    }
}
