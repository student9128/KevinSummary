package com.kevin.tech.kevinsummary.activity.widget;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.adapter.RecyclerViewDemoAdapter;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.view.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/11/2.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class RecyclerViewActivity extends BaseActivity implements RecyclerViewDemoAdapter.OnRecyclerItemClickListner {
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private RecyclerViewDemoAdapter mAdapter;
    private List<String> data = new ArrayList<>();

    @Override
    public int setLayoutResId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView() {
        actionBar.setTitle("RecyclerView");
        for (int i = 0; i < 20; i++) {
            data.add("RecyclerView Test" + i);
        }
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new RecyclerViewDemoAdapter(this, data);
        rvRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
mAdapter.setOnRecyclerItemClicker(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRecyclerItemClick(int position) {
        showToast("You click " + data.get(position));
    }
}
