package com.kevin.tech.kevinsummary.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/22.
 * <h3>Description:</h3>
 * <div>
 * 自定义GridView，解决scrollview中嵌套GridView显示不正常的问题
 * <div/>
 */
public class KGridView extends GridView {
    public KGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KGridView(Context context) {
        super(context);
    }

    public KGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}