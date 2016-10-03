package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/26.
 */
public class SendGirlFriendActivity  extends  AbsBaseActivity {

    private WebView girleWeb;
    private  String urlend;
    protected int setLayout() {
        return R.layout.activity_girl_info;
    }

    @Override
    protected void initView(){
        girleWeb = byview(R.id.girl_webview);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        if (intent != null){
            urlend = intent.getStringExtra("url");
        }
        WebSettings webSettings =  girleWeb.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Log.d("SendGirlFriendActivity", urlend);
        girleWeb.loadUrl(urlend);
        girleWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}
