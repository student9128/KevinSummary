package com.kevin.tech.kevinsummary.activity;

import android.widget.FrameLayout;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.fragment.SettingFragment;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/14.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class SettingActivity extends BaseActivity {

    //    @BindView(R.id.tv_title)
//    AnyTextView tvTitle;
//    @BindView(R.id.iv_function)
//    ImageView ivFunction;
//    @BindView(R.id.tool_bar)
//    Toolbar toolBar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        actionBar.setTitle("设置");
        getFragmentManager().beginTransaction()
                .add(R.id.fl_content, new SettingFragment())
                .commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
