package com.kevin.tech.kevinsummary.activity.material;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/9/30.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class CoordinatorLayoutActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_effect_1)
    Button btnEffect1;
    @BindView(R.id.btn_effect_2)
    Button btnEffect2;
    @BindView(R.id.btn_effect_3)
    Button btnEffect3;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_material_coordinatorlayout;
    }

    @Override
    public void initView() {
        actionBar.setTitle("CoordinatorLayout");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btnEffect1.setOnClickListener(this);
        btnEffect2.setOnClickListener(this);
        btnEffect3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_effect_1:
                startActivity(new Intent(this, CoordinatorDemoOneActivity.class));
                break;
            case R.id.btn_effect_2:
                startActivity(new Intent(this, CoordinatorDemoTwoActivity.class));
                break;
            case R.id.btn_effect_3:
                break;
        }
    }
}
