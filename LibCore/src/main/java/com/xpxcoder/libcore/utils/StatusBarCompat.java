package com.xpxcoder.libcore.utils;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-25 23:16(星期六)
 * Description:
 * ************************************************************/
public class StatusBarCompat {
    public StatusBarCompat() {
    }

    static int calculateStatusBarColor(int color, int alpha) {
        float a = 1.0F - (float) alpha / 255.0F;
        int red = color >> 16 & 255;
        int green = color >> 8 & 255;
        int blue = color & 255;
        red = (int) ((double) ((float) red * a) + 0.5D);
        green = (int) ((double) ((float) green * a) + 0.5D);
        blue = (int) ((double) ((float) blue * a) + 0.5D);
        return -16777216 | red << 16 | green << 8 | blue;
    }

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int statusColor, int alpha) {
        setStatusBarColor(activity, calculateStatusBarColor(statusColor, alpha));
    }

    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int statusColor) {
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarCompatLollipop.setStatusBarColor(activity, statusColor);
        } else if (Build.VERSION.SDK_INT >= 19) {
            StatusBarCompatKitKat.setStatusBarColor(activity, statusColor);
        }

    }

    public static void translucentStatusBar(@NonNull Activity activity) {
        translucentStatusBar(activity, false);
    }

    public static void translucentStatusBar(@NonNull Activity activity, boolean hideStatusBarBackground) {
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarCompatLollipop.translucentStatusBar(activity, hideStatusBarBackground);
        } else if (Build.VERSION.SDK_INT >= 19) {
            StatusBarCompatKitKat.translucentStatusBar(activity);
        }

    }

    public static void setStatusBarColorForCollapsingToolbar(@NonNull Activity activity, AppBarLayout appBarLayout, CollapsingToolbarLayout collapsingToolbarLayout, Toolbar toolbar, @ColorInt int statusColor) {
        if (Build.VERSION.SDK_INT >= 21) {
            StatusBarCompatLollipop.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, (Toolbar) null, statusColor);
        } else if (Build.VERSION.SDK_INT >= 19) {
            StatusBarCompatKitKat.setStatusBarColorForCollapsingToolbar(activity, appBarLayout, collapsingToolbarLayout, (Toolbar) null, statusColor);
        }

    }
}

