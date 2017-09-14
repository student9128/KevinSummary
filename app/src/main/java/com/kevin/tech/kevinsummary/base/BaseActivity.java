package com.kevin.tech.kevinsummary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * <br/>Base class for all the activities in the app except AppBaseActivity.
 * <p>There are some abstract methods that sub activities must override.This class
 * extends {@link AppBaseActivity} which extends AppCompatActivity.</p>
 * </div>
 */

public abstract class BaseActivity extends AppBaseActivity {
//    /**
//     * Tag,can be used for log or toast.
//     */
//    public String TAG = getClass().getSimpleName();

//    private DayNightHelper mDayNightHelper;
//    public LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDayNightHelper = new DayNightHelper(this);
//        initTheme();
        initView();
        initData();
        initListener();
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();
    //===============Some Methods=================//

//    /**
//     * init Loading Dialog.
//     */
//    public void initLoadingDialog() {
//        mLoadingDialog = new LoadingDialog();
//    }
//
//    /**
//     * show Loading Dialog.
//     */
//    public void showLoadingDialog() {
//        mLoadingDialog.show(getSupportFragmentManager(), TAG);
//    }
//
//    /**
//     * dismiss Loading Dialog.
//     */
//    public void dismissLoadingDialog() {
//        if (mLoadingDialog.isAdded()) {
//            mLoadingDialog.dismiss();
//        }
//    }

    /**
     * 切换日夜间模式后进行刷新界面，写到initView()方法的最后
     */
//    public abstract void refreshUI();
    private void initTheme() {
//        if (mDayNightHelper.isDay()) {
//            setTheme(R.style.DayTheme);
//        } else {
//            setTheme(R.style.NightTheme);
//        }
    }

    /**
     * 刷新 StatusBar
     */
    public void refreshStatusBar() {
//        if (Build.VERSION.SDK_INT >= 21) {
//            TypedValue typedValue = new TypedValue();
//            Resources.Theme theme = getTheme();
//            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
//            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
//        }
    }

//    /**
//     * can be used for setting text color or background
//     *
//     * @param color
//     * @return
//     */
//    public int getMyColor(int color) {
//        return ContextCompat.getColor(this, color);
//    }
//
//    /**
//     * store value in SharePreferences.
//     *
//     * @param key key
//     * @param str value
//     */
//    public void setSp(String key, String str) {
//        SPUtil.setSP(key, this, str);
//    }
//
//    public void setSp(String key, boolean b) {
//        SPUtil.setSP(key, this, b);
//    }
//
//    /**
//     * get SharePreferences value.
//     *
//     * @param key key
//     * @return value
//     */
//    public String getStringSp(String key) {
//        return SPUtil.getStringSP(key, this);
//    }
//
//    public Boolean getBooleanSp(String key) {
//        return SPUtil.getBooleanSP(key,this);
//    }
//    //===============Tool=================//
//
//    private void setStatusBarColor(int color) {
//        StatusBarUtil.setColor(this, color);
//    }
//
//    public void showToast(String message) {
//        ToastUtils.showToast(this, message);
//    }
//
//    public void showLongToast(String message) {
//        ToastUtils.showLongToast(this, message);
//    }
//
//    public void printLoge(String str) {
//        LogK.e(TAG, str);
//    }
//
//    public void printLogd(String str) {
//        LogK.d(TAG, str);
//    }
//
//    public void printLogi(String str) {
//        LogK.i(TAG, str);
//    }
//
//    public void printLogv(String str) {
//        LogK.v(TAG, str);
//    }
//
//    public void printLogw(String str) {
//        LogK.w(TAG, str);
//    }
}
