package com.example.clark.groupet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
//import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubView;


public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private MoPubView moPubView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView)findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.group-et.com/");
        //如果页面中链接,如果希望点击链接继续在当前browser中响应,而不是新开Android的系统browser中响应该链接,必须覆盖 WebView的WebViewClient对象
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        final String adUnitId = "497c211508fb480386cb5e2a500c0f01";
        moPubView = (MoPubView) findViewById(R.id.adview);
        moPubView.setAdUnitId(adUnitId);
        moPubView.setAutorefreshEnabled(true);
        moPubView.loadAd();
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (moPubView != null) {
            moPubView.destroy();
            moPubView = null;
        }
    }
}
