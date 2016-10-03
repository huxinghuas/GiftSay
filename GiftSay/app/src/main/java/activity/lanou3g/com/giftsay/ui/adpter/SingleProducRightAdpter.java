package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.SingleProductBean;
import activity.lanou3g.com.giftsay.ui.activity.SingleProductInfoActivity;
import activity.lanou3g.com.giftsay.view.MyGridView;

/**
 * Created by dllo on 16/9/21.
 * 单品右边列表的适配器
 */
public class SingleProducRightAdpter extends BaseAdapter {

    List<SingleProductBean.DataBean.CategoriesBean> datas;
    private Context context;
    private  int selectIndex;

    private List<SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> list;


    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
            notifyDataSetChanged();
    }

    public SingleProducRightAdpter(List<SingleProductBean.DataBean.CategoriesBean> datas, Context context
                               ) {

        this.datas = datas;
        this.context = context;

    }

    public void setList(List<SingleProductBean.DataBean.CategoriesBean.SubcategoriesBean> list) {
        this.list = list;
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

        SingleRightViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_single_right_lv, parent, false);
            holder = new SingleRightViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (SingleRightViewHolder) convertView.getTag();
        }

        SingleProductBean.DataBean.CategoriesBean bean = datas.get(position);

        if (bean.getName().equals("热门分类")){
            holder.titleTv.setText("");
            holder.views.setBackgroundColor(Color.WHITE);
        }else {
            holder.titleTv.setText(bean.getName());
            holder.views.setBackgroundColor(Color.parseColor("#dfdfdfdf"));
        }

   ;
        list = bean.getSubcategories();
        holder.adpter.setDatas(list);
       holder.gridView.setAdapter(holder.adpter);

        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, SingleProductInfoActivity.class);
               context.startActivity(intent);
            }
        });

        return convertView;
    }


    public  void setIndex(int index){
        selectIndex = index;
    }


    class SingleRightViewHolder {

        private TextView titleTv;
        private GridView gridView;
        private View views;
        SingleGrideAdpter adpter;

        public SingleRightViewHolder(View v) {
            adpter = new SingleGrideAdpter(context);
            titleTv = (TextView) v.findViewById(R.id.single_right_title_tv);
            gridView = (MyGridView) v.findViewById(R.id.single_right_lv_gride);
            views = (View) v.findViewById(R.id.single_right_view);
        }

    }

}
