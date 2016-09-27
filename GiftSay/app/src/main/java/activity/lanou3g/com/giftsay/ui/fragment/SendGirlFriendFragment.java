package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SendGirlFrendBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.activity.SendGirlFriendActivity;
import activity.lanou3g.com.giftsay.ui.adpter.SendGirlFrendAdapter;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/9.
 * 送女友页面
 */
public class SendGirlFriendFragment extends  AbsBaseFragment {

    private ListView listView;
    private String url;
    private RequestQueue queue;
    private SendGirlFrendAdapter girlFrendAdapter;

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

        girlFrendAdapter = new SendGirlFrendAdapter(context);


        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("SendGirlFriendFragment", resultStr);
                // 解析
                Gson gson = new Gson();
                SendGirlFrendBean bean = gson.fromJson(resultStr,SendGirlFrendBean.class);
                List<SendGirlFrendBean.DataBean.ItemsBean> datas =  bean.getData().getItems();
                girlFrendAdapter.setDatas(datas);
                listView.setAdapter(girlFrendAdapter);
            }

            @Override
            public void failure() {

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 根据数据获取具体某一行位置的行布局
                SendGirlFrendBean.DataBean.ItemsBean bean = (SendGirlFrendBean.DataBean.ItemsBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, SendGirlFriendActivity.class);
                if (bean.getColumn() !=null){
                    intent.putExtra("url",bean.getUrl());
                }
                startActivity(intent);
            }
        });



    }
}
