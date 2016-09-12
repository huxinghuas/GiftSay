package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.SelectiveRvAdpter;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/9.
 * 精选页面
 */
public class SelectiveFragment extends AbsBaseFragment {
    private TextView showTv;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private SelectiveRvAdpter adpter;
    private List<Integer> datas;
    String dataUrl = "http://api.liwushuo.com/v2/banners";


    @Override
    protected int setLayoout() {

        return R.layout.fragment_selective;
    }

    @Override
    protected void initViews() {
        recyclerView = byView(R.id.selective_rv);
    }

    @Override
    protected void initDatas() {
         // 获取网络数据的方法
         getIntent();
        // 横向图片滑动
        getTwoRecycleView();

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
        queue = Volley.newRequestQueue(GiftSayApp.getContext());

        VolleyeInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("SelectiveFragment", resultStr);
            }

            @Override
            public void failure() {

            }
        });
    }
}
