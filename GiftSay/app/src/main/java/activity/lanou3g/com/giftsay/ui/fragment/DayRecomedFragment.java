package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 * 每日精品页面
 */
public class DayRecomedFragment extends AbsBaseFragment {
    private RecyclerView dayRv;
    @Override
    protected int setLayoout() {
        return R.layout.fragment_day_recomend;
    }

    @Override
    protected void initViews() {
      dayRv = byView(R.id.day_recomed_rv);
    }

    @Override
    protected void initDatas() {

    }
}
