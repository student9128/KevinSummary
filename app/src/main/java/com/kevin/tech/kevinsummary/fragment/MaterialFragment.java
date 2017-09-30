package com.kevin.tech.kevinsummary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.activity.WebViewActivity;
import com.kevin.tech.kevinsummary.activity.material.BottomNavigationBarActivity;
import com.kevin.tech.kevinsummary.activity.material.CoordinatorLayoutActivity;
import com.kevin.tech.kevinsummary.activity.material.MaterialDesignActivity;
import com.kevin.tech.kevinsummary.adapter.RecyclerViewAdapter;
import com.kevin.tech.kevinsummary.base.BaseFragment;
import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.listener.OnItemClickListener;
import com.kevin.tech.kevinsummary.listener.OnShowEffectListener;
import com.kevin.tech.kevinsummary.view.widget.DividerItemDecoration;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/14.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class MaterialFragment extends BaseFragment implements OnItemClickListener, OnShowEffectListener {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private RecyclerViewAdapter mAdapter;

    public static MaterialFragment newInstance(String s) {
        MaterialFragment fragment = new MaterialFragment();
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
        text.setText("点击左侧进入文章内容，点击查看效果进入效果界面...。。。。。。。。。。。。。。。");

//        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
//        rotateAnimation.setDuration(1000);
//        rotateAnimation.setRepeatCount(100);
//        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        text.setAnimation(rotateAnimation);
//        rotateAnimation.startNow();
        String[] stringArray = getResources().getStringArray(R.array.material_item);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new RecyclerViewAdapter(mActivity, stringArray, true);
        rvRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnShowEffectListener(this);
    }

    @Override
    public void onRecyclerClick(int position) {
        String[] stringArray = getResources().getStringArray(R.array.site_material_design);
        Intent intent = new Intent(mActivity, WebViewActivity.class);
        intent.putExtra("url", stringArray[position]);
        startActivity(intent);

    }

    @Override
    public void showEffectActivity(int position) {
        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
                startActivity(new Intent(mActivity, MaterialDesignActivity.class));
                break;
            case 4:
                startActivity(new Intent(mActivity, BottomNavigationBarActivity.class));
                break;
            case 7:
                startActivity(new Intent(mActivity, CoordinatorLayoutActivity.class));
                break;
            default:
                break;

        }
    }
}

