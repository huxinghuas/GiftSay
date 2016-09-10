package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/10.
 * 这是攻略页面
 */
public class StartegyFragment extends  AbsBaseFragment {
    private TextView showTv;

    @Override
    protected int setLayoout() {
        return R.layout.fragment_startegy;
    }

    @Override
    protected void initViews() {
       showTv = byView(R.id.startegy_tv);

    }

    @Override
    protected void initDatas() {

    }
}
