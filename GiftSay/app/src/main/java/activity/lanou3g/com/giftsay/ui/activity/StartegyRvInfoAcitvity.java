package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvInfoBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyRvIfoAdAdapter;

/**
 * Created by dllo on 16/9/27.
 * 攻略rv二级详情页面
 */
public class StartegyRvInfoAcitvity extends AbsBaseActivity {


    String strartUrl = "http://api.liwushuo.com/v2/columns/";
    String middleUrl;
    String endUrl = "?limit=20&offset=0";


    private ListView listView;
    private TextView descrtionTv;
    private ImageView coverImge;
    private StartegyRvIfoAdAdapter adapter;
    private List<StartegyRvInfoBean.DataBean.PostsBean> datas;
    private StartegyRvInfoBean bean;

    @Override
    protected int setLayout() {
        return R.layout.activity_startegy_rv_info;
    }

    @Override
    protected void initView() {
        listView = byview(R.id.startegy_rv_info_lv);
    }

    @Override
    protected void initDatas() {


        Intent intent = getIntent();
        if (intent != null) {
            middleUrl = intent.getStringExtra("url");


        }


        String url = strartUrl + middleUrl + endUrl;
        Log.d("qqq", url);

        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {


            @Override
            public void success(String resultStr) {
                Log.d("StartegyRvInfoAcitvity", resultStr);
                Gson gson = new Gson();
                // 解析数据
                bean = gson.fromJson(resultStr, StartegyRvInfoBean.class);
                // 获取列表数据的集合
                datas = bean.getData().getPosts();
                getHead();
                adapter.setDatas(datas);
                listView.setAdapter(adapter);

            }

            @Override
            public void failure() {

            }
        });


        adapter = new StartegyRvIfoAdAdapter(StartegyRvInfoAcitvity.this);

    }

    private void getHead() {
        View view = getLayoutInflater().inflate(R.layout.headview_staregy_rv_info, null);

        descrtionTv = (TextView) view.findViewById(R.id.head_startegy_info_descrption);
        coverImge = (ImageView) view.findViewById(R.id.head_startegy_info_img);
        Picasso.with(StartegyRvInfoAcitvity.this).load(bean.getData().getCover_image_url()).into(coverImge);
        Log.d("qqq", "bean:" + bean);
        Log.d("qqq", bean.getData().getDescription());
        descrtionTv.setText(bean.getData().getDescription());
        listView.addHeaderView(view);
    }


}
