package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyLvAdapter;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyRvAdapter;
import activity.lanou3g.com.giftsay.view.MyListView;

/**
 * Created by dllo on 16/9/10.
 * 这是攻略页面
 */
public class StartegyFragment extends  AbsBaseFragment {
    private RecyclerView recyclerView;

    private List<StartegyRvBean.DataBean.ColumnsBean> datas;
    private StartegyRvAdapter adapter ;


    private List<StartegyLvBean.DataBean.ChannelGroupsBean>  list;
    private StartegyLvAdapter lvAdapter;
    private MyListView listView;


    protected int setLayoout() {
        return R.layout.fragment_startegy;
    }

    @Override
    protected void initViews() {
       recyclerView = byView(R.id.startegy_rv);
        listView = byView(R.id.strartegy_list_view);

    }

    @Override
    protected void initDatas() {
        getIntentRvData();
        getIntentLvData();


    }

    private void getIntentLvData() {

        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_LV, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 获取网络数据
                Log.d("StartegyFragmentLv", resultStr);
                // 解析
                Gson gson = new Gson();
                StartegyLvBean startegyLvBean = gson.fromJson(resultStr,StartegyLvBean.class);
                list = startegyLvBean.getData().getChannel_groups();
                Log.d("StartegyFragmentlvss", "list.size():" + list.size());
                lvAdapter = new StartegyLvAdapter(list,context);
                listView.setAdapter(lvAdapter);

            }

            @Override
            public void failure() {

            }
        });

    }

    private void getIntentRvData() {
        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_RV, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("StartegyFragmentRv", resultStr);

                Gson gson = new Gson();
                StartegyRvBean startgyRvbean = gson.fromJson(resultStr,StartegyRvBean.class);
               datas = startgyRvbean.getData().getColumns();
                Log.d("StartegyFragmentLvsss", "datas.size():" + datas.size());

                adapter = new StartegyRvAdapter(context);
                adapter.setDatas(datas);
                recyclerView.setAdapter(adapter);
               StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
               // LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void failure() {

            }
        });
    }
}
