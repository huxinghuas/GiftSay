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
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvInfoBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/29.
 */
public class StartegyRvIfoAdAdapter extends BaseAdapter {

    private List<StartegyRvInfoBean.DataBean.PostsBean> datas;
    private Context context;

    public StartegyRvIfoAdAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<StartegyRvInfoBean.DataBean.PostsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StartegyRvInfoViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_startegy_rv_info, parent, false);
            holder = new StartegyRvInfoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (StartegyRvInfoViewHolder) convertView.getTag();
        }

        holder.titleTv.setText(datas.get(position).getTitle());
        holder.nileNameTv.setText(datas.get(position).getAuthor().getNickname());
        holder.linkCountTv.setText(datas.get(position).getLikes_count() + "");
        Picasso.with(context).load(datas.get(position).getCover_image_url()).into(holder.IfoImg);
        return convertView;
    }

    class StartegyRvInfoViewHolder {
        private ImageView IfoImg;
        private TextView titleTv, nileNameTv, linkCountTv;

        public StartegyRvInfoViewHolder(View view) {
            IfoImg = (ImageView) view.findViewById(R.id.startegy_rv_info_img);
            titleTv = (TextView) view.findViewById(R.id.startegy_rv_info_title);
            nileNameTv = (TextView) view.findViewById(R.id.startegy_rv_info_nikname);
            linkCountTv = (TextView) view.findViewById(R.id.startegy_rv_info_linkcount);
        }
    }

}
