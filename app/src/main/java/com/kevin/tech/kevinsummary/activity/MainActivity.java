package com.kevin.tech.kevinsummary.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.adapter.TabLayoutAdapter;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.constants.Constants;
import com.kevin.tech.kevinsummary.fragment.AndroidFragment;
import com.kevin.tech.kevinsummary.fragment.ComponentFragment;
import com.kevin.tech.kevinsummary.fragment.MaterialFragment;
import com.kevin.tech.kevinsummary.fragment.ThirdFragment;
import com.kevin.tech.kevinsummary.fragment.ToolFragment;
import com.kevin.tech.kevinsummary.uitls.ColorUtils;
import com.kevin.tech.kevinsummary.uitls.DateUtils;
import com.kevin.tech.kevinsummary.uitls.SPUtil;
import com.kevin.tech.kevinsummary.view.NoSmoothViewPager;
import com.kevin.tech.kevinsummary.view.RotateAnimation;
import com.kevin.tech.kevinsummary.view.RoundedImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.kevin.tech.kevinsummary.uitls.FileUtils.directory;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener, DrawerLayout.DrawerListener {

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
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.ns_view_pager)
    NoSmoothViewPager nsViewPager;
    private ActionBarDrawerToggle drawerToggle;
    private RoundedImageView linkGitHub;
    private RoundedImageView linkCSDN;
    private RoundedImageView linkJianShu;
    private RoundedImageView linkSegmentFault;
    private ImageView ivSign;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabList = new ArrayList<>();
    private TabLayoutAdapter mAdapter;

    //灰色以及相对应的RGB值
    private int mGrayColor;
    private int mGrayRed;
    private int mGrayGreen;
    private int mGrayBlue;

    private int mBlueColor;
    private int mBlueRed;
    private int mBlueGreen;
    private int mBlueBlue;

    private int mBlackColor;
    private int mBlackRed;
    private int mBlackGreen;
    private int mBlackBlue;

    private int mRedColor;
    private int mRedRed;
    private int mRedGreen;
    private int mRedBlue;
    private Boolean skin;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        actionBar.setTitle("KS");
        toolBar.setNavigationIcon(R.drawable.ic_menu);
        if (Build.VERSION.SDK_INT >= 21) {
            toolBar.setElevation(0);
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);

//        drawerToggle.syncState();//显示默认的三道杠图标
        drawerLayout.setDrawerListener(this);
//        drawerLayout.setScrimColor(Color.TRANSPARENT);//drawerLayout滑出是侧边的阴影
//        drawerLayout.addDrawerListener(this);
//        navView.setItemIconTintList(null);//可以让图标保持原有颜色
        hideNavigationViewScrollbar(navView);
        View headerView = navView.getHeaderView(0);
        linkGitHub = (RoundedImageView) headerView.findViewById(R.id.riv_git_hub);
        linkCSDN = (RoundedImageView) headerView.findViewById(R.id.riv_csdn);
        linkJianShu = (RoundedImageView) headerView.findViewById(R.id.riv_jian_shu);
        linkSegmentFault = (RoundedImageView) headerView.findViewById(R.id.riv_segment_fault);
        ivSign = (ImageView) headerView.findViewById(R.id.iv_sign);
        skin = SPUtil.getBooleanSP("skin", this);
