package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;

/**
 * Created by dllo on 16/9/27.
 */
public class StartegyRvInfoAcitvity extends  AbsBaseActivity {


    String strartUrl ="http://api.liwushuo.com/v2/columns/";
    String middleUrl;
    String endUrl ="?limit=20&offset=0";


    private WebView infoWebView;
    @Override
    protected int setLayout() {
        return R.layout.activity_startegy_rv_info;
    }

    @Override
    protected void initView() {
        infoWebView = byview(R.id.startegy_rv_webview);
    }

    @Override
    protected void initDatas() {



        Intent intent = getIntent();
        if (intent != null){
            middleUrl = intent.getStringExtra("url");
            Log.d("qqq", "url");

        }


    String url = strartUrl+middleUrl+endUrl;
        Log.d("qqq", url);


    infoWebView.loadUrl(url);
        Log.d("qqq", url);


        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("StartegyRvInfoAcitvity", resultStr);

            }

            @Override
            public void failure() {

            }
        });

    }


}
