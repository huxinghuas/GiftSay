package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.ShufflingBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/15.
 * 轮播适配器
 */
public class ShufflingAdapter extends PagerAdapter {

    private List<ShufflingBean.DataBean.BannersBean> datas;
    private LayoutInflater inflater;
    private Context context;


    public ShufflingAdapter(List<ShufflingBean.DataBean.BannersBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setDatas(List<ShufflingBean.DataBean.BannersBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // 为了让ViewPager到最后一页不会像翻书一样回到第一页
        // 设置页数为int最大值,这样向下滑动永远都是下一页
        return datas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // position是int最大值所以这里可能是几百甚至上千,因此取余避免数组越界

        int newPositon = position % datas.size();
        View convertView = inflater.inflate(R.layout.item_shuffing_img, container, false);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_shuffling_imgaview);
        Log.d("ShufflingAdapter", "datas:" + datas.get(newPositon).getId());
        ShufflingBean.DataBean.BannersBean bean = datas.get(newPositon);
        Picasso.with(context).load(bean.getImage_url()).into(imageView);
//        int height = ScreenSizeUtil.getScreenHeight(context);
//        ViewGroup.LayoutParams params = convertView.getLayoutParams();
//        params.height = (int) height / 3;
//        convertView.setLayoutParams(params);
        container.addView(convertView);
        return convertView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
