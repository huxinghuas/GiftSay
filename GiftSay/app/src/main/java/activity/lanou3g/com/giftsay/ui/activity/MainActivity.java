package activity.lanou3g.com.giftsay.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.ui.fragment.CategoryFragment;
import activity.lanou3g.com.giftsay.ui.fragment.HomeFragment;
import activity.lanou3g.com.giftsay.ui.fragment.ListFragment;
import activity.lanou3g.com.giftsay.ui.fragment.MyFragment;

public class MainActivity extends AbsBaseActivity {

    private RadioGroup radioGroup;
    // 定义并初始化四个碎片
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private CategoryFragment categoryFragment;
    private MyFragment myFragment;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        radioGroup = byview(R.id.main_radio_grop);

    }

    @Override
    protected void initDatas() {
        homeFragment = new HomeFragment();
        listFragment = new ListFragment();
        categoryFragment = new CategoryFragment();
        myFragment = new MyFragment();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 创建并初始化业务管理者
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                switch (checkedId) {
                    case R.id.main_home_rb:
                        transaction.replace(R.id.main_frame_layout, homeFragment);
                        break;
                    case R.id.main_list_rb:
                        transaction.replace(R.id.main_frame_layout, listFragment);
                        break;
                    case R.id.main_class_category_rb:
                        transaction.replace(R.id.main_frame_layout, categoryFragment);
                        break;
                    case R.id.main_my_rb:
                        transaction.replace(R.id.main_frame_layout, myFragment);
                        break;
                }


                transaction.commit();
            }
        });
        radioGroup.check(R.id.main_home_rb);
    }
}
