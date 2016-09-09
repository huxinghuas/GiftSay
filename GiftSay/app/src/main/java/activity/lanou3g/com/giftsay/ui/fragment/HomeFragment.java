package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.adpter.HomeAdpter;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/8.
 */
public class HomeFragment extends AbsBaseFragment {

    private TextView showTv;
    private TabLayout homeTab;
    private ViewPager homeVp;
    private HomeAdpter homeAdpter;
    private List<Fragment> fragments;


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
        // 添加tablayout页面数据
        fragments = new ArrayList<>();
        fragments.add(new SelectiveFragment());
        for (int i = 0; i < 11; i++) {
            fragments.add(new SendGirlFriendFragment());
        }


        homeAdpter = new HomeAdpter(getChildFragmentManager(),fragments);
        homeVp.setAdapter(homeAdpter);
        homeTab.setupWithViewPager(homeVp);
        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_home_tab,null);
        homeTab.getTabAt(0).setText("精品");
        homeTab.getTabAt(1).setText("送女票");
        homeTab.getTabAt(2).setText("送女票");
        homeTab.getTabAt(3).setText("送女票");
        homeTab.getTabAt(4).setText("送女票");
        homeTab.getTabAt(5).setText("送女票");
        homeTab.getTabAt(6).setText("送女票");
        homeTab.getTabAt(7).setText("送女票");
        homeTab.getTabAt(8).setText("送女票");
        homeTab.getTabAt(9).setText("送女票");
        homeTab.getTabAt(10).setText("送女票");
        homeTab.getTabAt(11).setText("送女票");
    }




}
