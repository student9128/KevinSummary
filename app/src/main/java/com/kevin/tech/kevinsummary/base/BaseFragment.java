package com.kevin.tech.kevinsummary.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.tech.kevinsummary.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public abstract class BaseFragment extends AppBaseFragment {

    protected View mView;
    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setLayoutResId(), container, false);
        ButterKnife.bind(this, mView);
        unbinder = ButterKnife.bind(this, mView);
        initView();
        initData();
        initListener();
        printLogi("onCreateVew");
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        printLogi("onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
//        refreshUI();
        printLogi("onStart");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLogi("onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        printLogi("onActivityCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * set Layout Resource Id
     *
     * @return
     */
    public abstract int setLayoutResId();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    /**
     * refresh user interface.
     */
//    public abstract void refreshUI();

    //===============Some Methods=================//

    /**
     * 刷新 StatusBar
     */
    public void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = mActivity.getTheme();
            theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            mActivity.getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

}
