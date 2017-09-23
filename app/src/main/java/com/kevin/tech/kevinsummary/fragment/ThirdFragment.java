package com.kevin.tech.kevinsummary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.activity.OnItemClickListener;
import com.kevin.tech.kevinsummary.activity.WebViewActivity;
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


public class ThirdFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private RecyclerViewAdapter mAdapter;

    public static ThirdFragment newInstance(String s) {
        ThirdFragment fragment = new ThirdFragment();
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
        TextView textView = (TextView) mView.findViewById(R.id.text);
        Bundle bundle = getArguments();

        String string = bundle.getString(Constants.ARGS);
        textView.setText(string);
        String[] stringArray = getResources().getStringArray(R.array.third_item);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new RecyclerViewAdapter(mActivity, stringArray);
        rvRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(this);

    }

    @Override
    public void onRecyclerClick(int position) {
        String[] stringArray = getResources().getStringArray(R.array.site_material_design);
        Intent intent = new Intent(mActivity, WebViewActivity.class);
        intent.putExtra("url", stringArray[position]);
        startActivity(intent);
    }
}
