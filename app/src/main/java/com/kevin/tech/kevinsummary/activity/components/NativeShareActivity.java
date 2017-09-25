package com.kevin.tech.kevinsummary.activity.components;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.uitls.FileUtils;

import java.io.File;
import java.util.ArrayList;

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


}
