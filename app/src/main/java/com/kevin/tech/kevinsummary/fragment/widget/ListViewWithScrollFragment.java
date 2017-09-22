package com.kevin.tech.kevinsummary.fragment.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.adapter.ListViewAdapter;
import com.kevin.tech.kevinsummary.base.BaseFragment;
import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.view.widget.KListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/22.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class ListViewWithScrollFragment extends BaseFragment {
    public static ListViewWithScrollFragment newInstance(String s) {
        ListViewWithScrollFragment fragment = new ListViewWithScrollFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.klv_list_view)
    KListView lvListView;
    private List<String> data = new ArrayList<>();
    private ListViewAdapter listViewAdapter;

    @Override
    public int setLayoutResId() {
        return R.layout.fragment_widget_list_view_with_scroll_view;
    }

    @Override
    public void initView() {
        lvListView = mView.findViewById(R.id.klv_list_view);
        listViewAdapter = new ListViewAdapter(mActivity, data);
        lvListView.setAdapter(listViewAdapter);
    }

    @Override
    public void initData() {
        data.clear();
        for (int i = 1; i <= 20; i++) {
            data.add("ListView with ScrollView Test\t" + i);
        }
        listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        lvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("You click " + data.get(position));
            }
        });
    }

}
