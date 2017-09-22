package com.kevin.tech.kevinsummary.activity;

import android.content.Intent;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kevin.tech.kevinsummary.R;
import com.kevin.tech.kevinsummary.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/8/31.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class WebViewActivity extends BaseActivity {
    //    @BindView(R.id.tv_title)
//    TextView tvTitle;
//    @BindView(R.id.iv_function)
//    ImageView ivFunction;
//    @BindView(R.id.tool_bar)
//    Toolbar toolBar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.pb_progress_bar)
    ProgressBar progressBar;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
//        actionBar.setTitle("Blog");
        toolBar.setNavigationIcon(R.drawable.ic_close_activity);

        Intent intent = getIntent();
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//                getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                actionBar.setTitle(view.getTitle());
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
//                    progressBar.setVisibility(View.GONE);
                    progressBar.setAlpha(0);
                } else {
                    progressBar.setAlpha(1);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_share:
                showToast("分享");
                break;
            default:
                break;
        }
        return true;
//        return super.onOptionsItemSelected(item);
    }
}
