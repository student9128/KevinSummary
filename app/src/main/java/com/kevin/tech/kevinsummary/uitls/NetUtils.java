package com.kevin.tech.kevinsummary.uitls;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


/**
 * Created by Kevin on 2017/2/16.
 * Blog:http://blog.csdn.net/student9128
 * Description: the utils for net.
 */
public class NetUtils {

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            // 如果仅仅是用来判断网络连接
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 判断是否是手机网络
     *
     * @param context
     * @return true是，false不是
     */
    public static boolean isMobileConnectivity(Context context) {
        // ConnectivityManager---systemService---Context
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }


    /**
     * 判断是否是wifi网络 (判断是wifi还是手机网络,用户体验是否好不好在这里了，是wifi就可以建议下载或者在线播放。节省流量
     *
     * @param context
     * @return 是否是wifi网络
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络类型(2G/3G/4G/WIFI)
     *
     * @param context
     * @return 网络类型
     * <p>
     * <p>
     * 使用NetworkInfo的getType()方法可以判断是WiFi还是手机网络。手机网络的情况下，使用NetworkInfo的
     * getSubtype()方法去和TelephonyManager的网络类型常量值去比较，判断是何种具体网络。
     * TelephonyManager的网络类型常量值如下（API 17）：
     * <p>
     * 1、NETWORK_TYPE_1xRTT： 常量值：7 网络类型：1xRTT
     * <p>
     * 2、NETWORK_TYPE_CDMA ： 常量值：4 网络类型： CDMA （电信2g）
     * <p>
     * 3、NETWORK_TYPE_EDGE： 常量值：2 网络类型：EDGE（移动2g）
     * <p>
     * 4、NETWORK_TYPE_EHRPD： 常量值：14 网络类型：eHRPD
     * <p>
     * 5、NETWORK_TYPE_EVDO_0： 常量值：5 网络类型：EVDO 版本0.（电信3g）
     * <p>
     * 6、NETWORK_TYPE_EVDO_A： 常量值：6 网络类型：EVDO 版本A （电信3g）
     * <p>
     * 7、NETWORK_TYPE_EVDO_B： 常量值：12 网络类型：EVDO 版本B（电信3g）
     * <p>
     * 8、NETWORK_TYPE_GPRS： 常量值：1 网络类型：GPRS （联通2g）
     * <p>
     * 9、NETWORK_TYPE_HSDPA： 常量值：8 网络类型：HSDPA（联通3g）
     * <p>
     * 10、NETWORK_TYPE_HSPA： 常量值：10 网络类型：HSPA
     * <p>
     * 11、NETWORK_TYPE_HSPAP： 常量值：15 网络类型：HSPA+
     * <p>
     * 12、NETWORK_TYPE_HSUPA： 常量值：9 网络类型：HSUPA
     * <p>
     * 13、NETWORK_TYPE_IDEN： 常量值：11 网络类型：iDen
     * <p>
     * 14、NETWORK_TYPE_LTE： 常量值：13 网络类型：LTE(3g到4g的一个过渡，称为准4g)
     * <p>
     * 15、NETWORK_TYPE_UMTS： 常量值：3 网络类型：UMTS（联通3g）
     * <p>
     * 16、NETWORK_TYPE_UNKNOWN：常量值：0 网络类型：未知
     */
    public static String getNetworkTypeName(Context context) {
        String type = "";
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "null";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "WIFI";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4G";
            }
        }
        return type;
    }
}
