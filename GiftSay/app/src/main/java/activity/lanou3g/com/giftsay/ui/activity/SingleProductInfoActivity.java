package activity.lanou3g.com.giftsay.ui.activity;

import android.util.Log;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductGridViewBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.SingleProdcutInfoAdapter;

/**
 * Created by dllo on 16/9/30.
 */
public class SingleProductInfoActivity extends AbsBaseActivity {

    private GridView singleGridView;
    private String url = "http://api.liwushuo.com/v2/item_subcategories/1/items?limit=20&offset=0";
    private SingleProdcutInfoAdapter adapter;

    protected int setLayout() {
        return R.layout.activity_single_product_info;
    }

    @Override
    protected void initView() {
        singleGridView = byview(R.id.single_product_gride);
    }

    @Override
    protected void initDatas() {
        adapter = new SingleProdcutInfoAdapter(this);
        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Gson gson = new Gson();
                SingleProductGridViewBean bean = gson.fromJson(resultStr,SingleProductGridViewBean.class);
                List<SingleProductGridViewBean.DataBean.ItemsBean> grideDatas = bean.getData().getItems();
                Log.d("SingleProductInfoActivi", "grideDatas.size():" + grideDatas.size());
                adapter.setDatas(grideDatas);
                singleGridView.setAdapter(adapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
