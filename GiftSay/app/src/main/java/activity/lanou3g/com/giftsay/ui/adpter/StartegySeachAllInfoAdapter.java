package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegySeachAllInfoBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/28.
 */
public class StartegySeachAllInfoAdapter  extends  RecyclerView.Adapter<StartegySeachAllInfoAdapter.SeachAllInfoViewHolder> {

    private List<StartegySeachAllInfoBean.DataBean.ColumnsBean> datas;
    private Context context;

    public StartegySeachAllInfoAdapter(List<StartegySeachAllInfoBean.DataBean.ColumnsBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public SeachAllInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_startegy_search_all_info,parent,false);
        SeachAllInfoViewHolder holder = new SeachAllInfoViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(SeachAllInfoViewHolder holder, int position) {

        Picasso.with(context).load(datas.get(position).getBanner_image_url()).into(holder.allImg);
        holder.allTileTv.setText(datas.get(position).getTitle());
        holder.allContentTv.setText(datas.get(position).getSubtitle());
        float width = ScreenSizeUtil.getScreenWidth(context);
        float height = ScreenSizeUtil.getScreenHeight(context);
        ViewGroup.LayoutParams params = holder.allImg.getLayoutParams();
        params.width = (int) (width / 3);
        params.height = (int) (height/7);
        holder.allImg.setLayoutParams(params);
        holder.allAuthorTv.setText(datas.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 :datas.size();
    }


    class SeachAllInfoViewHolder extends RecyclerView.ViewHolder{
        private ImageView allImg;
        private TextView allTileTv,allContentTv,allAuthorTv;

        public SeachAllInfoViewHolder(View itemView) {
            super(itemView);
            allImg = (ImageView) itemView.findViewById(R.id.item_startegy_info_img);
            allTileTv = (TextView) itemView.findViewById(R.id.item_startegy_title_info_tv);
            allContentTv = (TextView) itemView.findViewById(R.id.item_startegy_contact_info_tv);
            allAuthorTv = (TextView) itemView.findViewById(R.id.item_startegy_author_info_tv);

        }
    }
}
