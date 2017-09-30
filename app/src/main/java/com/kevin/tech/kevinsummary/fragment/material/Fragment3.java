package com.kevin.tech.kevinsummary.fragment.material;

import android.os.Bundle;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseFragment;
import com.kevin.tech.kevinsummary.constants.Constants;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/29.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class Fragment3 extends BaseFragment {

    @BindView(R.id.text_view)
    TextView textView;

    public static Fragment3 newInstance(String s) {
        Fragment3 fragment = new Fragment3();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_item_material;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        String string = bundle.getString(Constants.ARGS);
        textView.setText(string);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

}
