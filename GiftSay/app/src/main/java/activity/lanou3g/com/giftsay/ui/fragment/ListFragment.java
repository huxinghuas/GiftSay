package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.activity.DayRecomedFragment;
import activity.lanou3g.com.giftsay.ui.adpter.ListedAdpter;

/**
 * Created by dllo on 16/9/8.
 * 这是榜单的适配器
 */
public class ListFragment extends AbsBaseFragment {

    private TextView showTv;
    private List<Fragment> fragments;
    private ListedAdpter adapter;
    private TabLayout listTab;
    private ViewPager listVp;
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
        for (int i = 0; i < 4; i++) {
            fragments.add(new DayRecomedFragment());
        }

        adapter = new ListedAdpter(getChildFragmentManager(),fragments);
        listVp.setAdapter(adapter);
        listTab.setupWithViewPager(listVp);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_list_tab,null);
        listTab.getTabAt(0).setText("每日精品");
        listTab.getTabAt(1).setText("TOP100");
        listTab.getTabAt(2).setText("独立原创榜");
        listTab.getTabAt(3).setText("新星榜");

    }
}
