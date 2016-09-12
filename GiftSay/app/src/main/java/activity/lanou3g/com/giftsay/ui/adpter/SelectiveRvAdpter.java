package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import activity.lanou3g.com.giftsay.R;


/**
 * Created by dllo on 16/9/12.
 * 首页->精品->recycleview-->适配器
 */
public class SelectiveRvAdpter  extends  RecyclerView.Adapter<SelectiveRvAdpter.SelectiveRvHolder> {

    private List<Integer> datas;
    private Context context;

    public SelectiveRvAdpter(List<Integer> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public SelectiveRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_selective_recycleview, parent, false);
        SelectiveRvHolder holder = new SelectiveRvHolder(view);
        return holder;

    }


    @Override
    public void onBindViewHolder(SelectiveRvHolder holder, int position) {
          holder.img.setImageResource(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class SelectiveRvHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        public SelectiveRvHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.item_selective_img);

        }


    }
}







