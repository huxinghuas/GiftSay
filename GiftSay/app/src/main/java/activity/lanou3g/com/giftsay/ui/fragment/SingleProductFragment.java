package activity.lanou3g.com.giftsay.ui.fragment;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private int selecIndex = 0;
    private ListView leftLv;
    private ListView rightLv;
    private SingleProducLeftAdpter leftAdpter;
    private SingleProducRightAdpter rightAdpter;
    private List<SingleProductBean.DataBean.CategoriesBean> leftdatas;// 左
    private List<SingleProductBean.DataBean.CategoriesBean> rightdatas;  // 右
    private List<SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> gridedatas;


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

        leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ppp", "position:" + position);
                selecIndex = position;
                leftAdpter.setSelectIndex(position);
                leftAdpter.notifyDataSetChanged();
                //当点击某个item的时候让这个item自动滑动到listview的顶部(下面item够多，如果点击的是最后一个就不能到达顶部了)
                leftLv.smoothScrollToPositionFromTop(position, 0);
                rightAdpter.setIndex(position);
                rightLv.setSelection(position);
            }
        });


        rightLv.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int scrollState;

            public void onScrollStateChanged(AbsListView view, int scrollState) {

                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    return;
                }
                leftAdpter.setIndex(firstVisibleItem);
                leftLv.smoothScrollToPositionFromTop(firstVisibleItem, 0);
                leftLv.setSelection(firstVisibleItem);
                leftAdpter.notifyDataSetChanged();
            }
        });


    }


    private void getIntentUrl() {

        VolleyeInstance.getInstance().startRequest(GetUrl.SINGLE_PRODUCT, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("SingleProductFragment", resultStr);
                Gson gson = new Gson();
                SingleProductBean bean = gson.fromJson(resultStr, SingleProductBean.class);
                leftdatas = bean.getData().getCategories();
                rightdatas = bean.getData().getCategories();
                Log.d("SingleProductFragment", "datas:" + leftdatas);
                leftAdpter = new SingleProducLeftAdpter(leftdatas, context);
                leftLv.setAdapter(leftAdpter);
                gridedatas = new ArrayList<>();
                rightAdpter = new SingleProducRightAdpter(rightdatas, context);
                rightAdpter.setList(gridedatas);
                Log.d("abc", "rightAdpter:" + rightAdpter);
                rightLv.setAdapter(rightAdpter);

            }

            @Override
            public void failure() {

            }
        });
    }


}
