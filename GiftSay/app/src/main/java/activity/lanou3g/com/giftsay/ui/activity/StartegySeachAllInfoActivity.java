package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveLVInfoBean;
import activity.lanou3g.com.giftsay.modle.bean.StartegySeachAllInfoBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.StartegySeachAllInfoAdapter;

/**
 * Created by dllo on 16/9/28.
 */
public class StartegySeachAllInfoActivity extends AbsBaseActivity {
    private RecyclerView  recyclerView;
    private StartegySeachAllInfoAdapter adapter;
    private TextView titleTv;


    @Override
    protected int setLayout() {
        return R.layout.activity_startegy_search_all_info;
    }

    @Override
    protected void initView() {
        recyclerView  = byview(R.id.startegy_search_all_rv);
        titleTv = byview(R.id.list_tv);
    }

    @Override
    protected void initDatas() {

        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_INFO, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                StartegySeachAllInfoBean bean = gson.fromJson(resultStr,StartegySeachAllInfoBean.class);
                List<StartegySeachAllInfoBean.DataBean.ColumnsBean> datas = bean.getData().getColumns();
                Log.d("StartegySeachAllInfoAct", "datas.size():" + datas.size());
                adapter = new StartegySeachAllInfoAdapter(datas,StartegySeachAllInfoActivity.this);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(StartegySeachAllInfoActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void failure() {

            }
        });
    }
}
