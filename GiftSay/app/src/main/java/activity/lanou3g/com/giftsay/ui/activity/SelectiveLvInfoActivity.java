package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.toolbox.Volley;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;

/**
 * Created by dllo on 16/9/21.
 * 精选lv详情
 */
public class SelectiveLvInfoActivity extends AbsBaseActivity {



//    String urlStart = "http://www.liwushuo.com/posts/";
    String urlMiddle;


    private  WebView webView;
    protected int setLayout() {
        return R.layout.activity_selective_lv_info;
    }

    @Override
    protected void initView() {
        webView = byview(R.id.selecive_lv_webview);
    }

    @Override
    protected void initDatas() {

        Intent intent = getIntent();
        if (intent != null) {
            urlMiddle = intent.getStringExtra("id");
        }
     //   String url = urlStart + urlMiddle;


        WebSettings webSettings = webView.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);

        webView.loadUrl(urlMiddle);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });






    }
}
