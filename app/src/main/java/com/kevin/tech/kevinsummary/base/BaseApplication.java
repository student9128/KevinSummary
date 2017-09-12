package com.kevin.tech.kevinsummary.base;

import android.app.Application;
import android.content.Context;

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
    }
}
