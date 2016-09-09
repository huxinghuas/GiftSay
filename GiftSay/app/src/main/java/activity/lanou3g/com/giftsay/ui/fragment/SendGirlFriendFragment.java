package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/9.
 */
public class SendGirlFriendFragment extends  AbsBaseFragment {

    private TextView showTv;
    @Override
    protected int setLayoout() {
        return R.layout.fragment_girle_friend;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.girle_friend_tv);
    }

    @Override
    protected void initDatas() {

    }
}
