package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.adpter.CategoryAdpter;

/**
 * Created by dllo on 16/9/8.
 * 分类碎片一级页面
 */
public class CategoryFragment extends AbsBaseFragment {

    private TextView homeTextView;
    private CategoryAdpter adpter;
    private List<Fragment> fragments;
    private TabLayout categoryTab;
    private ViewPager categoryVp;

    @Override
    protected int setLayoout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews() {
        homeTextView = byView(R.id.category_tv);
        categoryTab = byView(R.id.category_tab);
        categoryVp = byView(R.id.category_vp);

    }

    @Override
    protected void initDatas() {

        fragments = new ArrayList<>();

        fragments.add(new StartegyFragment());
        fragments.add(new SingleProductFragment());

        adpter = new CategoryAdpter(getChildFragmentManager(),fragments);
        categoryVp.setAdapter(adpter);
        categoryTab.setupWithViewPager(categoryVp);
        View v = getLayoutInflater(getArguments()).inflate(R.layout.item_category_tab,null);
        categoryTab.getTabAt(0).setText("攻略");
        categoryTab.getTabAt(1).setText("单品");

    }
}
