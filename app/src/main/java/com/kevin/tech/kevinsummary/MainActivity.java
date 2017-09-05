package com.kevin.tech.kevinsummary;

import android.content.Intent;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.uitls.SPUtil;
import com.kevin.tech.kevinsummary.uitls.ToastUtils;
import com.kevin.tech.kevinsummary.view.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

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
    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.btn)
    Button btn;
    private ActionBarDrawerToggle drawerToggle;
    private RoundedImageView linkGitHub;
    private RoundedImageView linkCSDN;
    private RoundedImageView linkJianShu;
    private RoundedImageView linkSegmentFault;

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
        Boolean skin = SPUtil.getBooleanSP("skin", this);
        if (skin) {//换肤了
            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark_night));
        } else {//未换肤
            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
//        navView.setItemIconTintList(null);//可以让图标保持原有颜色
        navView.setNavigationItemSelectedListener(this);
        hideNavigationViewScrollbar(navView);
        View headerView = navView.getHeaderView(0);
        linkGitHub = (RoundedImageView) headerView.findViewById(R.id.riv_git_hub);
        linkCSDN = (RoundedImageView) headerView.findViewById(R.id.riv_csdn);
        linkJianShu = (RoundedImageView) headerView.findViewById(R.id.riv_jian_shu);
        linkSegmentFault = (RoundedImageView) headerView.findViewById(R.id.riv_segment_fault);
        linkGitHub.setOnClickListener(this);
        linkCSDN.setOnClickListener(this);
        linkJianShu.setOnClickListener(this);
        linkSegmentFault.setOnClickListener(this);
        btn.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.riv_git_hub:
                drawerLayout.closeDrawers();
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", getString(R.string.github));
                startActivity(intent);
                break;
            case R.id.riv_csdn:
                drawerLayout.closeDrawers();
                Intent intentCSDN = new Intent(MainActivity.this, WebViewActivity.class);
                intentCSDN.putExtra("url", getString(R.string.csdn));
                startActivity(intentCSDN);
                break;
            case R.id.riv_jian_shu:
                drawerLayout.closeDrawers();
                Intent intentJ = new Intent(MainActivity.this, WebViewActivity.class);
                intentJ.putExtra("url", getString(R.string.jianshu));
                startActivity(intentJ);
                break;
            case R.id.riv_segment_fault:
                drawerLayout.closeDrawers();
                Intent intentSF = new Intent(MainActivity.this, WebViewActivity.class);
                intentSF.putExtra("url", getString(R.string.segment_fault));
                startActivity(intentSF);
                break;
            case R.id.btn:
                SpringForce springForce = new SpringForce(0)
                        .setDampingRatio(0.5f)//setDampingRatio(float dampingRatio)方法设置弹性阻尼，dampingRatio越大，摆动次数越少，当到1的时候完全不摆动
                        .setStiffness(SpringForce.STIFFNESS_VERY_LOW);//setStiffness(float stiffness)方法设置弹性的生硬度，stiffness值越小，弹簧越容易摆动，摆动的时间越长，反之摆动时间越短
                SpringAnimation anim = new SpringAnimation(imageView, SpringAnimation.TRANSLATION_Y).setSpring(springForce);
                anim.setStartValue(-100);
                anim.start();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_skin:
                SPUtil.setSP("skin", MainActivity.this, true);
                drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark_night));
                SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                ToastUtils.showKevinToast(MainActivity.this, "Skin");
                break;
            case R.id.nav_send:
                SPUtil.setSP("skin", MainActivity.this, false);
                drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                SkinCompatManager.getInstance().restoreDefaultTheme();
                break;
            default:
                break;
        }
        return true;
    }
}
