package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.InterFaces.StartgyOnRvitemClick;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.activity.SelectiveLvInfoActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyClassGridInfoActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyClassGrideAllActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyNewGridInfoActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyNewGrideAllActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyRvInfoAcitvity;
import activity.lanou3g.com.giftsay.ui.activity.StartegySeachAllInfoActivity;
import activity.lanou3g.com.giftsay.ui.activity.StartegyStyleGridInfoActivity;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyGrideAdapter;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyRvAdapter;
import activity.lanou3g.com.giftsay.view.MyListView;


/**
 * Created by dllo on 16/9/10.
 * 这是攻略页面
 */
public class StartegyFragment extends AbsBaseFragment implements View.OnClickListener {
    // 攻略rv 的一些初始化
    protected RecyclerView recyclerView;
    private List<StartegyRvBean.DataBean.ColumnsBean> datas;
    private StartegyRvAdapter adapter;

    // 攻略 三个grideview初始化
    private GridView classGridView, styleGridview, newGridview;
    private StartegyGrideAdapter grideclassAdapter, gridestyleAdapter, gridenewAdapter;
    private List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> grideclassbean, gridestylebean, gridenewbean;
    // 查看全部的点击事件
    private TextView rvcheckallTv, classGrideallTv, newGrideallTv;


    protected int setLayoout() {
        return R.layout.fragment_startegy;
    }

    @Override
    protected void initViews() {
        recyclerView = byView(R.id.startegy_rv);
        classGridView = byView(R.id.startegy_class_grid);
        styleGridview = byView(R.id.startegy_style_gride);
        newGridview = byView(R.id.startegy_new_gride);
        rvcheckallTv = byView(R.id.startegy_rv_check_all_tv);
        classGrideallTv = byView(R.id.stategy_class_gride_all_tv);
        newGrideallTv = byView(R.id.stategy_new_gride_all_tv);
    }

    @Override
    protected void initDatas() {
        getIntentRvData();

        getIntentLvData();
        // 点击事件
        getClick();

        classGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean bean = (StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean) parent.getItemAtPosition(position);
                Intent classintent = new Intent(context, StartegyClassGridInfoActivity.class);
                classintent.putExtra("id", bean.getId());
                classintent.putExtra("name",bean.getName());
                startActivity(classintent);
            }
        });

        styleGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean bean = (StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean) parent.getItemAtPosition(position);
                Intent styleIntent = new Intent(context, StartegyStyleGridInfoActivity.class);
                styleIntent.putExtra("id", bean.getId());
                styleIntent.putExtra("name",bean.getName());
                startActivity(styleIntent);

            }
        });

        newGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean bean = (StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean) parent.getItemAtPosition(position);
                Intent newIntent = new Intent(context, StartegyNewGridInfoActivity.class);
                newIntent.putExtra("id", bean.getId());
                newIntent.putExtra("name",bean.getName());
                Log.d("dda", bean.getName());
                startActivity(newIntent);


            }
        });


    }

    private void getClick() {
        rvcheckallTv.setOnClickListener(this);
        classGrideallTv.setOnClickListener(this);
        newGrideallTv.setOnClickListener(this);
    }

    private void getIntentLvData() {

        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_GRIDE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 获取网络数据
                Log.d("StartegyFragmentLv", resultStr);
                // 解析
                Gson gson = new Gson();
                StartegyLvBean startegyLvBean = gson.fromJson(resultStr, StartegyLvBean.class);
                grideclassbean = startegyLvBean.getData().getChannel_groups().get(0).getChannels();
                gridestylebean = startegyLvBean.getData().getChannel_groups().get(1).getChannels();
                gridenewbean = startegyLvBean.getData().getChannel_groups().get(2).getChannels();
                grideclassAdapter = new StartegyGrideAdapter(grideclassbean, context);
                gridestyleAdapter = new StartegyGrideAdapter(gridestylebean, context);
                gridenewAdapter = new StartegyGrideAdapter(gridenewbean, context);
                classGridView.setAdapter(grideclassAdapter);
                styleGridview.setAdapter(gridestyleAdapter);
                newGridview.setAdapter(gridenewAdapter);


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
                StartegyRvBean startgyRvbean = gson.fromJson(resultStr, StartegyRvBean.class);
                datas = startgyRvbean.getData().getColumns();
                Log.d("StartegyFragmentLvsss", "datas.size():" + datas.size());

                adapter = new StartegyRvAdapter(context);
                adapter.setDatas(datas);
                recyclerView.setAdapter(adapter);
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                // LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(manager);
                adapter.setOnRvitemClick(new StartgyOnRvitemClick() {
                    @Override
                    public void StartgyOnRvItemClicListener(int position, StartegyRvBean.DataBean.ColumnsBean bean) {
                        switch (adapter.getItemViewType(position)) {
                            case 0:
                                Intent intentDetail = new Intent(context, StartegyRvInfoAcitvity.class);
                                intentDetail.putExtra("url", bean.getId() + "");
                                Log.d("StartegyFragment", "bean.getId():" + bean.getId());
                                startActivity(intentDetail);
                                break;
                            case 1:
                                Intent intent = new Intent(context, StartegySeachAllInfoActivity.class);
                                startActivity(intent);
                                break;

                        }


                    }
                });

            }

            @Override
            public void failure() {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.startegy_rv_check_all_tv:
                final Intent intentRvCheck = new Intent(context, StartegySeachAllInfoActivity.class);
                startActivity(intentRvCheck);
                break;
            case R.id.stategy_class_gride_all_tv:
                Intent intentClass = new Intent(context, StartegyClassGrideAllActivity.class);
                startActivity(intentClass);
                break;
            case R.id.stategy_new_gride_all_tv:
                Intent intentNew = new Intent(context, StartegyNewGrideAllActivity.class);
                startActivity(intentNew);
                break;
        }


    }
}
