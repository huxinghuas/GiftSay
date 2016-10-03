package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StargegyClassGrideBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.StarteegyClassGrideAdpter;

/**
 * Created by dllo on 16/9/29.
 * 攻略页面->
 */
public class StartegyStyleGridInfoActivity extends AbsBaseActivity {

    private String startUrl = "http://api.liwushuo.com/v2/channels/";
    int middleUrl;
    private String endUrl = "/items_v2?order_by=now&limit=20&offset=0";
    private StarteegyClassGrideAdpter adapter;
    private String nameurl;
    private TextView titelTv;
    private ListView listView;

    protected int setLayout() {
        return R.layout.activity_startegy_class_gride;
    }

    @Override
    protected void initView() {
        listView = byview(R.id.gride_view_lv);
            titelTv = byview(R.id.class_gride_title_ifo_tv);
    }

    @Override
    protected void initDatas() {

        Intent intent = getIntent();

        if (intent != null) {

            middleUrl = intent.getIntExtra("id", 0);
            nameurl = intent.getStringExtra("name");
        }
        String url = startUrl + middleUrl + endUrl;


        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("StartegyClassGridInfoAc", resultStr);
                Gson gson = new Gson();
                StargegyClassGrideBean bean = gson.fromJson(resultStr, StargegyClassGrideBean.class);
                List<StargegyClassGrideBean.DataBean.ItemsBean> datas = bean.getData().getItems();
                Log.d("StartegyClassGridInfoAc", "datas.size():" + datas.size());
                titelTv.setText(bean.getData().getItems().get(1).getColumn().getCategory());
                adapter = new StarteegyClassGrideAdpter(StartegyStyleGridInfoActivity.this);
                adapter.setDatas(datas);
                listView.setAdapter(adapter);
                titelTv.setText(nameurl);

            }

            @Override
            public void failure() {

            }
        });

    }
}
