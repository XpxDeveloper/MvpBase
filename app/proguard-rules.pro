# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#优化  不优化输入的类文件
-dontoptimize
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
#忽略警告
-ignorewarning
#保护注解
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn android.support.**
-dontwarn android.support.v4.**
-dontwarn android.support.v7.**
-dontwarn com.baidu.mobstat.**,com.alipay.android.app.**,com.tencent.**,com.baidu.**,org.apache.http.**,com.umeng.**
-dontwarn com.alibaba.fastjson.**

-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep public class com.android.vending.licensing.ILicensingService

-keep public class **.R$*{
   public static final int *;
}

-keep class android.net.http.** { *;}

-keep class android.content.** { *; }

#保持 native 方法不被混淆
-keepclasseswithmembers,allowshrinking class * {
    native <methods>;
}
# Keep native methods
-keepclassmembers class * {
    native <methods>;
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepattributes EnclosingMethod

-keepclassmembers @interface * {
    *;
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
}

-keepclasseswithmembers class * {                                                # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);      # 保持自定义控件类不被混淆
}

-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
   public void *(android.view.View);
}

-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}

-keep class android.support.v4.view.** {
    <fields>;
    <methods>;
}

-keep class android.support.v4.content.** {
    <fields>;
    <methods>;
}

#----------------library------------------#
-keep class com.xpxcoder.libbase.**{*;}
-keep class com.xpxcoder.libcore.**{*;}
-keep class com.xpxcoder.libwidget.**{*;}


#----------------第三方库------------------#
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

# Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
# 如果使用了Gson之类的工具要使被它解析的JavaBean类即实体类不被混淆。


# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#lombok
-dontwarn lombok.**
-keep class lombok.** { *; }