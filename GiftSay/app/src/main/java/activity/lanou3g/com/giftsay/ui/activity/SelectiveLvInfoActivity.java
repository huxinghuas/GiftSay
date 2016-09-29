package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveLVInfoBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;

/**
 * Created by dllo on 16/9/21.
 * 精选lv详情
 */
public class SelectiveLvInfoActivity extends AbsBaseActivity {


    private TextView fomalTv,shareTv,contenTv;



    String urlStart = "http://api.liwushuo.com/v2/posts_v2/";
    int urlMiddle ;



    String url;


    private  WebView webView;
    protected int setLayout() {
        return R.layout.activity_selective_lv_info;
    }

    @Override
    protected void initView() {
        webView = byview(R.id.selecive_lv_webview);
        fomalTv = byview(R.id.selcetive_lv_info_normal);
        shareTv = byview(R.id.selective_lv_info_share);
        contenTv = byview(R.id.selective_lv_info_comment);
    }

    @Override
    protected void initDatas() {

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            urlMiddle = intent.getIntExtra("id",0);
        }

        String finalUrl = urlStart + urlMiddle;

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

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


    VolleyeInstance.getInstance().startRequest(finalUrl, new VolleyResult() {
        @Override
        public void success(String resultStr) {
            Log.d("SelectiveLvInfoActivity", resultStr);
            Gson gson = new Gson();
            SelectiveLVInfoBean infoBean= gson.fromJson(resultStr,SelectiveLVInfoBean.class);
            fomalTv.setText(infoBean.getData().getLikes_count()+"");
            shareTv.setText(infoBean.getData().getShares_count()+"");
            contenTv.setText(infoBean.getData().getComments_count()+"");
        }

        @Override
        public void failure() {

        }
    });



    }
}
