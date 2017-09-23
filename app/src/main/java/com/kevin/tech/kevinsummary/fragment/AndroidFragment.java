package com.kevin.tech.kevinsummary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.listener.OnItemClickListener;
import com.kevin.tech.kevinsummary.activity.widget.ListViewActivity;
import com.kevin.tech.kevinsummary.activity.widget.TextViewActivity;
import com.kevin.tech.kevinsummary.adapter.RecyclerViewAdapter;
import com.kevin.tech.kevinsummary.base.BaseFragment;
import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.view.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class AndroidFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private String[] stringArray;

    public static AndroidFragment newInstance(String s) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_android;
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        String string = bundle.getString(Constants.ARGS);
        text.setText(string);

//        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
//        rotateAnimation.setDuration(1000);
//        rotateAnimation.setRepeatCount(100);
//        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        text.setAnimation(rotateAnimation);
//        rotateAnimation.startNow();
        stringArray = getResources().getStringArray(R.array.widget_item);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new RecyclerViewAdapter(mActivity, stringArray, false);
        rvRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onRecyclerClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(mActivity, TextViewActivity.class));
                break;
            case 1:
                startActivity(new Intent(mActivity, ListViewActivity.class));
                break;
            default:
                break;
        }
    }

}
