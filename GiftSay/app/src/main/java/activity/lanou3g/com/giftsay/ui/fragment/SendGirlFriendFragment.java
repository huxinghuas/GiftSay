package activity.lanou3g.com.giftsay.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/9.
 * 送女友页面
 */
public class SendGirlFriendFragment extends  AbsBaseFragment {

    private ListView listView;
    private String url;
    private RequestQueue queue;

    public static SendGirlFriendFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        SendGirlFriendFragment fragment = new SendGirlFriendFragment();
        fragment.setArguments(args);
        return fragment;
    }


    protected int setLayoout() {
        return R.layout.fragment_girle_friend;
    }

    @Override
    protected void initViews() {

        listView = byView(R.id.sendgirle_lv);
    }

    @Override
    protected void initDatas() {

        // 取值
        Bundle bundle = getArguments();
        this.url = bundle.getString("url");
        listView.setTag(this.url);
        // 开始网络请求

        queue = Volley.newRequestQueue(GiftSayApp.getContext());
        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Toast.makeText(context, resultStr, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure() {

            }
        });


        //解析



    }
}
