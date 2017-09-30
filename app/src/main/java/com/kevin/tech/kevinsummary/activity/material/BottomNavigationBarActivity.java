package com.kevin.tech.kevinsummary.activity.material;

import android.graphics.Color;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/29.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class BottomNavigationBarActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.bottom_navigation_bar1)
    BottomNavigationBar bottomNavigationBar1;
    @BindView(R.id.bottom_navigation_bar2)
    BottomNavigationBar bottomNavigationBar2;
    @BindView(R.id.bottom_navigation_bar3)
    BottomNavigationBar bottomNavigationBar3;
    @BindView(R.id.bottom_navigation_bar4)
    BottomNavigationBar bottomNavigationBar4;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_material_bottom_navigation_bar;
    }

    @Override
    public void initView() {
        actionBar.setTitle("BottomNavigationBar");
        TextBadgeItem numberBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("5")
                .setHideOnSelect(false)
//                .toggle()
                .setAnimationDuration(2000);
        ShapeBadgeItem shapeBadgeItem = new ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_HEART)
                .setShapeColorResource(R.color.colorAccent)
                .setGravity(Gravity.TOP | Gravity.END)
                .setHideOnSelect(false);
        bottomNavigationBar.clearAll();
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING_NO_TITLE);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home").setActiveColorResource(R.color.teal)).setInActiveColor(R.color.colorPrimaryDark_night)
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black, "Dashboard").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black, "Explore").setActiveColorResource(R.color.teal_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black, "Account").setActiveColorResource(R.color.orange_1))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar1.clearAll();
        bottomNavigationBar1.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar1.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar1
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home").setActiveColorResource(R.color.teal_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black, "Dashboard").setActiveColorResource(R.color.black_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black, "Explore").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black, "Account").setActiveColorResource(R.color.teal))
                .initialise();
        bottomNavigationBar2.clearAll();
        bottomNavigationBar2.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar2.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar2
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black, "Dashboard")).setActiveColor(R.color.teal_2)
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black, "Explore")).setActiveColor(R.color.teal)
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black, "Account")).setActiveColor(R.color.pink_1)
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar3.clearAll();
        bottomNavigationBar3.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar3.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar3
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home")).setActiveColor(R.color.colorAccent).setInActiveColor(R.color.orange_1)
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black, "Dashboard").setActiveColor(R.color.black_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black, "Explore").setActiveColor(R.color.teal_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black, "Account"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar4.clearAll();
        bottomNavigationBar4.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar4.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar4
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home").setBadgeItem(numberBadgeItem)).setBarBackgroundColor(R.color.green)
                .addItem(new BottomNavigationItem(R.drawable.ic_dashboard_black, "Dashboard").setActiveColor(R.color.white_1).setBadgeItem(shapeBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_explore_black, "Explore").setActiveColor(R.color.teal_2))
                .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black, "Account"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

}
