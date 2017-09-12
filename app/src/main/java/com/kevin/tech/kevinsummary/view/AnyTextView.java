package com.kevin.tech.kevinsummary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/8.
 * <h3>Description:</h3>
 * <div>
 * 自定义可以设置字体格式的TextView
 * <br/>参考：https://github.com/hanspeide/anytextview
 * <div/>
 */


public class AnyTextView extends TextView {
    public AnyTextView(Context context) {
        super(context);
    }

    public AnyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        try {
            setTypeface(attrs, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        try {
            setTypeface(attrs, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Typeface> typefaceMap = new HashMap<>();

    public static void setTypeface(AttributeSet attributeSet, TextView textView) throws Exception {
        if (textView.isInEditMode()) return;
        Context context = textView.getContext();
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AnyTextView);
        String typefaceName = typedArray.getString(R.styleable.AnyTextView_typeface);
        if (typefaceMap.containsKey(typefaceName)) {
            textView.setTypeface(typefaceMap.get(typefaceName));
        } else {
            Typeface typeface = null;
            try {
                typeface = Typeface.createFromAsset(textView.getContext().getAssets(), typefaceName);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Can't find typeface " + typefaceName);
            }

            typefaceMap.put(typefaceName, typeface);
            textView.setTypeface(typeface);
        }
        typedArray.recycle();


    }
}
