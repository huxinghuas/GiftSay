package activity.lanou3g.com.giftsay.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.DayRecomedBean;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.adpter.DayRecomedAdpter;
import activity.lanou3g.com.giftsay.ui.fragment.AbsBaseFragment;

/**
 * Created by dllo on 16/9/9.
 * 每日精品页面
 */
public class DayRecomedFragment extends AbsBaseFragment {
    private RecyclerView dayRv;
    private String url;
    private DayRecomedAdpter adpter;
    private ImageView imageView;

    public static DayRecomedFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url",url);
        DayRecomedFragment fragment = new DayRecomedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoout() {
        return R.layout.fragment_day_recomend;
    }

    @Override
    protected void initViews() {
      dayRv = byView(R.id.day_recomed_rv);
        imageView = byView(R.id.dayrecomd_img);
    }

    @Override
    protected void initDatas() {
        // 取值
        Bundle bundle = getArguments();
        this.url = bundle.getString("url");
        dayRv.setTag(this.url);

        adpter = new DayRecomedAdpter(context);

        // 获取网络数据
        VolleyeInstance.getInstance().startRequest(url, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                Log.d("dfd", resultStr);
                // 解析
                Gson gson = new Gson();
                DayRecomedBean bean = gson.fromJson(resultStr,DayRecomedBean.class);
                List<DayRecomedBean.DataBean.ItemsBean> datas = bean.getData().getItems();

                Picasso.with(context).load(bean.getData().getCover_image()).into(imageView);
                Log.d("DayRecomedFragment", "datas.size():" + datas.size());
                adpter.setDatas(datas);
                GridLayoutManager manager = new GridLayoutManager(context,2);
                dayRv.setLayoutManager(manager);
                dayRv.setAdapter(adpter);
            }

            @Override
            public void failure() {

            }
        });

    }
}
