package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveLvBean;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveRvBean;
import activity.lanou3g.com.giftsay.modle.bean.ShufflingBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.SelectiveLvAdapter;
import activity.lanou3g.com.giftsay.ui.adpter.SelectiveRvAdpter;
import activity.lanou3g.com.giftsay.ui.adpter.ShufflingAdapter;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;
import activity.lanou3g.com.giftsay.view.MyListView;

/**
 * Created by dllo on 16/9/9.
 * 精品页面
 */
public class SelectiveFragment extends AbsBaseFragment {
    private LinearLayout drawerView;


    // 创建轮播图
    private static  final  int TIME = 3000;
    private  LinearLayout pointLl; // 轮播状态改变的小圆点容器
    private ShufflingAdapter shuflingAdapter;
    private List<ShufflingBean.DataBean.BannersBean> datas;
    private ViewPager viewPager;


    // 定义并初始化rv适配器和集合
    private RecyclerView recyclerView;
    private SelectiveRvAdpter rvadpter;
   // 定义并初始化lv适配器和集合;
    private MyListView listview;
    private SelectiveLvAdapter lvadpter;



    String dataShuffling = "http://api.liwushuo.com/v2/banners?channel=IOS";
    String dataRv ="http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=2";
    String dataLv ="http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0";



    @Override
    protected int setLayoout() {

        return R.layout.fragment_selective;
    }

    @Override
    protected void initViews() {
        recyclerView = byView(R.id.selective_rv);
        listview = byView(R.id.seletive_lv);
        viewPager = byView(R.id.shuffling_vp);
        pointLl = byView(R.id.shuffling_point_container);
    }

    @Override
    protected void initDatas() {
         // Rv获取网络数据的方法并解析数据
        geRvtIntent();
        // 横向图片滑动
        getTwoRecycleView();
        // 顶部轮播图
        getLunbo();

    }

    private void getLunbo() {

        VolleyeInstance.getInstance().startRequest(dataShuffling, new VolleyResult() {



            @Override
            public void success(String resultStr) {
                // 获取网络
                Log.d("SelectiveFragmentlunbo", resultStr);
                // 解析
                Gson gson = new Gson();
                ShufflingBean shufflingBean = gson.fromJson(resultStr,ShufflingBean.class);
                datas = shufflingBean.getData().getBanners();

                Log.d("SelectiveFragmentds", "datas.size():" + datas.size());
//                for (int i = 0; i < datas.size(); i++) {
//                    Picasso.with(context).load(shufflingBean.getData().getBanners().get(i).getImage_url());
//                }

                 shuflingAdapter = new ShufflingAdapter(datas,context);
                viewPager.setAdapter(shuflingAdapter);
                // ViewPager的页数为int最大值,设置当前页多一些,可以上来就向前滑动
                // 为了保证第一页始终为数据的第0条 取余要为0,因此设置数据集合大小的倍数
                viewPager.setCurrentItem(datas.size() * 100);
                // 开始轮播
                handler = new Handler();
                startRotate();
                // 添加轮播小店
                addPoints();
                // 随着轮播改变小店
                changePoints();
            }

            @Override
            public void failure() {

            }
        });

    }

    private void changePoints() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (isRotate) {
                    // 把所有小点设置为白色
                    for (int i = 0; i < datas.size(); i++) {
                        ImageView pointIv = (ImageView) pointLl.getChildAt(i);
                        pointIv.setImageResource(R.mipmap.btn_check_normal);
                    }
                    // 设置当前位置小点为灰色
                    ImageView iv = (ImageView) pointLl.getChildAt(position % datas.size());
                    iv.setImageResource(R.mipmap.btn_check_disabled_nightmode);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addPoints() {
            // 有多少张图加载多少个小点
        for (int i = 0; i < datas.size(); i++) {
            ImageView pointTv = new ImageView(context);
            pointTv.setPadding(5,5,5,5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30,30);
            pointTv.setLayoutParams(params);

            // 设置第0个页小点灰色
            if(i == 0 ){
                pointTv.setImageResource(R.mipmap.btn_check_disabled_nightmode);
            }else {
                pointTv.setImageResource(R.mipmap.btn_check_normal);
            }
            pointLl.addView(pointTv);
        }

    }


    /**
     * 开始轮播
     */

    private Handler handler;
    private boolean isRotate = false;
    private Runnable rotateRunnable;


    private  void  startRotate(){

       rotateRunnable = new Runnable() {
           @Override
           public void run() {
               int nowIndex = viewPager.getCurrentItem();
                viewPager.setCurrentItem(++nowIndex);
               if (isRotate){
                   handler.postDelayed(rotateRunnable,TIME);
               }

           }
       };
        handler.postDelayed(rotateRunnable,TIME);
    }

    @Override
    public void onResume() {
        super.onResume();
        isRotate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isRotate = false;
    }



    private void getTwoRecycleView() {

        rvadpter = new SelectiveRvAdpter(context);
        //获取网络
     //   queue = Volley.newRequestQueue(GiftSayApp.getContext());
        VolleyeInstance.getInstance().startRequest(dataRv, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("SelectiveFragmentRV", resultStr);
                // 解析
                Gson gson = new Gson();
                SelectiveRvBean   rvBean = gson.fromJson(resultStr,SelectiveRvBean.class);
                List<SelectiveRvBean.DataBean.SecondaryBannersBean> datas = rvBean.getData().getSecondary_banners();
                Log.d("SelectiveFragmentRv", "datas.size():" + datas.size());
                rvadpter.setDatas(datas);
                recyclerView.setAdapter(rvadpter);
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                 recyclerView.setLayoutManager(manager);

            }

            @Override
            public void failure() {

            }
        });

    }

    private void geRvtIntent() {

         lvadpter = new SelectiveLvAdapter(context);

    //    queue = Volley.newRequestQueue(GiftSayApp.getContext());

        VolleyeInstance.getInstance().startRequest(dataLv, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                // 获取网络
                Log.d("SelectiveFragmentRv", resultStr);
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
