package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.DayRecomedBean;

/**
 * Created by dllo on 16/9/14.
 */
public class DayRecomedAdpter extends RecyclerView.Adapter<DayRecomedAdpter.DayViewHolder>{

    private Context context;
    private List<DayRecomedBean.DataBean.ItemsBean> datas;

    public DayRecomedAdpter(Context context) {
        this.context = context;
    }

    public void setDatas(List<DayRecomedBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
        Log.d("DayRecomedAdpter", "setDatas");
        notifyDataSetChanged();
    }

    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_day_recome,parent,false);
        DayViewHolder holder = new DayViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {

               DayRecomedBean.DataBean.ItemsBean bean = datas.get(position);
               Log.d("DayRecomedAdpter", bean.getShort_description());
                holder.contactOneTv.setText(bean.getShort_description());
                holder.contactTwoTv.setText(bean.getName());
                holder.priceTv.setText("¥"+ "  " + bean.getPrice());
        Log.d("XXX", bean.getCover_image_url());
                Picasso.with(context).load(bean.getCover_image_url()).into(holder.dayImg);
//             holder.contactOneTv.setText(bean.get(position).getShort_description());
//             holder.contactTwoTv.setText(bean.get(position).getName());
//            holder.priceTv.setText(bean.get(position).getPrice());
        }



    @Override
    public int getItemCount() {
        int count = datas == null ? 0:datas.size();
        Log.d("DayRecomedAdpter", "count:" + count);
        return datas == null ? 0:datas.size();
    }

    class DayViewHolder extends RecyclerView.ViewHolder{
        private TextView contactOneTv,contactTwoTv,priceTv;
        private ImageView dayImg;

        public DayViewHolder(View itemView) {
            super(itemView);
            contactOneTv = (TextView) itemView.findViewById(R.id.item_day_recome_contactt_one);
            contactTwoTv = (TextView) itemView.findViewById(R.id.item_day_recome_contactt_two);
            priceTv = (TextView) itemView.findViewById(R.id.item_day_recome_price);
            dayImg = (ImageView) itemView.findViewById(R.id.iem_day_recom_img);
        }
    }

}
