package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;

/**
 * Created by dllo on 16/9/27.
 */
public class StartegyGrideAdapter extends BaseAdapter {

    private List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas;
    private Context context;

    public StartegyGrideAdapter(List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public int getCount() {
        return datas == null ? 0 : 6;
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
      return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StartegyGrideViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_startegy_gride,parent,false);
            holder = new StartegyGrideViewHolder(convertView);
            convertView.setTag(holder);
        }else {
          holder = (StartegyGrideViewHolder) convertView.getTag();
            holder.imageView.setImageResource(R.mipmap.ic_launcherss);
        }
        Picasso.with(context).load(datas.get(position).getCover_image_url()).into(holder.imageView);


        return convertView;
    }




    class  StartegyGrideViewHolder {
        private ImageView imageView;
        public  StartegyGrideViewHolder(View view){
            imageView = (ImageView) view.findViewById(R.id.item_startegy_single_img);
        }
    }




}
