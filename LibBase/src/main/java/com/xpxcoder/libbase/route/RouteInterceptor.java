package com.xpxcoder.libbase.route;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;


/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/9/20 16:26
 * @describe :
 */

@Interceptor(priority = 1)
public class RouteInterceptor implements IInterceptor {

    private String TAG = getClass().getSimpleName();

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e(TAG, "routeInterceptor process" + postcard.getPath());
        callback.onContinue(postcard);

//        LoginInfo loginInfo = SpManager.getLoginInfo();
//        if (postcard.getPath().startsWith("/onecar/ucenter") ||
//                postcard.getPath().startsWith("/onecar/order") ||
//                postcard.getPath().startsWith("/onecar/message")||postcard.getPath().startsWith("/onecar/car")
//        ) {
//            if (loginInfo != null && loginInfo.getUserId() > 0) {
//                callback.onContinue(postcard);
//            } else {
//                Bundle bundle = postcard.getExtras();
//                bundle.putString(IntentKey.KEY_ROUTER_PATH, postcard.getPath());
//                ARouter.getInstance().build(RouteMap.ACTIVITY_START_LOGINONEPASS)
//                        .withBundle(IntentKey.KEY_ROUTER_BUNDLE, bundle)
//                        .navigation();
//                callback.onInterrupt(null);
//            }
//        }
//        else if (postcard.getPath().startsWith("/onecar/car")) {
//            if (loginInfo != null && loginInfo.getUserId() > 0) {
//                callback.onContinue(postcard);
//            } else {
//                ARouter.getInstance().build(RouteMap.ACTIVITY_START_LOGINONEPASS)
//                        .navigation();
//                callback.onInterrupt(null);
//            }
//        }
//        else if (postcard.getPath().equals(RouteMap.ACTIVITY_COMMON_WEB)) {
//            //特殊处理待整理
//            CommonWebWrap commonWebWrap = postcard.getExtras().getParcelable(IntentKey.WEB_ACTIVITY_KEY_INFO);
//            if (commonWebWrap.isVerifyLoginInfo()) {
//                if (loginInfo != null && loginInfo.getUserId() > 0) {
//                    callback.onContinue(postcard);
//                } else {
//                    Bundle bundle = postcard.getExtras();
//                    bundle.putString(IntentKey.KEY_ROUTER_PATH, postcard.getPath());
//                    ARouter.getInstance().build(RouteMap.ACTIVITY_START_LOGINONEPASS)
//                            .withBundle(IntentKey.KEY_ROUTER_BUNDLE, bundle)
//                            .navigation();
//                    callback.onInterrupt(null);
//                }
//            } else {
//                callback.onContinue(postcard);
//            }
//        } else {
//            callback.onContinue(postcard);
//        }
    }

    @Override
    public void init(Context context) {
        Log.e(TAG, "routeInterceptor init");
    }

}
