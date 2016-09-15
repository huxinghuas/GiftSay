package activity.lanou3g.com.giftsay.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;


/**
 * Created by dllo on 16/9/8.
 * 我的页面
 */
public class MyFragment extends AbsBaseFragment implements RadioGroup.OnCheckedChangeListener {

    private TextView showTv;
    private RadioGroup radioGroup;
    private RadioButton mysingleBtn,mystartegyBtn;

    protected int setLayoout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.my_tv);
        radioGroup = byView(R.id.my_raidogroup);
        mysingleBtn = byView(R.id.my_single_btn);
        mystartegyBtn = byView(R.id.my_startegy_btn);

    }

    @Override
    protected void initDatas() {

        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.my_single_btn:
                break;
            case R.id.my_startegy_btn:
                break;
        }
    }
}
