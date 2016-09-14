package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveLvBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.SelectiveLvAdapter;
import activity.lanou3g.com.giftsay.ui.adpter.SelectiveRvAdpter;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;
import activity.lanou3g.com.giftsay.view.MyListView;

/**
 * Created by dllo on 16/9/9.
 * 精品页面
 */
public class SelectiveFragment extends AbsBaseFragment {
    private TextView showTv;

    private RequestQueue queue;
    // 定义并初始化rv适配器和集合
    private RecyclerView recyclerView;
    private SelectiveRvAdpter adpter;
    private List<Integer> datas;
   // 定义并初始化lv适配器和集合;
    private MyListView listview;
    private SelectiveLvAdapter lvadpter;



    String datalunbo = "http://api.liwushuo.com/v2/banners";
    String dataRv ="";
    String dataLv ="http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";



    @Override
    protected int setLayoout() {

        return R.layout.fragment_selective;
    }

    @Override
    protected void initViews() {
        recyclerView = byView(R.id.selective_rv);
        listview = byView(R.id.seletive_lv);
    }

    @Override
    protected void initDatas() {
         // 获取网络数据的方法
         getIntent();
        // 横向图片滑动
        getTwoRecycleView();
        // 底部listView
        getFourListView();

    }

    private void getFourListView() {




    }

    private void getTwoRecycleView() {  datas = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            datas.add(R.mipmap.ic_launcherss);
        }
        adpter = new SelectiveRvAdpter(datas, context);
        recyclerView.setAdapter(adpter);
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
    }

    private void getIntent() {

         lvadpter = new SelectiveLvAdapter(context);

        queue = Volley.newRequestQueue(GiftSayApp.getContext());

        VolleyeInstance.getInstance().startRequest(dataLv, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 获取网络
                Log.d("SelectiveFragment", resultStr);
                // 解析
                Gson gson = new Gson();
                SelectiveLvBean  lvbean = gson.fromJson(resultStr,SelectiveLvBean.class);
                // 获取数据添加到集合

              List<SelectiveLvBean.DataBean.ItemsBean> list = lvbean.getData().getItems();
                   Log.d("xxx", "datas.size():" + list.size());
                lvadpter.setDatas(list);
                listview.setAdapter(lvadpter);

            }

            @Override
            public void failure() {

            }
        });

    }



}
