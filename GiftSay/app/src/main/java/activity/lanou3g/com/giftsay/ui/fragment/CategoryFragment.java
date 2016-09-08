package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/8.
 */
public class CategoryFragment extends AbsBaseFragment {

    private TextView homeTextView;
    @Override
    protected int setLayoout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews() {
        homeTextView = byView(R.id.home_tv);

    }

    @Override
    protected void initDatas() {

    }
}
