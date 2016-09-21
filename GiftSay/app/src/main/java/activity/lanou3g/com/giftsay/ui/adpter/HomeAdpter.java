package activity.lanou3g.com.giftsay.ui.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import activity.lanou3g.com.giftsay.modle.bean.GuidBean;

/**
 * Created by dllo on 16/9/9.
 */
public class HomeAdpter  extends FragmentPagerAdapter{

    private List<Fragment>  fragments;

    public HomeAdpter(FragmentManager fm ,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}
