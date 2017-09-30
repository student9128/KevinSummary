package com.kevin.tech.kevinsummary.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.uitls.LogK;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/5.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class BaseApplication extends Application {
    private static Context mContext;
    public static int stateCount;


    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
//                .addStrategy(new CustomSDCardLoader())                  // 自定义加载策略，指定SDCard路径[可选]
//                .addHookInflater(new SkinHookAutoLayoutViewInflater())  // hongyangAndroid/AndroidAutoLayout[可选]http://blog.csdn.net/lmj623565791/article/details/49990941#comments
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//                .addInflater(new SkinCircleImageViewInflater())         // hdodenhof/CircleImageView[可选]
//                .addInflater(new SkinFlycoTabLayoutInflater())          // H07000223/FlycoTabLayout[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();

        /********MOB分享**********/
        MobSDK.init(this, Constants.MOB_APP_KEY, Constants.MOB_APP_SECRET);

        /********极光推送**********/
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        /********腾讯Bugly**********/
        CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, false);

        /********友盟推送**********/
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogK.d("umeng", "==============注册成功===============：\t" + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogK.d("umeng", "===============注册失败=============：\t" + s+"=---="+s1);

            }
        });
    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    Log.i("后台", appProcess.processName);
                    return true;
                } else {
                    Log.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     * 需要权限
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private void initActivityLife() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                stateCount++;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                stateCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /*
    在某些情景下依旧没办法使用，上面这种方式是在Activity-onStop后进行自减，
    如果我们是监听锁屏后在屏幕暗下后做处理，我们的App在屏幕暗下之前仍处于前台，
    在得到锁屏通知之后这个时候判断自己的App是否处于前后台，得到的结果会是我们的应用处于后台。
    因为我们在监听锁屏系统广播Screen-on or Screen off.，在收到Screen off.的时候,
    我们的App的处于栈顶的Activity已经onStop了。所以得不到正确的结果
     */
    public static int isBackground() {
        if (BaseApplication.stateCount == 0) {
            //后台
            return 0;
        } else {
            //前台
            return 1;
        }
    }
}
