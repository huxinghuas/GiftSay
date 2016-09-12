package activity.lanou3g.com.giftsay.ui.fragment;

import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;
import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/9.
 * 精选页面
 */
public class SelectiveFragment extends AbsBaseFragment {
    private TextView showTv;

    private RequestQueue queue;
    String dataUrl = "http://api.liwushuo.com/v2/banners";

    @Override
    protected int setLayoout() {
        return R.layout.fragment_selective;
    }

    @Override
    protected void initViews() {
    showTv = byView(R.id.seletive_tv);
    }

    @Override
    protected void initDatas() {

        queue = Volley.newRequestQueue(GiftSayApp.getContext());

        VolleyeInstance.getInstance().startRequest(dataUrl, new VolleyResult() {
            @Override
            public void success(String resultStr) {
                showTv.setText(resultStr);
            }

            @Override
            public void failure() {

            }
        });
    }
}
