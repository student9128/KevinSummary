package com.kevin.tech.kevinsummary.activity.widget;

import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.fragment.widget.ListViewFragment;
import com.kevin.tech.kevinsummary.fragment.widget.ListViewWithScrollFragment;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/22.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class ListViewActivity extends BaseActivity {
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_widget_list_view;
    }

    @Override
    public void initView() {
        actionBar.setTitle("ListView");
        getSupportFragmentManager().beginTransaction().
                add(R.id.fl_content, ListViewFragment.newInstance("ListView"))
                .commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.action_no_scroll_view:
                beginTransaction.replace(R.id.fl_content, ListViewFragment.newInstance("ListView"));
                break;
            case R.id.action_with_scroll_view:
                beginTransaction.replace(R.id.fl_content, ListViewWithScrollFragment.newInstance("ListViewWithScrollView"));
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        beginTransaction.commit();
        return true;
    }
}
