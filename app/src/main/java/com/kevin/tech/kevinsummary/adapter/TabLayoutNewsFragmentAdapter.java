package com.kevin.tech.kevinsummary.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */

public class TabLayoutNewsFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;
    private List<String> mTabList;
    private int[] mTabImgs, mTabImgsFill;
    private boolean isSkin;

    private ImageView mTabIcon, mTabIconFill;
    private TextView mTabTitle;

    public TabLayoutNewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutNewsFragmentAdapter(FragmentManager fm, Context context, List<Fragment> fragments,
                                        List<String> tabList, boolean isSkin) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
        this.mTabList = tabList;
        this.isSkin = isSkin;

    }

    public void upDateSkin(boolean isSkin) {
        this.isSkin = isSkin;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_news_fragment_tab_view, null);
        mTabTitle = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabTitle.setText(mTabList.get(position));
        if (0 == position) {//the default color of item home is green
            if (isSkin) {

                mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary_night));
            } else {
                mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));

            }
        } else {
            mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black_2));
        }
        return view;
    }
}
