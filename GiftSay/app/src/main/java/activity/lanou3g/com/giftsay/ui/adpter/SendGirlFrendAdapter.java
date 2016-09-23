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
import activity.lanou3g.com.giftsay.modle.bean.SelectiveLvBean;
import activity.lanou3g.com.giftsay.modle.bean.SendGirlFrendBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/9/14.
 * 送女友适配器
 */
public class SendGirlFrendAdapter extends BaseAdapter {

    private Context context;
    private List<SendGirlFrendBean.DataBean.ItemsBean> datas;

    public SendGirlFrendAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SendGirlFrendBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null :datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_selective_listview, parent, false);
            holder = new MyViewHolder(convertView);
            int height  = ScreenSizeUtil.getScreenHeight(context);
            ViewGroup.LayoutParams params = convertView.getLayoutParams();
            params.height = (int) (height/2.12);
            convertView.setLayoutParams(params);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        SendGirlFrendBean.DataBean.ItemsBean bean = datas.get(position);
        if(bean.getColumn() != null && bean.getAuthor() != null){
            holder.show_first_line_left_tv.setText(bean.getColumn().getCategory());
            holder.show_first_line_left_second_tv.setText(bean.getColumn().getTitle());
            Picasso.with(context).load(bean.getAuthor().getAvatar_url()).into(holder.show_first_line_right_img);
            holder.show_first_line_rihgt_tv.setText(bean.getAuthor().getNickname());
            Picasso.with(context).load(bean.getCover_image_url()).into(holder.show_center_img);
            holder.show_three_line_left_tv.setText(bean.getTitle());
            holder.show_three_line_right_tv.setText(bean.getLikes_count()+"");

        }
        return convertView;
    }



    class MyViewHolder {

        private TextView show_first_line_left_tv, show_first_line_left_second_tv, show_first_line_rihgt_tv;
        private CircleImageView show_first_line_right_img;
        private ImageView show_center_img;
        private TextView show_three_line_left_tv, show_three_line_right_tv;
        private ImageView show_three_line_right_img;


        public MyViewHolder(View v) {

            show_first_line_left_tv = (TextView) v.findViewById(R.id.selective_lv_first_line_left_tv);
            show_first_line_left_second_tv = (TextView) v.findViewById(R.id.seletive_lv_first_line_left_second_tv);
            show_first_line_rihgt_tv = (TextView) v.findViewById(R.id.selective_lv_first_line_right_tv);
            show_first_line_right_img = (CircleImageView) v.findViewById(R.id.selective_lv_first_line_right_img);
            show_center_img = (ImageView) v.findViewById(R.id.selective_lv_center_img);
            show_three_line_left_tv = (TextView) v.findViewById(R.id.selective_lv_three_line_left_tv);
            show_three_line_right_img = (ImageView) v.findViewById(R.id.selective_lv_three_righ_timg);
            show_three_line_right_tv = (TextView) v.findViewById(R.id.selectiver_lv_three_right_tv);
        }

    }






}
