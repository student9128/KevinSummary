package com.kevin.tech.kevinsummary.activity.components;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Button;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.uitls.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/25.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class NativeShareActivity extends BaseActivity {
    @BindView(R.id.btn_share_text)
    Button btnShareText;
    @BindView(R.id.btn_share_image)
    Button btnShareImage;
    @BindView(R.id.btn_share_multi)
    Button btnShareMulti;
    @BindView(R.id.btn_share_custom)
    Button btnShareCustom;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_component_share;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn_share_text)
    public void shareText() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.introduction_app));
        startActivity(Intent.createChooser(textIntent, "分享到"));
    }

    @OnClick(R.id.btn_share_image)
    public void shareImage() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri avatar = Uri.fromFile(new File(FileUtils.directory + "avatar.png"));
        intent.putExtra(Intent.EXTRA_STREAM, avatar);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "分享到"));
    }

    @OnClick(R.id.btn_share_multi)
    public void shareMulti() {
        Uri avatar = Uri.fromFile(new File(FileUtils.directory + "avatar.png"));
        Uri launcher = Uri.fromFile(new File(FileUtils.directory + "launcher.png"));
        ArrayList<Uri> uriList = new ArrayList<>();
        uriList.add(avatar);
        uriList.add(launcher);
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "分享到"));

    }

    @OnClick(R.id.btn_share_custom)
    public void shareCustom() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(
                intent, 0);
        if (!resInfo.isEmpty()) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo info : resInfo) {
                Intent targeted = new Intent(Intent.ACTION_SEND);
                targeted.setType("text/plain");
                ActivityInfo activityInfo = info.activityInfo;

                PackageManager packageManager = getPackageManager();
                try {
                    String packageName = activityInfo.packageName;
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
//                    int labelRes = packageInfo.applicationInfo.labelRes;
                    String string = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                    printLogd("----------" + packageName + "==" + string + "=========\t");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                //在这里可以添加相应的平台，用 || 连接
//                if (activityInfo.packageName.contains("com.tencent.mm")) {
//                    targeted.putExtra(Intent.EXTRA_TEXT, "分享内容");
//                    targeted.setPackage(activityInfo.packageName);
//                    targetedShareIntents.add(targeted);
//                }
            }
//            Intent chooserIntent = Intent.createChooser(
//                    targetedShareIntents.remove(0), "Select app to share");
//            if (chooserIntent == null) {
//                return;
//            }
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
//                    targetedShareIntents.toArray(new Parcelable[]{}));
//            try {
//                startActivity(chooserIntent);
//            } catch (android.content.ActivityNotFoundException ex) {
//                showToast("Can't find sharecomponent to share");
//            }
        }
    }
}
