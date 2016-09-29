package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.DayRecomedBean;
import activity.lanou3g.com.giftsay.modle.InterFaces.DayRecomedOnRvItemClick;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/14.
 * 每日精选适配器
 */
public class DayRecomedAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private String imgUrl;
    private List<DayRecomedBean.DataBean.ItemsBean> datas;
    private static final int TYPE_ONE_IMG = 0;// 一张图片
    private static final int TYPE_LIST = 1; // 列表数据
    private DayRecomedOnRvItemClick onRvItemClick;

    public void setOnRvItemClick(DayRecomedOnRvItemClick onRvItemClick) {
        this.onRvItemClick = onRvItemClick;
    }

    public DayRecomedAdpter(Context context) {
        this.context = context;
    }


    public void setDatas(List<DayRecomedBean.DataBean.ItemsBean> datas, String imgUrl) {
        this.datas = datas;
        this.imgUrl = imgUrl;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_ONE_IMG:
                View v = LayoutInflater.from(context).inflate(R.layout.item_day_title_img, parent, false);
                DayImgViewHolder imgHolder = null;
                imgHolder = new DayImgViewHolder(v);
                return imgHolder;
            case TYPE_LIST:
                View vlist = LayoutInflater.from(context).inflate(R.layout.item_day_content, parent, false);
                DayListViewHolder listHolder = null;
                listHolder = new DayListViewHolder(vlist);
                return listHolder;
        }


        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE_IMG;
        } else {
            return TYPE_LIST;
        }
    }


    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_ONE_IMG ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final int type = getItemViewType(position);
        switch (type) {
            case TYPE_ONE_IMG:
                DayImgViewHolder imgHolder = (DayImgViewHolder) holder;
                ViewGroup.LayoutParams params = imgHolder.OnePageImg.getLayoutParams();
                params.width = ScreenSizeUtil.getScreenWidth(context);
                imgHolder.OnePageImg.setLayoutParams(params);
                Picasso.with(context).load(imgUrl).into(imgHolder.OnePageImg);
                break;
            case TYPE_LIST:
                final DayListViewHolder listHolder = (DayListViewHolder) holder;
                DayRecomedBean.DataBean.ItemsBean itemsBean = datas.get(position - 1);
                Picasso.with(context).load(itemsBean.getCover_image_url()).into(((DayListViewHolder) holder).dayImg);
                listHolder.contactOneTv.setText(itemsBean.getName());
                listHolder.contactTwoTv.setText(itemsBean.getShort_description());
                listHolder.priceTv.setText("¥ " + itemsBean.getPrice());
                break;
        }

        //  item点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvItemClick != null) {
                    int type2 = getItemViewType(holder.getLayoutPosition());
                    switch (type2) {
                        case TYPE_ONE_IMG:
                            break;
                        case TYPE_LIST:
                            // 获取行布局的位置
                            int p = holder.getLayoutPosition();
                            // 获取行布局的数据
                            DayRecomedBean.DataBean.ItemsBean bean = datas.get(position-1);
                            onRvItemClick.OnRvItemClickLisner(p, bean);
                            break;
                    }

                }
            }
        });


    }

    @Override
    public int getItemCount() {


        return datas == null ? 0 : datas.size() + 1;
    }


    class DayImgViewHolder extends RecyclerView.ViewHolder {
        private ImageView OnePageImg;

        public DayImgViewHolder(View itemView) {
            super(itemView);
            OnePageImg = (ImageView) itemView.findViewById(R.id.day_title_img);
        }
    }


    class DayListViewHolder extends RecyclerView.ViewHolder {
        private TextView contactOneTv, contactTwoTv, priceTv;
        private ImageView dayImg;

        public DayListViewHolder(View itemView) {
            super(itemView);
            contactOneTv = (TextView) itemView.findViewById(R.id.day_recome_contactt_one);
            contactTwoTv = (TextView) itemView.findViewById(R.id.item_day_recome_contactt_two);
            priceTv = (TextView) itemView.findViewById(R.id.item_day_recome_price);
            dayImg = (ImageView) itemView.findViewById(R.id.item_day_content_img);
        }
    }

}
