package com.kevin.tech.kevinsummary.activity.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.uitls.DisplayUtils;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/29.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class MaterialDesignActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, View.OnClickListener, TextWatcher {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.text_view_1)
    TextView textView1;
    @BindView(R.id.text_view_2)
    TextView textView2;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.text_input_user_name)
    TextInputLayout textInputUserName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.text_input_email)
    TextInputLayout textInputEmail;
    @BindView(R.id.text_input_et_password)
    TextInputEditText textInputEtPassword;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_material_design);
        ButterKnife.bind(this);
        setSupportActionBar(toolBar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
//        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        //DrawerLayout从左往右滑动的时候，很难触发，这里修改触发范围，使其更容易滑出
/**********修改DrawerLayout滑动响应范围*******开始******/
        Field mDragger = null;
        try {
            mDragger = drawerLayout.getClass().getDeclaredField(
                    "mLeftDragger"); //mRightDragger for right obviously
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mDragger.setAccessible(true);
        ViewDragHelper draggerObj = null;
        try {
            draggerObj = (ViewDragHelper) mDragger
                    .get(drawerLayout);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field mEdgeSize = null;
        try {
            mEdgeSize = draggerObj.getClass().getDeclaredField(
                    "mEdgeSize");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mEdgeSize.setAccessible(true);
        int edge = 0;
        try {
            edge = mEdgeSize.getInt(draggerObj);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            mEdgeSize.setInt(draggerObj, DisplayUtils.dip2px(this, 50)); //optimal value as for me, you may set any constant in dp
            //You can set it even to the value you want like mEdgeSize.setInt(draggerObj, 150); for 150dp
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//方法来源：
// https://stackoverflow.com/questions/17699869/how-to-show-the-drawerlayout-when-sliding-from-left-to-right-no-matter-where
/**********修改DrawerLayout滑动响应范围*******结束******/

        drawerToggle.syncState();
        drawerLayout.addDrawerListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView.setOnClickListener(this);
        textInputUserName.setCounterEnabled(true);
        textInputUserName.setCounterMaxLength(10);
        textInputEtPassword.addTextChangedListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                Snackbar.make(llContainer, "item1", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_item2:
                Snackbar.make(llContainer, "item2", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.action_item3:
                Snackbar.make(llContainer, "item3", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        final Snackbar snackbar = Snackbar.make(llContainer, "Click the Snackbar", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));//set the background color for snackbar
        setSnackbarActionTextAllCaps(snackbar, false);//set the action text whether all caps
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//      if (snackbar != null && snackbar.isShown()) {
//          snackbar.dismiss();
//      }

                drawerLayout.closeDrawers();
            }
        })
                .setText("打开")//set the message text
                .setActionTextColor(ContextCompat.getColor(this, R.color.orange_1))
                .show();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Snackbar snackbar1 = Snackbar.make(llContainer, "关闭", Snackbar.LENGTH_SHORT);
        setSnackbarMessageTextColor(snackbar1, ContextCompat.getColor(MaterialDesignActivity.this, R.color.teal_2));
        snackbar1.show();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }


    /**
     * set the message text color
     *
     * @param snackbar
     * @param color    the text color
     */
    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
        textView.setTextColor(color);
    }

    /**
     * set the action text whether all caps
     *
     * @param snackbar the sanckbar
     * @param allCaps  boolean allCaps,true or false
     */
    public static void setSnackbarActionTextAllCaps(Snackbar snackbar, boolean allCaps) {
        View view = snackbar.getView();
        TextView textView = (TextView) view.findViewById(R.id.snackbar_action);
        textView.setAllCaps(allCaps);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view:
                onBackPressed();
                break;
            case R.id.text_view_1:
                Snackbar snackbar1 = Snackbar.make(llContainer, "关闭", Snackbar.LENGTH_SHORT);
                setSnackbarMessageTextColor(snackbar1, ContextCompat.getColor(MaterialDesignActivity.this, R.color.teal_2));
                snackbar1.show();
                break;
            case R.id.text_view_2:
                final Snackbar snackbar = Snackbar.make(llContainer, "Click the Snackbar", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));//set the background color for snackbar
                setSnackbarActionTextAllCaps(snackbar, false);//set the action text whether all caps
                snackbar.setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//      if (snackbar != null && snackbar.isShown()) {
//          snackbar.dismiss();
//      }

                        Snackbar snackbar1 = Snackbar.make(llContainer, "点击了取消", Snackbar.LENGTH_SHORT);
                        setSnackbarMessageTextColor(snackbar1, ContextCompat.getColor(MaterialDesignActivity.this, R.color.colorAccent));
                        snackbar1.show();
                    }
                })
                        .setText("TextView2")//set the message text
                        .setActionTextColor(ContextCompat.getColor(this, R.color.orange_1))
                        .show();
                break;
            case R.id.btn_login:
                String password = textInputEtPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(password) && password.length() < 6) {
                    textInputPassword.setError("密码错误，不能少于6个字符");
                    textInputEtPassword.setError("密码错误，不能少于6个字符");
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textInputPassword.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
