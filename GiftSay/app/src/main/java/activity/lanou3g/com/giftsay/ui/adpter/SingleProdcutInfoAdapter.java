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
import activity.lanou3g.com.giftsay.modle.bean.SingleProductGridViewBean;

/**
 * Created by dllo on 16/9/30.
 * 单品gridview详情适配器
 */
public class SingleProdcutInfoAdapter extends BaseAdapter {
    List<SingleProductGridViewBean.DataBean.ItemsBean> datas;
    private Context context;

    public SingleProdcutInfoAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<SingleProductGridViewBean.DataBean.ItemsBean> datas) {
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
        SingleProdcutInfoViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_product_gride_info_,parent,false);
          holder = new SingleProdcutInfoViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SingleProdcutInfoViewHolder) convertView.getTag();
        }

        holder.contentTv.setText(datas.get(position).getName());
        holder.priceTv.setText("¥"+" "+datas.get(position).getPrice());
        Picasso.with(context).load(datas.get(position).getCover_image_url()).into(holder.imageView);
        return convertView;
    }

    class SingleProdcutInfoViewHolder {
        private TextView contentTv, priceTv;
        private ImageView imageView;
        public SingleProdcutInfoViewHolder(View view) {
            contentTv = (TextView) view.findViewById(R.id.item_single_gride_contactt);
            priceTv = (TextView) view.findViewById(R.id.item_single_gride_price);
            imageView = (ImageView) view.findViewById(R.id.item_single_content_img);

        }
    }

}
