package com.kevin.tech.kevinsummary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.view.AnyTextView;
import com.umeng.message.PushAgent;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    @BindView(R.id.tv_title)
    public AnyTextView tvTitle;
    @BindView(R.id.iv_function)
    public ImageView ivFunction;
    @BindView(R.id.tool_bar)
    public Toolbar toolBar;
    public ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        initView();
        initData();
        initListener();
        /********友盟推送**********/
        PushAgent.getInstance(this).onAppStart();
    }

    public abstract int setLayoutResId();

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
//}
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}
