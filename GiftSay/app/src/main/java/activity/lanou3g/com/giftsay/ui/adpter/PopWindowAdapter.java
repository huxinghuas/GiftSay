package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.GuidBean;
import activity.lanou3g.com.giftsay.modle.InterFaces.PopWindowOnRvItemClick;

/**
 * Created by dllo on 16/9/27.
 */
public class PopWindowAdapter extends RecyclerView.Adapter<PopWindowAdapter.PopWindowViewHolder> {

    private List<GuidBean.DataBean.ChannelsBean> datas;
    private Context context;
    private int selectIndex;
    private PopWindowOnRvItemClick onRvItemClick;

    public PopWindowAdapter( Context context) {

        this.context = context;
    }

    public void setDatas(List<GuidBean.DataBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public void setOnRvItemClick(PopWindowOnRvItemClick onRvItemClick) {
        this.onRvItemClick = onRvItemClick;
    }

    public PopWindowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PopWindowViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_window, parent, false);
        holder = new PopWindowViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PopWindowViewHolder holder, final int position) {
        holder.popTv.setText(datas.get(position).getName());

        if (position == selectIndex) {

            holder.popTv.setTextColor(Color.RED);
            holder.views.setBackgroundColor(Color.RED);
        } else {
            holder.popTv.setTextColor(Color.BLACK);
            holder.views.setBackgroundColor(Color.parseColor("#dfdfdfdf"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRvItemClick != null) {
                    int p = holder.getLayoutPosition();
                    GuidBean.DataBean.ChannelsBean bean = datas.get(position);
                    onRvItemClick.PopWindowOnRvItemClikLinster(p, bean);

                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class PopWindowViewHolder extends RecyclerView.ViewHolder {
        private TextView popTv;
        private View views;

        public PopWindowViewHolder(View itemView) {
            super(itemView);
            popTv = (TextView) itemView.findViewById(R.id.pop_window_tv);
            views = (View) itemView.findViewById(R.id.pop_window_view);
        }
    }
}
