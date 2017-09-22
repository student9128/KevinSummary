package com.kevin.tech.kevinsummary.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/22.
 * <h3>Description:</h3>
 * <div>
 * 自定义ListView，解决scrollview中嵌套ListView显示不正常的问题
 * <div/>
 */
public class KListView extends ListView {

    public KListView(Context context) {
        super(context);
    }

    public KListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}