package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
import activity.lanou3g.com.giftsay.ui.activity.StartegyRvInfoAcitvity;
import activity.lanou3g.com.giftsay.ui.activity.StartegySeachAllInfoActivity;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyGrideAdapter;
import activity.lanou3g.com.giftsay.ui.adpter.StartegyRvAdapter;
import activity.lanou3g.com.giftsay.view.MyListView;

/**
 * Created by dllo on 16/9/10.
 * 这是攻略页面
 */
public class StartegyFragment extends  AbsBaseFragment {

    protected RecyclerView recyclerView;
    private List<StartegyRvBean.DataBean.ColumnsBean> datas;
    private StartegyRvAdapter adapter ;


    private GridView classGridView,styleGridview,newGridview;
    private StartegyGrideAdapter grideclassAdapter,gridestyleAdapter,gridenewAdapter;
    private List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> grideclassbean,gridestylebean,gridenewbean;




    protected int setLayoout() {
        return R.layout.fragment_startegy;
    }

    @Override
    protected void initViews() {
       recyclerView = byView(R.id.startegy_rv);
        classGridView = byView(R.id.startegy_class_grid);
        styleGridview = byView(R.id.startegy_style_gride);
        newGridview = byView(R.id.startegy_new_gride);
    }

    @Override
    protected void initDatas() {
        getIntentRvData();
        getIntentLvData();


    }

    private void getIntentLvData() {

        VolleyeInstance.getInstance().startRequest(GetUrl.STRATEGY_GRIDE, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 获取网络数据
                Log.d("StartegyFragmentLv", resultStr);
                // 解析
                Gson gson = new Gson();
                StartegyLvBean startegyLvBean = gson.fromJson(resultStr,StartegyLvBean.class);
                grideclassbean = startegyLvBean.getData().getChannel_groups().get(0).getChannels();
                gridestylebean = startegyLvBean.getData().getChannel_groups().get(1).getChannels();
                gridenewbean = startegyLvBean.getData().getChannel_groups().get(2).getChannels();
                grideclassAdapter = new StartegyGrideAdapter(grideclassbean,context);
                gridestyleAdapter = new StartegyGrideAdapter(gridestylebean,context);
                gridenewAdapter = new StartegyGrideAdapter(gridenewbean,context);
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
                StartegyRvBean startgyRvbean = gson.fromJson(resultStr,StartegyRvBean.class);
               datas = startgyRvbean.getData().getColumns();
                Log.d("StartegyFragmentLvsss", "datas.size():" + datas.size());

                adapter = new StartegyRvAdapter(context);
                adapter.setDatas(datas);
                recyclerView.setAdapter(adapter);
               StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
               // LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(manager);
                adapter.setOnRvitemClick(new StartgyOnRvitemClick() {
                    @Override
                    public void StartgyOnRvItemClicListener(int position, StartegyRvBean.DataBean.ColumnsBean bean) {
                        switch (adapter.getItemViewType(position)) {
                            case 0:
                                Intent intentDetail = new Intent(context, StartegyRvInfoAcitvity.class);
                                intentDetail.putExtra("url",bean.getId()+"");
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
}
