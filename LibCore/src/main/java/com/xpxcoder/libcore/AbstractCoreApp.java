package com.xpxcoder.libcore;

import android.app.Activity;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.xpxcoder.libcore.utils.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/7/25 11:39
 * @describe :
 */
public abstract class AbstractCoreApp extends MultiDexApplication {

    public final String TAG = getClass().getSimpleName();

    public static Set<Activity> sAllActivities;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        onAppCreate();
    }

    /**
     * onAppCreate
     */
    public abstract void onAppCreate();

    public static void addActivity(Activity act) {
        if (sAllActivities == null) {
            sAllActivities = new HashSet<>();
        }
        sAllActivities.add(act);
    }

    public static void removeActivity(Activity act) {
        if (sAllActivities != null) {
            sAllActivities.remove(act);
        }
    }

    public static void exitApp() {
        if (sAllActivities != null) {
            synchronized (sAllActivities) {
                for (Activity act : sAllActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

}
