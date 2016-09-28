package activity.lanou3g.com.giftsay.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.GuidBean;
import activity.lanou3g.com.giftsay.modle.InterFaces.PopWindowOnRvItemClick;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.HomeAdpter;
import activity.lanou3g.com.giftsay.ui.adpter.PopWindowAdapter;

/**
 * Created by dllo on 16/9/8.
 * 首页碎片一级页面
 */
public class HomeFragment extends AbsBaseFragment {

    private TextView showTv;
    private LinearLayout linearLayout;
    private TabLayout homeTab;
    private ViewPager homeVp;
    private HomeAdpter homeAdpter;
    private List<Fragment> fragments;
    private ImageView indexDownImg;
    private List<GuidBean.DataBean.ChannelsBean> list;
    private PopWindowAdapter windowAdapter;
    private RecyclerView popWindowRv;


    // 请求队列
    private RequestQueue queue;
    private String url;

    public static HomeFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    protected int setLayoout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.home_tv);
        linearLayout = byView(R.id.home_title_layout);
        homeTab = byView(R.id.home_tab);
        homeVp = byView(R.id.home_vp);
        indexDownImg = byView(R.id.home_arrow_index_down);
    }


    @Override
    protected void initDatas() {
        // home页tab
        buildData();
        // 

    }


    private void buildData() {

        Bundle bundle = getArguments();
        this.url = bundle.getString("url");

        getPopWindow();


        // 添加tablayout页面数据
        fragments = new ArrayList<>();
        fragments.add(new SelectiveFragment());
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_GIRLFREND));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.ONLIN_SHOP));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.CREATE_LIFE));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_BOYFREND));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_PARENTCE));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_COLLEGUE));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_BODY));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.SEND_DESIGN));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.THE_WIND));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.THE_EXIOC));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.FAN_SENCISE));
        fragments.add(SendGirlFriendFragment.newInstance(GetUrl.THE_LOVELY));


        homeAdpter = new HomeAdpter(getChildFragmentManager(), fragments);
        homeVp.setAdapter(homeAdpter);
        homeTab.setupWithViewPager(homeVp);
        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        windowAdapter = new PopWindowAdapter(context);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_home_tab, null);


        VolleyeInstance.getInstance().startRequest(GetUrl.GUID, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("HomeFragmentURL", resultStr);
                Gson gson = new Gson();
                GuidBean bean = gson.fromJson(resultStr, GuidBean.class);
                list = bean.getData().getChannels();
                Log.d("HomeFragmentURL", "list.size():" + list.size());

                Log.d("HomeFragmentURL", "list:" + list);
                // 获取Tab数据并显示已
                for (int i = 0; i < fragments.size(); i++) {
                    homeTab.getTabAt(i).setText(list.get(i).getName());
                }
                windowAdapter.setDatas(list);

                // 获取popWindow数据并显示


            }

            @Override
            public void failure() {

            }
        });

    }

    private void getPopWindow() {

        indexDownImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                final PopupWindow pw = new PopupWindow(context);
                pw.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View v1 = getLayoutInflater(getArguments()).inflate(R.layout.home_pop_window, null);
                popWindowRv = (RecyclerView) v1.findViewById(R.id.pop_window_rv);
                pw.setContentView(v1);
                pw.setOutsideTouchable(true);
                pw.setFocusable(true);
                pw.showAsDropDown(homeTab);
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        indexDownImg.setImageResource(R.mipmap.arrow_index_down);
                    }
                });

                indexDownImg.setImageResource(R.mipmap.arrow_index_up);

                Log.d("qqq", "windowAdapter:" + windowAdapter);
                Log.d("qqq", "popWindowRv:" + popWindowRv);
                popWindowRv.setAdapter(windowAdapter);
                GridLayoutManager manager = new GridLayoutManager(context, 4);
                popWindowRv.setLayoutManager(manager);

                windowAdapter.setOnRvItemClick(new PopWindowOnRvItemClick() {
                    @Override
                    public void PopWindowOnRvItemClikLinster(int position, GuidBean.DataBean.ChannelsBean bean) {
                        homeVp.setCurrentItem(position);
                        windowAdapter.setSelectIndex(position);
                        pw.dismiss();

                    }
                });

            }

        });


    }


}
