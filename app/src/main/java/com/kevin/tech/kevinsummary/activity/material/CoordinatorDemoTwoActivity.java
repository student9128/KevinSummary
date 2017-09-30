package com.kevin.tech.kevinsummary.activity.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.adapter.CoordinatorDemoRecyclerViewAdapter;
import com.kevin.tech.kevinsummary.view.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/30.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class CoordinatorDemoTwoActivity extends AppCompatActivity {

    @BindView(R.id.iv_header_view)
    ImageView ivHeaderView;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.coorinator_layout)
    CoordinatorLayout coorinatorLayout;
    private ActionBar actionBar;
    private List<String> data = new ArrayList<>();
    private CoordinatorDemoRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_coordinator_demo_two);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
        collapsingToolbar.setTitle("Kevin");
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
        collapsingToolbar.setExpandedTitleGravity(Gravity.CENTER);
        for (int i = 1; i <= 30; i++) {
            data.add("CoordinatorLayout Demo item\t" + i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setDivider(R.drawable.bg_divider_recycler);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new CoordinatorDemoRecyclerViewAdapter(this, data, false);
        recyclerView.setAdapter(mAdapter);
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
}
