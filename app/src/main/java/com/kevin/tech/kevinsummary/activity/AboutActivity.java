package com.kevin.tech.kevinsummary.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.adapter.AboutAdapter;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.view.AnyTextView;
import com.kevin.tech.kevinsummary.view.widget.DividerItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/14.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class AboutActivity extends BaseActivity implements AboutAdapter.OnItemClickListener {
    @BindView(R.id.tv_title)
    AnyTextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.rv_recycler_view)
    RecyclerView rvRecyclerView;
    private AboutAdapter mAdapter;
    private String[] stringText;
    private String[] stringTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("关于");
//        toolBar.setNavigationIcon(R.drawable.ic_close_activity);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        stringText = getResources().getStringArray(R.array.about_text);
        stringTitle = getResources().getStringArray(R.array.about_title);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        rvRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new AboutAdapter(this, stringTitle, stringText);
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

    @Override
    public void onRecyclerClick(int position) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", stringText[position]);
        startActivity(intent);
    }
}
