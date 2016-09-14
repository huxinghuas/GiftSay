package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveRvBean;


/**
 * Created by dllo on 16/9/12.
 * 首页->精品->recycleview-->适配器
 */
public class SelectiveRvAdpter  extends  RecyclerView.Adapter<SelectiveRvAdpter.SelectiveRvHolder> {


    private Context context;
    private List<SelectiveRvBean.DataBean.SecondaryBannersBean> datas;

    public SelectiveRvAdpter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SelectiveRvBean.DataBean.SecondaryBannersBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public SelectiveRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_selective_recycleview, parent, false);
        SelectiveRvHolder holder = new SelectiveRvHolder(view);
        return holder;

    }


    @Override
    public void onBindViewHolder(SelectiveRvHolder holder, int position) {

        SelectiveRvBean.DataBean.SecondaryBannersBean bean = datas.get(position);

        Picasso.with(context).load(bean.getImage_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class SelectiveRvHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        public SelectiveRvHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_selective_rv_img);

        }


    }
}







