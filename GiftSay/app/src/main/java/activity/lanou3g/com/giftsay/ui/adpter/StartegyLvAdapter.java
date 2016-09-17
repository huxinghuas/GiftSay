package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/17.
 */
public class StartegyLvAdapter extends BaseAdapter {

    List<StartegyLvBean.DataBean.ChannelGroupsBean> datas;
    private Context context;


    public StartegyLvAdapter(List<StartegyLvBean.DataBean.ChannelGroupsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public int getCount() {
        return datas == null ? 0 :datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas ==null ? datas:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      StartegyLvViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_startegy_lv,parent,false);
            holder = new StartegyLvViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (StartegyLvViewHolder) convertView.getTag();
        }

        StartegyLvBean.DataBean.ChannelGroupsBean bean = datas.get(position);
        holder.titleTv.setText(bean.getName());
        Picasso.with(context).load(bean.getChannels().get(0).getCover_image_url()).into(holder.oneImg);
        Picasso.with(context).load(bean.getChannels().get(1).getCover_image_url()).into(holder.twoImg);
        Picasso.with(context).load(bean.getChannels().get(2).getCover_image_url()).into(holder.threeImg);
        Picasso.with(context).load(bean.getChannels().get(3).getCover_image_url()).into(holder.fourImg);
        Picasso.with(context).load(bean.getChannels().get(4).getCover_image_url()).into(holder.fiveImg);
        Picasso.with(context).load(bean.getChannels().get(5).getCover_image_url()).into(holder.sixImg);

//        float height = ScreenSizeUtil.getScreenHeight(context);
//        ViewGroup.LayoutParams params = convertView.getLayoutParams();
//        params.height = (int) (height / 3);


        return convertView;
    }

    class  StartegyLvViewHolder{
        private TextView titleTv;
        private ImageView oneImg,twoImg,threeImg,fourImg,fiveImg,sixImg;

        public StartegyLvViewHolder(View view){

            titleTv = (TextView) view.findViewById(R.id.item_startegy_lv_title);
            oneImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_one);
            twoImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_two);
            threeImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_three);
            fourImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_four);
            fiveImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_five);
            sixImg = (ImageView) view.findViewById(R.id.item_startegy_lv_img_six);
        }
    }



}
