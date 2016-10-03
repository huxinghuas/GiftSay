package activity.lanou3g.com.giftsay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.activity.LogInActivity;


/**
 * Created by dllo on 16/9/8.
 * 我的页面
 */
public class MyFragment extends AbsBaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private TextView showTv;
    private RadioGroup radioGroup;
    private RadioButton mysingleBtn,mystartegyBtn;
    private String url;
    private ImageView logImg;

    public static MyFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    protected int setLayoout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViews() {
        showTv = byView(R.id.my_tv);
        radioGroup = byView(R.id.my_raidogroup);
        mysingleBtn = byView(R.id.my_single_btn);
        mystartegyBtn = byView(R.id.my_startegy_btn);
       logImg = byView(R.id.my_login_boy_img);
    }

    @Override
    protected void initDatas() {
        radioGroup.setOnCheckedChangeListener(this);

        logImg.setOnClickListener(this);




        Bundle bundle = getArguments();
        this.url = bundle.getString("url");


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


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, LogInActivity.class);
        startActivity(intent);
    }
}