//        reduceMarginsInTabs(tabLayout, DisplayUtils.dip2px(this, 50));
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, Constants.BAIDU_PUSH_API_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        skin = SPUtil.getBooleanSP("skin", this);
        if (skin) {//换肤了
            tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.black_2), ContextCompat.getColor(this, R.color.colorPrimary_night));
            tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_title_color_night));
            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark_night));
        } else {//未换肤
            tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.black_2), ContextCompat.getColor(this, R.color.colorPrimary));
            tabLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_title_color));
            drawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }

    @Override
    public void initData() {
        Menu navViewMenu = navView.getMenu();
        MenuItem item = navViewMenu.findItem(R.id.nav_calendar);
        SpannableString spanText = new SpannableString("今天是:" + DateUtils.getCurrentDate());
        spanText.setSpan(new ForegroundColorSpan(Color.RED), 4, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        item.setTitle(spanText);
        MenuItem item1 = navViewMenu.findItem(R.id.nav_last_time);
        String signDate = SPUtil.getStringSP(Constants.KEY_SIGN, this);
        if (!TextUtils.isEmpty(signDate)) {
            if (DateUtils.getCurrentDate().equals(signDate)) {
                ivSign.setImageResource(R.drawable.ic_signed);
            } else {
                ivSign.setImageResource(R.drawable.ic_sign);
            }
            item1.setTitle("上次签到:" + signDate);
        } else {
            ivSign.setImageResource(R.drawable.ic_sign);
            SpannableString spanText1 = new SpannableString("上次签到:还未签到");
            spanText1.setSpan(new ForegroundColorSpan(Color.RED), 5, spanText1.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            item1.setTitle(spanText1);
        }
        initTabList();
        initFragmentList();
        mAdapter = new TabLayoutAdapter(getSupportFragmentManager(),
                this, mFragments, mTabList, skin);
        nsViewPager.setAdapter(mAdapter);
        nsViewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(nsViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        initColor();
        buildImage();
    }

    @Override
    public void initListener() {
        ivSign.setOnClickListener(this);
        linkGitHub.setOnClickListener(this);
        linkCSDN.setOnClickListener(this);
        linkJianShu.setOnClickListener(this);
        linkSegmentFault.setOnClickListener(this);
        btn.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(this);
        nsViewPager.addOnPageChangeListener(this);
        navView.setNavigationItemSelectedListener(this);
        //让DrawerLayout从右边滑出
//        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
//                    drawerLayout.closeDrawer(Gravity.RIGHT);
//                } else {
//                    drawerLayout.openDrawer(Gravity.RIGHT);
//                }
//            }
//        });
    }

    private void initTabList() {
        mTabList.clear();
        mTabList.add("Components");
        mTabList.add("Widget");
        mTabList.add("MaterialDesign");
        mTabList.add("3rdParty");
        mTabList.add("Tools");
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(ComponentFragment.newInstance("Components"));
        mFragments.add(AndroidFragment.newInstance("Widget"));
        mFragments.add(MaterialFragment.newInstance("MaterialDesign"));
        mFragments.add(ThirdFragment.newInstance("3rdParty"));
        mFragments.add(ToolFragment.newInstance("Tools"));

    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.action_share:
                showToast("分享");
                showShare();
//                showNativeShare();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                break;
            case R.id.iv_sign:
//                showToast("Sign");
                String signDate = SPUtil.getStringSP(Constants.KEY_SIGN, this);
                if (!TextUtils.isEmpty(signDate)) {
                    if (DateUtils.getCurrentDate().equals(signDate)) {
                        showToast("今日已签");
                    } else {
                        RotateAnimation rotateAnimation = new RotateAnimation(ivSign,
                                BitmapFactory.decodeResource(getResources(), R.drawable.ic_signed));
                        ivSign.startAnimation(rotateAnimation);
                        SPUtil.setSP("sign", this, DateUtils.getCurrentDate());
                    }
                } else {
                    RotateAnimation rotateAnimation = new RotateAnimation(ivSign,
                            BitmapFactory.decodeResource(getResources(), R.drawable.ic_signed));
                    ivSign.startAnimation(rotateAnimation);
                    SPUtil.setSP("sign", this, DateUtils.getCurrentDate());
                }
                break;
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
        int selectedTabPosition = tabLayout.getSelectedTabPosition();
        View customView = tabLayout.getTabAt(selectedTabPosition).getCustomView();
        TextView viewById = (TextView) customView.findViewById(R.id.tv_tab_text);
        switch (item.getItemId()) {

            default:
                break;
        }
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
//            printLogd("Position:===============\t" + position);
            TabLayout.Tab tabAt = tabLayout.getTabAt(position);
            TabLayout.Tab tabAt1 = tabLayout.getTabAt(position + 1);

            View customView1 = tabAt1.getCustomView();
            TextView tabText1 = (TextView) customView1.findViewById(R.id.tv_tab_text);

            View customView = tabAt.getCustomView();
            TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
            if (SPUtil.getBooleanSP("skin", MainActivity.this)) {
                tabText.setTextColor(switchBlue2Black(positionOffset));
                tabText1.setTextColor(switchBlack2Blue(positionOffset));
            } else {
                tabText.setTextColor(switchRed2Black(positionOffset));
                tabText1.setTextColor(switchBlack2Red(positionOffset));
            }

        }
    }


    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setTabSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        if (SPUtil.getBooleanSP("skin", MainActivity.this)) {
            tabText.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            tabText.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
        TextPaint tp = tabText.getPaint();
        tp.setFakeBoldText(true);
//        tabText.setScaleX(1.1f);
        tabText.setScaleY(1.1f);

    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.gray_14));
        TextPaint tp = tabText.getPaint();
        tp.setFakeBoldText(false);

//        tabText.setScaleX(1.0f);
        tabText.setScaleY(1.0f);
    }


    private void initColor() {
        mRedColor = ColorUtils.getColor(this, R.color.white);
        mRedRed = ColorUtils.getColorRed(mRedColor);
        mRedGreen = ColorUtils.getColorGreen(mRedColor);
        mRedBlue = ColorUtils.getColorBlue(mRedColor);

        mBlueColor = ColorUtils.getColor(this, R.color.white);
        mBlueRed = ColorUtils.getColorRed(mBlueColor);
        mBlueGreen = ColorUtils.getColorGreen(mBlueColor);
        mBlueBlue = ColorUtils.getColorBlue(mBlueColor);

        mBlackColor = ColorUtils.getColor(this, R.color.gray_14);
        mBlackRed = Color.red(mBlackColor);
        mBlackGreen = Color.green(mBlackColor);
        mBlackBlue = Color.blue(mBlackColor);
    }

    /**
     * offset from 0 to 1
     *
     * @param positionOffset
     * @return
     */
    private int switchRed2Black(float positionOffset) {
        int red = ColorUtils.switchARGB(mRedRed, mBlackRed, positionOffset);
        int green = ColorUtils.switchARGB(mRedGreen, mBlackGreen, positionOffset);
        int blue = ColorUtils.switchARGB(mRedBlue, mBlackBlue, positionOffset);
        return ColorUtils.switchColor(red, green, blue);
    }

    private int switchBlack2Red(float positionOffset) {
        int red = ColorUtils.switchARGB(mBlackRed, mRedRed, positionOffset);
        int green = ColorUtils.switchARGB(mBlackGreen, mRedGreen, positionOffset);
        int blue = ColorUtils.switchARGB(mBlackBlue, mRedBlue, positionOffset);
        return ColorUtils.switchColor(red, green, blue);
    }

    private int switchBlue2Black(float positionOffset) {
        int red = ColorUtils.switchARGB(mBlueRed, mBlackRed, positionOffset);
        int green = ColorUtils.switchARGB(mBlueGreen, mBlackGreen, positionOffset);
        int blue = ColorUtils.switchARGB(mBlueBlue, mBlackBlue, positionOffset);
        return ColorUtils.switchColor(red, green, blue);
    }

    private int switchBlack2Blue(float positionOffset) {
        int red = ColorUtils.switchARGB(mBlackRed, mBlueRed, positionOffset);
        int green = ColorUtils.switchARGB(mBlackGreen, mBlueGreen, positionOffset);
        int blue = ColorUtils.switchARGB(mBlackBlue, mBlueBlue, positionOffset);
        return ColorUtils.switchColor(red, green, blue);
    }


    /**
     * offset from 0 to 1
     *
     * @param offset
     * @return
     */
    private int getGreen2Black(float offset) {
        int red = (int) (offset * (mBlackRed - mBlueRed) + mBlueRed);
        int green = (int) (offset * (mBlackGreen - mBlueGreen) + mBlueGreen);
        int blue = (int) (offset * (mBlackBlue - mBlueBlue) + mBlueBlue);
        return Color.argb(255, red, green, blue);
    }

    private int getBlack2Green(float offset) {
        int red = (int) (offset * (mBlueRed - mBlackRed) + mBlackRed);
        int green = (int) (offset * (mBlueGreen - mBlackGreen) + mBlackGreen);
        int blue = (int) (offset * (mBlueBlue - mBlackBlue) + mBlackBlue);
        return Color.argb(255, red, green, blue);
    }

    public static void reduceMarginsInTabs(TabLayout tabLayout, int marginOffset) {

        View tabStrip = tabLayout.getChildAt(0);
        if (tabStrip instanceof ViewGroup) {
            ViewGroup tabStripGroup = (ViewGroup) tabStrip;
            for (int i = 0; i < ((ViewGroup) tabStrip).getChildCount(); i++) {
                View tabView = tabStripGroup.getChildAt(i);
                if (tabView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    ((ViewGroup.MarginLayoutParams) tabView.getLayoutParams()).leftMargin = marginOffset;
                    ((ViewGroup.MarginLayoutParams) tabView.getLayoutParams()).rightMargin = marginOffset;
                }
            }

            tabLayout.requestLayout();
        }
    }

    private void showNativeShare() {
//        Intent textIntent = new Intent(Intent.ACTION_SEND);
//        textIntent.setType("text/plain");
//        textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
//        startActivity(Intent.createChooser(textIntent, "分享"));

//        Intent intent2 = new Intent(Intent.ACTION_SEND);
        Uri avatar = Uri.fromFile(new File(directory + "avatar.png"));
//        intent2.putExtra(Intent.EXTRA_STREAM, avatar);
//        intent2.setType("image/*");
//        startActivity(Intent.createChooser(intent2, "分享到"));
        Uri launcher = Uri.fromFile(new File(directory + "launcher.png"));

        ArrayList<Uri> uriList = new ArrayList<>();
        uriList.add(avatar);
        uriList.add(launcher);
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "分享到"));


    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(getString(R.string.csdn));
//        // text是分享文本，所有平台都需要这个字段
        oks.setText("An app for Android Learning");
        oks.setImageUrl(getString(R.string.icon_url));
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(getString(R.string.csdn));
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    /**
     * 在本地建立图片
     */
    private void buildImage() {
        writeImage(R.drawable.ic_avatar, "avatar");
        writeImage(R.mipmap.ic_launcher, "launcher");
    }

    private void writeImage(int resId, String name) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(directory + name + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
