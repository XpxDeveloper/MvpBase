package com.xpxcoder.libcore.rx;


import android.net.ParseException;
import android.util.MalformedJsonException;

import com.google.gson.JsonParseException;
import com.xpxcoder.libcore.base.IBaseView;
import com.xpxcoder.libcore.utils.ToastUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/8/29 16:58
 * @describe :
 */
public class RxThrowableConsumer implements Consumer<Throwable> {

    private IBaseView view;

    public RxThrowableConsumer() {
    }

    public RxThrowableConsumer(IBaseView view) {
        this.view = view;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        if (throwable instanceof SocketTimeoutException || throwable instanceof SocketException) {
            /* ToastUtils.showShortToastSafe("网络连接超时"); */
            if (view != null) {
                view.onLoadError();
            }
            handleConnectException();
        } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException || throwable instanceof HttpException) {
            /* ToastUtils.showShortToastSafe("网络连接断开"); */
            if (view != null) {
                view.onLoadError();
            }
            handleConnectException();
        } else if (throwable instanceof JsonParseException
                || throwable instanceof JSONException
                || throwable instanceof ParseException || throwable instanceof MalformedJsonException) {
            /*解析数据错误ToastUtils.showShortToastSafe("解析错误");*/
            if (view != null) {
                view.onLoadError();
            }
            handleConnectException();
        } else {
            handleThrowable(throwable);
        }
    }

    public void handleThrowable(Throwable throwable) {
        int code = 1;
        if (throwable instanceof RxUtils.ApiException) {
            code = ((RxUtils.ApiException) throwable).getCode();
        }
        if (view != null) {
            view.onLoadError();
            if (throwable instanceof RxUtils.ApiException) {
                view.onHandleErrorCode(((RxUtils.ApiException) throwable).getCode(), throwable.getMessage());
            }
        } else {
            ToastUtils.showShortToastSafe(throwable.getMessage());
        }
    }

    public void handleConnectException() {
    }

}
