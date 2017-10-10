# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

# MobShareSDK
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-keep class com.mob.**{*;}
-dontwarn com.mob.**
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

#腾讯Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#百度推送
 -libraryjars libs/pushservice-VERSION.jar
 -dontwarn com.baidu.**
 -keep class com.baidu.**{*; }
 #友盟推送
 -dontwarn com.taobao.**
 -dontwarn anet.channel.**
 -dontwarn anetwork.channel.**
 -dontwarn org.android.**
 -dontwarn org.apache.thrift.**
 -dontwarn com.xiaomi.**
 -dontwarn com.huawei.**

 -keepattributes *Annotation*

 -keep class com.taobao.** {*;}
 -keep class org.android.** {*;}
 -keep class anet.channel.** {*;}
 -keep class com.umeng.** {*;}
 -keep class com.xiaomi.** {*;}
 -keep class com.huawei.** {*;}
 -keep class org.apache.thrift.** {*;}

 -keep class com.alibaba.sdk.android.**{*;}
 -keep class com.ut.**{*;}
 -keep class com.ta.**{*;}

 -keep public class **.R$*{
    public static final int *;
 }

 #（可选）避免Log打印输出
 -assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
  }


#  百度地图
-keep class com.baidu.** {*;}
-keep class vi.com.** {*;}
-dontwarn com.baidu.**
