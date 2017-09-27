package com.kevin.tech.kevinsummary.receiver.baidupush;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/*
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 * onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 * onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调
 * 返回值中的errorCode，解释如下：
 * 0 - Success
 * 10001 - Network Problem
 * 10101 - Integrate Check Error
 * 30600 - Internal Server Error
 * 30601 - Method Not Allowed
 * 30602 - Request Params Not Valid
 * 30603 - Authentication Failed
 * 30604 - Quota Use Up Payment Required
 * 30605 - Data Required Not Found
 * 30606 - Request Time Expires Timeout
 * 30607 - Channel Token Timeout
 * 30608 - Bind Relation Not Found
 * 30609 - Bind Number Too Many
 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 *
 */

public class BaiduPushMessageReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid, String channelId, String requestId, String s3) {
        Log.i("kevin","绑定");
        Log.i("kevin", errorCode + "---" + appid + "---" + channelId + "---" + requestId);

    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        Log.i("kevin","解绑");

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.i("kevin","onSetTags");
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.i("kevin","onDelTags");
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        Log.i("kevin","onListTags");
    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        Log.i("kevin","onMessage透传消息发送成功了~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.i("kevin","onNotificationClicked");

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        Log.i("kevin","onNotificationArrived");

    }
}
