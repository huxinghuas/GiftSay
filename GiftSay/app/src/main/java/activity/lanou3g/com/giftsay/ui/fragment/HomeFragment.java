package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.ui.adpter.HomeAdpter;

/**
 * Created by dllo on 16/9/8.
 * 首页碎片一级页面
 */
public class HomeFragment extends AbsBaseFragment {

    private TextView showTv;
    private TabLayout homeTab;
    private ViewPager homeVp;
    private HomeAdpter homeAdpter;
    private List<Fragment> fragments;

    // 请求队列
    private RequestQueue queue;

    protected int setLayoout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.home_tv);
        homeTab = byView(R.id.home_tab);
        homeVp = byView(R.id.home_vp);


    }

    @Override
    protected void initDatas() {
        // home页tab
        buildData();
        // 
        initGetNet();
    }

    private void initGetNet() {
    }

    private void buildData() {
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



        homeAdpter = new HomeAdpter(getChildFragmentManager(),fragments);
        homeVp.setAdapter(homeAdpter);
        homeTab.setupWithViewPager(homeVp);
        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_home_tab,null);


        homeTab.getTabAt(0).setText("精品");
        homeTab.getTabAt(1).setText("送女票");
        homeTab.getTabAt(2).setText("海淘");
        homeTab.getTabAt(3).setText("创意生活");
        homeTab.getTabAt(4).setText("送基友");
        homeTab.getTabAt(5).setText("送爸妈");
        homeTab.getTabAt(6).setText("送宝贝");
        homeTab.getTabAt(7).setText("设计感");
        homeTab.getTabAt(8).setText("文艺风");
        homeTab.getTabAt(9).setText("奇葩搞怪");
        homeTab.getTabAt(10).setText("科技范");
        homeTab.getTabAt(11).setText("萌萌哒");
    }


}
