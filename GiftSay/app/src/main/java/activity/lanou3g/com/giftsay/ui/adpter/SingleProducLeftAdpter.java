package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductBean;

/**
 * Created by dllo on 16/9/19.
 * 单品左面列表适配器
 */
public class SingleProducLeftAdpter extends BaseAdapter {
    private List<SingleProductBean.DataBean.CategoriesBean> datas;
    private Context context;
    private int selectIndex;


    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public SingleProducLeftAdpter(List<SingleProductBean.DataBean.CategoriesBean> datas, Context context) {
        this.datas = datas;
        this.context = context;

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
        SingleLeftViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_left_lv, parent, false);
            holder = new SingleLeftViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SingleLeftViewHolder) convertView.getTag();
        }


        SingleProductBean.DataBean.CategoriesBean bean = datas.get(position);

        holder.showTv.setText(bean.getName());



        if (position == selectIndex) {
            holder.showTv.setTextColor(Color.RED);
            holder.views.setBackgroundColor(Color.RED);
        } else {
            holder.showTv.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.showTv.setTextColor(Color.BLACK);
            holder.views.setBackgroundColor(Color.WHITE);



        }
        return convertView;
    }
    public  void setIndex(int index){
        selectIndex = index;
    }


    class SingleLeftViewHolder {
        private TextView showTv;
        private  View views;
        public SingleLeftViewHolder(View view) {
            showTv = (TextView) view.findViewById(R.id.single_left_tv);
            views = (View) view.findViewById(R.id.single_left_view);
        }
    }

}
