package com.kevin.tech.kevinsummary.uitls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * SharedPreferences
 * <div/>
 */
public class SPUtil {
    public static final String TAG = "SPUtil";
    private static SharedPreferences mSP;

    public static String getStringSP(String key, Context context) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return mSP.getString(key, "");
    }

    public static void setSP(String key, Context context, String str) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        mSP.edit().putString(key, str).commit();
    }

    public static void setTokenSP(Context context, String str) {
        mSP = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        mSP.edit().putString("token", str).commit();
    }

    public static Boolean getBooleanSP(String key, Context context) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        boolean value = mSP.getBoolean(key, false);
        LogK.d(TAG, value + "");
        return value;

    }

    /**
     * 默认为true
     *
     * @param key
     * @param context
     * @return
     */
    public static Boolean getBooleanSPDefaultTrue(String key, Context context) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        boolean value = mSP.getBoolean(key, true);
        LogK.d("Kevin", value + "");
        return value;

    }

    public static void setSP(String key, Context context, boolean b) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        mSP.edit().putBoolean(key, b).commit();
    }

    public static void removeSP(String key, Context context) {
        mSP = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        mSP.edit().remove(key).commit();
    }


}