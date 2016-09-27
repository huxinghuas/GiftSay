package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/26.
 */
public class DayRecomedactivity extends AbsBaseActivity {

    private WebView webView;
    private String urlEnd;

    @Override
    protected int setLayout() {
        return R.layout.activity_day_recomeda;
    }

    @Override
    protected void initView() {
        webView = byview(R.id.day_webview);
    }

    @Override
    protected void initDatas() {

        Intent intent = getIntent();
        urlEnd = intent.getStringExtra("imgurl");
        WebSettings webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(urlEnd);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}
