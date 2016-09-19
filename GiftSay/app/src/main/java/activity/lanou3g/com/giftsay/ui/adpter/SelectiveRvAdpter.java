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
import activity.lanou3g.com.giftsay.modle.bean.SelectOnRvitemClick;
import activity.lanou3g.com.giftsay.modle.bean.SelectiveRvBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;


/**
 * Created by dllo on 16/9/12.
 * 首页->精品->recycleview-->适配器
 */
public class SelectiveRvAdpter  extends  RecyclerView.Adapter<SelectiveRvAdpter.SelectiveRvHolder> {


    private Context context;
    private List<SelectiveRvBean.DataBean.SecondaryBannersBean> datas;

    private SelectOnRvitemClick onRvitemClick;


    public void setOnRvitemClick(SelectOnRvitemClick onRvitemClick) {
        this.onRvitemClick = onRvitemClick;
    }

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
        // 用工具类分别适配高度和宽度
        int height  = ScreenSizeUtil.getScreenHeight(context);
        int weight = ScreenSizeUtil.getScreenWidth(context);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = (int)(height/7) ;
        params.width = (int)(weight/4);
        view.setLayoutParams(params);

        return holder;

    }


    @Override
    public void onBindViewHolder(final SelectiveRvHolder holder, final int position) {

        SelectiveRvBean.DataBean.SecondaryBannersBean bean = datas.get(position);

        Picasso.with(context).load(bean.getImage_url()).into(holder.img);

        // 设置item的行点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRvitemClick != null){
                    // 获取当前行布局的positon
                    int p = holder.getLayoutPosition();
                    // 获取当前行布局的数据
                    SelectiveRvBean.DataBean.SecondaryBannersBean str  = datas.get(position);

                    onRvitemClick.onRvItemClickListener(p,str);
                }

            }
        });



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







