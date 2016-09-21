package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductBean;

/**
 * Created by dllo on 16/9/21.
 */
public class SingleProducRightAdpter extends BaseAdapter {

    List<SingleProductBean.DataBean.CategoriesBean> datas;
    private Context context;


    public SingleProducRightAdpter(List<SingleProductBean.DataBean.CategoriesBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 :datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SingleRightViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_right_lv,parent,false);
            holder = new SingleRightViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SingleRightViewHolder) convertView.getTag();
        }

        SingleProductBean.DataBean.CategoriesBean bean = datas.get(position);
        holder.titleTv.setText(bean.getName());
        holder.linearLayout.setTag("");
        return convertView;
    }


    class  SingleRightViewHolder{

        private TextView titleTv;
        private RecyclerView recyclerView;
        private LinearLayout linearLayout;
        public  SingleRightViewHolder(View v){

            titleTv = (TextView) v.findViewById(R.id.single_right_title_tv);
            recyclerView = (RecyclerView) v.findViewById(R.id.single_right_rv);
            linearLayout = (LinearLayout) v.findViewById(R.id.single_right_lay);
        }

    }

}
