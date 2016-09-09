package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.List;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/8.
 */
public class HomeFragment extends AbsBaseFragment {

    private TextView showTv;
    private TabLayout homeTab;
    private ViewPager homeVp;
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

    }
}
