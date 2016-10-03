package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegyLvBean;

/**
 * Created by dllo on 16/9/30.
 * 品类适配器
 */
public class StartegyClassGrideAdapter extends RecyclerView.Adapter<StartegyClassGrideAdapter.StartegyClassGrideViewHolder> {

    private List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas ;
    private Context context;

    public StartegyClassGrideAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<StartegyLvBean.DataBean.ChannelGroupsBean.ChannelsBean> datas) {
        this.datas = datas;
        Log.d("StartegyClassGrideAdapt", "datas:" + datas);
        notifyDataSetChanged();
    }


    @Override
    public StartegyClassGrideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_staregy_class_gride_img,parent,false);
        StartegyClassGrideViewHolder holder = new StartegyClassGrideViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(StartegyClassGrideViewHolder holder, int position) {

        Picasso.with(context).load(datas.get(position).getCover_image_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class StartegyClassGrideViewHolder extends RecyclerView.ViewHolder{
            private  ImageView imageView;

        public StartegyClassGrideViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.staregy_class_gride_img);
        }
    }
}
