package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;
import activity.lanou3g.com.giftsay.modle.net.VolleyeInstance;

/**
 * Created by dllo on 16/9/18.
 * 显示大图Activity
 */
public class SelectiveRvInfoActivity extends AbsBaseActivity {

    private ImageView infoImge;

    protected int setLayout() {
        return R.layout.activity_selective_rv_info;
    }

    @Override
    protected void initView() {

        infoImge = byview(R.id.selective_lv_info_img);
    }

    @Override
    protected void initDatas() {
        Intent intent = getIntent();
        if (intent != null){
            String imgUrl = intent.getStringExtra("imgurl");

            Picasso.with(this).load(imgUrl).into(infoImge);


            infoImge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }

    }
}
