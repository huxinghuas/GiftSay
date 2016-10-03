package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductBean;

/**
 * Created by dllo on 16/9/22.
 * 单品右边grideView适配器
 */
public class SingleGrideAdpter extends BaseAdapter {


    private List<SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> datas;
    private Context context;
    private int selectIndex;

    public void setDatas(List<SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public SingleGrideAdpter(Context context) {
        this.context = context;
    }

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

        SingleGrideViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_gride_view, parent, false);
            holder = new SingleGrideViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SingleGrideViewHolder) convertView.getTag();
        }

        SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean bean = datas.get(position);
        Log.d("SingleGrideAdpter", bean.getName());
      holder.textView.setText(bean.getName());
//        holder.textView.setText(datas.get(selectIndex).getName());
        Picasso.with(context).load(datas.get(position).getIcon_url()).into(holder.imageView);
     //   Picasso.with(context).load(datas.get(selectIndex).getIcon_url()).into(holder.imageView);
        return convertView;
    }


    class SingleGrideViewHolder {

        private TextView textView;
        private ImageView imageView;

        public SingleGrideViewHolder(View view) {

            textView = (TextView) view.findViewById(R.id.single_gride_tv);
            imageView = (ImageView) view.findViewById(R.id.single_gride_img);
        }

    }


}
