package com.kevin.tech.kevinsummary;

import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_function)
    ImageView ivFunction;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        toolBar.setNavigationIcon(R.drawable.ic_menu);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
//        drawerToggle.syncState();//显示默认的三道杠图标
//        drawerLayout.setDrawerListener(this);
        drawerLayout.addDrawerListener(this);
        drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.pink_1));
        navView.setItemIconTintList(null);//可以让图标保持原有颜色
        hideNavigationViewScrollbar(navView);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        ToastUtils.showKevinToast(this, "Open");

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        ToastUtils.showKevinToast(this, "Closed");

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /**
     * 隐藏NavigationView中的ScrollBar
     * <p>
     * 使用android:scrollbars="none"不生效。
     * <br/>
     * 这个滚动条不在NavigationView中，而是在他的child—NavigationMenuView中
     * 所以解决办法就是对NavigationView调用下面这个方法
     * </p>
     *
     * @param navigationView
     */
    private void hideNavigationViewScrollbar(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

}
