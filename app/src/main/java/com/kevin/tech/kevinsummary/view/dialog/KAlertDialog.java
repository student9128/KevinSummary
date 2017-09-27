package com.kevin.tech.kevinsummary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;


/**
 * Created by <a href="http://blog.csdn.net/student9128">Jingpeng</a> on 2017/6/13.
 * <p>Blog:http://blog.csdn.net/student9128.
 * <p>
 * <h3>Description:</h3>
 * <p>
 * <p>
 */


public class KAlertDialog extends Dialog implements DialogInterface {
    private String mTitle;
    private String mMessage;
    private String positiveText;
    private String negativeText;
    private boolean isNegativeBtnShow = true;
    private TextView mTvTitle, mTvMessage, mBtnPositive, mBtnNegative;
    private FrameLayout mFlContent;
    private int mLayoutResId;
    private View mView;

    public KAlertDialog(@NonNull Context context) {
        super(context);
    }

    public KAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public KAlertDialog(int layoutResId, @NonNull Context ctx) {
        super(ctx);
        this.mLayoutResId = layoutResId;

    }
//    private MyDialog(Context context, Builder builder) {
//        this(context, 0);
//        this.mTitle = builder.mTitle;
//        this.mMessage = builder.mMessage;
//        this.positiveText = builder.mPositiveText;
//    }

    protected KAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (TextUtils.isEmpty(String.valueOf(mLayoutResId))) {
//        setContentView(R.layout.dialog_my);
//        } else {
//            setContentView(mLayoutResId);
//        }
        setContentView(mLayoutResId);
//        LinearLayout viewById = (LinearLayout) mWindow.findViewById(R.id.parentPanel);
//        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvMessage = (TextView) findViewById(R.id.tv_content);
        mBtnPositive = (TextView) findViewById(R.id.btn_positive);
        mBtnNegative = (TextView) findViewById(R.id.btn_negative);

    }

    @Override
    public void show() {
        super.show();
        show(this);
    }

    private void show(final KAlertDialog dialog) {
        if (mView != null) {
            mFlContent.addView(dialog.mView);
        }
        if (dialog.mTitle != null) {
            dialog.mTvTitle.setText(dialog.mTitle);
        }
        if (dialog.mTvMessage != null) {
            dialog.mTvMessage.setText(dialog.mMessage);
        }
        if (!isNegativeBtnShow) {
            dialog.mBtnNegative.setVisibility(View.GONE);
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) dialog.mBtnPositive
//                    .getLayoutParams();
//            layoutParams.setMargins(150, layoutParams.topMargin, 150, layoutParams.bottomMargin);
//            dialog.mBtnPositive.setLayoutParams(layoutParams);
        } else {
            dialog.mBtnNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                    buttonClickListener.setNegative();
                }
            });
            if (negativeText != null) {
                dialog.mBtnNegative.setText(negativeText);
            }
            dialog.mBtnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                    buttonClickListener.setPositive();
                }
            });
            if (positiveText != null) {
                dialog.mBtnPositive.setText(positiveText);
            }
        }
    }

    public static class Builder {
        public OnClickListener mPositiveButtonListener;
        private KAlertDialog myDialog;

        public Builder(@NonNull Context context) {
            myDialog = new KAlertDialog(context);
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            myDialog = new KAlertDialog(context, themeResId);
        }

        public Builder(int layoutResId, @NonNull Context context) {
            myDialog = new KAlertDialog(layoutResId, context);
        }


        public Builder mTitle(String title) {
            myDialog.mTitle = title;
            return this;
        }

        public Builder mMessage(String message) {
            myDialog.mMessage = message;
            return this;
        }

        public Builder positiveText(String positivieText) {
            myDialog.positiveText = positivieText;
            return this;
        }

        public Builder negativeText(String negativeText) {
            myDialog.negativeText = negativeText;
            return this;
        }

        /**
         * 设置该对话框能否被Cancel掉，默认可以
         *
         * @param cancelable
         */
        public Builder setCancelable(boolean cancelable) {
            myDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * 设置对话框被cancel对应的回调接口，cancel()方法被调用时才会回调该接口
         *
         * @param onCancelListener
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            myDialog.setOnCancelListener(onCancelListener);
            return this;
        }

        /**
         * 设置对话框消失对应的回调接口，一切对话框消失都会回调该接口
         *
         * @param onDismissListener
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            myDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        /**
         * 设置确认按钮的回调
         *
         * @param btnText,onClickListener
         */
        public Builder setPositiveButton(String btnText, ButtonClickListener listener) {
            myDialog.positiveText = btnText;
            buttonClickListener = listener;
            return this;
        }

        public Builder setCustomView(View view) {
            myDialog.mView = view;
            return this;
        }

        public KAlertDialog create() {
            return myDialog;
        }

    }

    public static ButtonClickListener buttonClickListener;

    interface ButtonClickListener {
        void setPositive();

        void setNegative();
    }

    interface PostiviBtnClickListner {
        void setPositive();
    }

    interface NegativeBtnClickListner {
        void setNegative();
    }
}
