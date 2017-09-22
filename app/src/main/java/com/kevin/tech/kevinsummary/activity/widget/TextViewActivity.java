package com.kevin.tech.kevinsummary.activity.widget;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;
import com.kevin.tech.kevinsummary.view.widget.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/14.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class TextViewActivity extends BaseActivity {
    @BindView(R.id.etv_expandable_text)
    ExpandableTextView etvExpandableText;
    @BindView(R.id.expand_text_view)
    com.ms.square.android.expandabletextview.ExpandableTextView expandTextView;
    @BindView(R.id.text_view_1)
    TextView textView1;
    @BindView(R.id.text_view_2)
    TextView textView2;
    @BindView(R.id.text_view_3)
    TextView textView3;
    @BindView(R.id.text_view_4)
    TextView textView4;
    @BindView(R.id.text_view_5)
    TextView textView5;
    @BindView(R.id.text_view_6)
    TextView textView6;
    @BindView(R.id.text_view_7)
    TextView textView7;
    @BindView(R.id.text_view_8)
    TextView textView8;
    @BindView(R.id.text_view_9)
    TextView textView9;
    @BindView(R.id.text_view_10)
    TextView textView10;
    @BindView(R.id.text_view_11)
    TextView textView11;
    @BindView(R.id.text_view_12)
    TextView textView12;
    @BindView(R.id.text_view_13)
    TextView textView13;
    @BindView(R.id.text_view_14)
    TextView textView14;
    @BindView(R.id.text_view_15)
    TextView textView15;
    @BindView(R.id.text_view_16)
    TextView textView16;
    @BindView(R.id.text_view_17)
    TextView textView17;
    @BindView(R.id.text_view_18)
    TextView textView18;
    @BindView(R.id.text_view_19)
    TextView textView19;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_widget_text_view;
    }

    @Override
    public void initView() {
        actionBar.setTitle("TextView");
        etvExpandableText.setText(getString(R.string.test_string));
        expandTextView.setText(getString(R.string.test_string));
    }

    @Override
    public void initData() {
        SpannableString spannableString = new SpannableString("Hello==========");
        MaskFilterSpan maskFilterSpan1 = new MaskFilterSpan(new BlurMaskFilter(2, BlurMaskFilter.Blur.OUTER));
        spannableString.setSpan(maskFilterSpan1, 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView1.setText(spannableString);
        SpannableString spanText = new SpannableString("Span-Test by Kevin.");
//        spanText.setSpan(new BackgroundColorSpan(Color.GREEN), 0, 1,
//                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        textView1.setText(spanText);
        spanText = new SpannableString("Span-Test by Kevin.");
        spanText.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView2.setText(spanText);
        spanText = new SpannableString("Span-Test by Kevin.");
        spanText.setSpan(new URLSpan("http://blog.csdn.net/student9128"), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView3.setText(spanText);
        //让URLSpan可以点击
        textView3.setMovementMethod(new LinkMovementMethod());

        spanText = new SpannableString("Span-Test by Kevin.");
        int length = spanText.length();
//模糊(BlurMaskFilter)
        MaskFilterSpan maskFilterSpan = new MaskFilterSpan(new BlurMaskFilter(3, BlurMaskFilter.Blur.OUTER));
        spanText.setSpan(maskFilterSpan, 0, length - 10, Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);

        spanText = new SpannableString("Span-Test by Kevin.");
//浮雕(EmbossMaskFilter)
        maskFilterSpan = new MaskFilterSpan(new EmbossMaskFilter(new float[]{1, 1, 3}, 1.5f, 8, 3));
        spanText.setSpan(maskFilterSpan, length - 10, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView4.setText(spanText);

        spanText = new SpannableString("Span-Test by Kevin.");
        spanText.setSpan(new StrikethroughSpan(), 0, 3, Spannable.
                SPAN_INCLUSIVE_EXCLUSIVE);
        textView5.setText(spanText);
        spanText = new SpannableString("Span-Test by Kevin.");
        spanText.setSpan(new StrikethroughSpan(), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView6.setText(spanText);
        spanText = new SpannableString("UnderlineSpan");
        spanText.setSpan(new UnderlineSpan(), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView7.setText(spanText);

        spanText = new SpannableString("AbsoluteSizeSpan");
        spanText.setSpan(new AbsoluteSizeSpan(20, true), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView8.setText(spanText);


        DynamicDrawableSpan drawableSpan =
                new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BASELINE) {
                    @Override
                    public Drawable getDrawable() {
                        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
                        d.setBounds(0, 0, 50, 50);
                        return d;
                    }
                };
        DynamicDrawableSpan drawableSpan2 = new DynamicDrawableSpan(
                DynamicDrawableSpan.ALIGN_BOTTOM) {
            @Override
            public Drawable getDrawable() {
                Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
                d.setBounds(0, 0, 50, 50);
                return d;
            }
        };
        spanText.setSpan(drawableSpan, 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(drawableSpan2, 7, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView9.setText(spanText);

        spanText = new SpannableString("ImageSpan");
        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0, 0, 50, 50);
        spanText.setSpan(new ImageSpan(d), 3, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView10.setText(spanText);

        spanText = new SpannableString("RelativeSizeSpan");
//参数proportion:比例大小
        spanText.setSpan(new RelativeSizeSpan(2.5f), 3, 4,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView11.setText(spanText);

        spanText = new SpannableString("ScaleXSpan -- Span-Test by Kevin.");
//参数proportion:比例大小
        spanText.setSpan(new ScaleXSpan(3.8f), 3, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        spanText = new SpannableString("SubscriptSpan -- 下标（数学公式会用到）");
        spanText.setSpan(new SubscriptSpan(), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView13.setText(spanText);

        spanText = new SpannableString("SuperscriptSpan -- 上标（数学公式会用到）");
        spanText.setSpan(new SuperscriptSpan(), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView14.setText(spanText);

        spanText = new SpannableString("TextAppearanceSpan -- 文本外貌（包括字体、大小、样式和颜色）");
//若需自定义TextAppearance，可以在系统样式上进行修改
        spanText.setSpan(new TextAppearanceSpan(this, android.R.style.TextAppearance_Medium),
                6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView15.setText(spanText);

        spanText = new SpannableString("TypefaceSpan -- 文本字体");
//若需使用自定义字体，可能要重写类TypefaceSpan
        spanText.setSpan(new TypefaceSpan("cursive"), 3, 10,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView16.setText(spanText);

        SpannableString spannable17 = new SpannableString("Hello17---What-----");
        MaskFilterSpan maskFilterSpan2 = new MaskFilterSpan(new BlurMaskFilter(1, BlurMaskFilter.Blur.INNER));
        spannable17.setSpan(maskFilterSpan2, 0, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView17.setText(spannable17);

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
