package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/8.
 */
public class MyFragment extends AbsBaseFragment {

    private TextView showTv;
    @Override
    protected int setLayoout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViews() {
   showTv = byView(R.id.my_tv);

    }

    @Override
    protected void initDatas() {

    }
}
