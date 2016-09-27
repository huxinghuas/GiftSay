package activity.lanou3g.com.giftsay.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.DayRecomedBean;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.ListedAdpter;

/**
 * Created by dllo on 16/9/8.
 * 榜单碎片一级页面
 */
public class ListFragment extends AbsBaseFragment {

    private TextView showTv;
    private List<Fragment> fragments;
    private ListedAdpter adapter;
    private TabLayout listTab;
    private ViewPager listVp;
    private String url;




    public static ListFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayoout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.list_tv);
        listTab = byView(R.id.list_tab);
        listVp = byView(R.id.list_vp);

    }

    @Override
    protected void initDatas() {
        // 添加碎片(每日推荐视图)
        fragments = new ArrayList<>();
        fragments.add(DayRecomedFragment.newInstance(GetUrl.EVERY_DAY));
        fragments.add(DayRecomedFragment.newInstance(GetUrl.TOP_ONE));
        fragments.add(DayRecomedFragment.newInstance(GetUrl.ORIGINL));
        fragments.add(DayRecomedFragment.newInstance(GetUrl.NEW_START));

        adapter = new ListedAdpter(getChildFragmentManager(),fragments);
        listVp.setAdapter(adapter);
        listTab.setupWithViewPager(listVp);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_list_tab,null);
        listTab.getTabAt(0).setText("每日精品");
        listTab.getTabAt(1).setText("TOP100");
        listTab.getTabAt(2).setText("独立原创榜");
        listTab.getTabAt(3).setText("新星榜");


        Bundle bundle = getArguments();
        this.url = bundle.getString("url");
        // showTv.setText(this.url);




    }
}
