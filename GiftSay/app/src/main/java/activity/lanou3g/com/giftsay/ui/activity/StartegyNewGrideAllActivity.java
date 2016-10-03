package activity.lanou3g.com.giftsay.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyClassGrideAdapter;

/**
 * Created by dllo on 16/9/30.
 *  攻略页面->品类->查看全部
 */
public class StartegyNewGrideAllActivity extends AbsBaseActivity {

    private RecyclerView recyclerView;
    private StartegyClassGrideAdapter adapter;
    private TextView titleTv;

    protected int setLayout() {
        return R.layout.activity_startegy_class_gride_all;
    }

    @Override
    protected void initView() {
        recyclerView = byview(R.id.startegy_class_grid_rv);
        titleTv = byview(R.id.class_gride_all_title_tv);
    }


    protected void initDatas() {

        adapter = new StartegyClassGrideAdapter(this);
        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_GRIDE, new VolleyResult() {
            @Override
            public void success(String resultStr) {

                Gson gson = new Gson();
                StartegyLvBean bean = gson.fromJson(resultStr,StartegyLvBean.class);
                List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas = bean.getData().getChannel_groups().get(2).getChannels();
                adapter.setDatas(datas);
                Log.d("dd", "adapter:" + adapter);
                recyclerView.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(StartegyNewGrideAllActivity.this,2);
                recyclerView.setLayoutManager(manager);
                titleTv.setText(bean.getData().getChannel_groups().get(2).getName());
            }

            @Override
            public void failure() {

            }
        });
    }
}
