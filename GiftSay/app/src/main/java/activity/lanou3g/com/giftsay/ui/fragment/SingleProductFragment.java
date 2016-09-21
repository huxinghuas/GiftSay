package activity.lanou3g.com.giftsay.ui.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GetUrl;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.SingleProducLeftAdpter;
import activity.lanou3g.com.giftsay.ui.adpter.SingleProducRightAdpter;


/**
 * Created by dllo on 16/9/10.
 * 这是单品页面
 */
public class SingleProductFragment extends AbsBaseFragment {

    private ListView leftLv;
    private ListView rightLv;
    private SingleProducLeftAdpter leftAdpter;
    private SingleProducRightAdpter rightAdpter;
    private List<SingleProductBean.DataBean.CategoriesBean> datas;// 左
    private List<SingleProductBean.DataBean.CategoriesBean> list;  // 右


    protected int setLayoout() {
        return R.layout.fragment_single_product;
    }

    @Override
    protected void initViews() {

        leftLv = byView(R.id.single_left_lv);
        rightLv = byView(R.id.single_right_lv);

    }

    @Override
    protected void initDatas() {

        getIntentUrl();
        onclik();

    }


    private void onclik() {

    }


    private void getIntentUrl() {

        VolleyeInstance.getInstance().startRequest(GetUrl.SINGLE_PRODUCT, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("SingleProductFragment", resultStr);
                Gson gson = new Gson();
                SingleProductBean bean = gson.fromJson(resultStr, SingleProductBean.class);
                datas = bean.getData().getCategories();
                list = bean.getData().getCategories();
                Log.d("SingleProductFragment", "datas:" + datas);
                leftAdpter = new SingleProducLeftAdpter(datas, context);
                leftLv.setAdapter(leftAdpter);
                rightAdpter = new SingleProducRightAdpter(datas,context);
                rightLv.setAdapter(rightAdpter);

            }

            @Override
            public void failure() {

            }
        });
    }


}
