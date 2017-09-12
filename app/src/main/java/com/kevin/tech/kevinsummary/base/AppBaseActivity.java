package com.kevin.tech.kevinsummary.base;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.kevin.tech.kevinsummary.uitls.LogK;
import com.kevin.tech.kevinsummary.uitls.NetUtils;
import com.kevin.tech.kevinsummary.uitls.SPUtil;
import com.kevin.tech.kevinsummary.uitls.StatusBarUtil;
import com.kevin.tech.kevinsummary.uitls.ToastUtils;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * Base class for all the activities in the app extends {@link android.support.v7.app.AppCompatActivity}.
 * <br/>There are some global methods in this class which maybe used in all the sub activities.
 * <div/>
 */


public class AppBaseActivity extends AppCompatActivity {
    /**
     * Tag,can be used for log or toast.
     */
    public String TAG = getClass().getSimpleName();

    //===============Methods=================//

    /**
     * check network is available.
     *
     * @return true or false.
     */
    public boolean networkAvailable() {
        return NetUtils.isNetworkAvailable(BaseApplication.getContext());
    }

    /**
     * get the text from string
     *
     * @param ResId
     * @return
     */
    public String getMyString(int ResId) {
        return getResources().getString(ResId);
    }

    /**
     * can be used for setting text color or background
     *
     * @param color
     * @return
     */
    public int getMyColor(int color) {
        return ContextCompat.getColor(this, color);
    }

    /**
     * store value in SharePreferences.
     *
     * @param key key
     * @param str value
     */
    public void setSp(String key, String str) {
        SPUtil.setSP(key, this, str);
    }

    public void setSp(String key, boolean b) {
        SPUtil.setSP(key, this, b);
    }

    /**
     * get SharePreferences value.
     *
     * @param key key
     * @return value
     */
    public String getStringSp(String key) {
        return SPUtil.getStringSP(key, this);
    }

    public Boolean getBooleanSp(String key) {
        return SPUtil.getBooleanSP(key, this);
    }
    //===============Tool=================//

    public void setStatusBarColor(int color) {
        StatusBarUtil.setColor(this, color);
    }

    public void showToast(String message) {
        ToastUtils.showKevinToast(this, message);
    }

    public void showLongToast(String message) {
        ToastUtils.showKevinLongToast(this, message);
    }

    public void printLoge(String str) {
        LogK.e(TAG, str);
    }

    public void printLogd(String str) {
        LogK.d(TAG, str);
    }

    public void printLogi(String str) {
        LogK.i(TAG, str);
    }

    public void printLogv(String str) {
        LogK.v(TAG, str);
    }

    public void printLogw(String str) {
        LogK.w(TAG, str);
    }
}
