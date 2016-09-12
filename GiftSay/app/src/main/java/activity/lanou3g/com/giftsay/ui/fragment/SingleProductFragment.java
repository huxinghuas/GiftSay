package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/10.
 * 这是单品页面
 */
public class SingleProductFragment extends  AbsBaseFragment {

    private TextView showTv;
    protected int setLayoout() {
        return R.layout.fragment_single_product;
    }

    @Override
    protected void initViews() {

        showTv = byView(R.id.single_product_tv);
    }

    @Override
    protected void initDatas() {

    }
}
