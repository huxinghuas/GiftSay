package activity.lanou3g.com.giftsay.ui.activity;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 */
public class DayRecomedFragment extends AbsBaseFragment {
    private TextView showTv;
    @Override
    protected int setLayoout() {
        return R.layout.fragment_day_recomend;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.day_recomend_tv);
    }

    @Override
    protected void initDatas() {

    }
}
