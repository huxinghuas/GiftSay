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
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/16.
 * 攻略页面适配器
 */
public class StartegyRvAdapter extends RecyclerView.Adapter<StartegyRvAdapter.MyViewHodler> {


    private Context context;
    private List<StartegyRvBean.DataBean.ColumnsBean> datas;


    public StartegyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<StartegyRvBean.DataBean.ColumnsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_startegy_rv, parent, false);

        float width = ScreenSizeUtil.getScreenWidth(context);
        float height = ScreenSizeUtil.getScreenHeight(context);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int) (height / 7.5);
        params.width = (int) (width / 1.5);
        view.setLayoutParams(params);

        MyViewHodler hodler = new MyViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        StartegyRvBean.DataBean.ColumnsBean bean = datas.get(position);
        holder.contentTv.setText(bean.getSubtitle());
        holder.titleTv.setText(bean.getTitle());
        holder.authorTv.setText(bean.getAuthor());
        Log.d("StartegyRvAdaptersds", bean.getBanner_image_url());

        Picasso.with(context).load(bean.getBanner_image_url()).into(holder.img);
        float width = ScreenSizeUtil.getScreenWidth(context);
        float height = ScreenSizeUtil.getScreenHeight(context);
        ViewGroup.LayoutParams params = holder.img.getLayoutParams();
        params.width = (int) (width / 3.1);
        holder.img.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        private TextView contentTv, titleTv, authorTv;
        private ImageView img;

        public MyViewHodler(View itemView) {
            super(itemView);

            contentTv = (TextView) itemView.findViewById(R.id.item_startegy_contact_tv);
            titleTv = (TextView) itemView.findViewById(R.id.item_startegy_title_tv);
            authorTv = (TextView) itemView.findViewById(R.id.item_startegy_author_tv);
            img = (ImageView) itemView.findViewById(R.id.item_startegy_img);

        }
    }

}
