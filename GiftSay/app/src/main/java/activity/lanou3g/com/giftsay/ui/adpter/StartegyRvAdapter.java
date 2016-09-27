package activity.lanou3g.com.giftsay.ui.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.bean.StartegyRvBean;
import activity.lanou3g.com.giftsay.tools.ScreenSizeUtil;

/**
 * Created by dllo on 16/9/16.
 * 攻略页面RV适配器
 */
public class StartegyRvAdapter extends RecyclerView.Adapter {


    private Context context;
    private List<StartegyRvBean.DataBean.ColumnsBean> datas;
    private static final int TYPE_ONE_TV = 1;// 一张图片
    private static final int TYPE_LIST = 0; // 列表数据


    public StartegyRvAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<StartegyRvBean.DataBean.ColumnsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_LIST:
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.item_startegy_rv, parent, false);
                float width = ScreenSizeUtil.getScreenWidth(context);
                float height = ScreenSizeUtil.getScreenHeight(context);
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = (int) (height / 7.5);
                params.width = (int) (width / 1.5);
                view.setLayoutParams(params);
                MyViewHodler hodler = new MyViewHodler(view);
                return hodler;

            case TYPE_ONE_TV:
                LayoutInflater inflaterTv = LayoutInflater.from(context);
                View viewTv = inflaterTv.inflate(R.layout.item_startgy_one, parent, false);
                OneTVgHolder tvHolder = new OneTVgHolder(viewTv);
                return tvHolder;
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case TYPE_LIST:
                MyViewHodler hodlers = (MyViewHodler) holder;
                StartegyRvBean.DataBean.ColumnsBean bean = datas.get(position);
                hodlers.contentTv.setText(bean.getSubtitle());
                hodlers.titleTv.setText(bean.getTitle());
                hodlers.authorTv.setText(bean.getAuthor());
                Log.d("StartegyRvAdaptersds", bean.getBanner_image_url());
                Picasso.with(context).load(bean.getBanner_image_url()).into(hodlers.img);
                float width = ScreenSizeUtil.getScreenWidth(context);
                float height = ScreenSizeUtil.getScreenHeight(context);
                ViewGroup.LayoutParams params = hodlers.img.getLayoutParams();
                params.width = (int) (width / 3.1);
                hodlers.img.setLayoutParams(params);
                break;

            case TYPE_ONE_TV:
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return TYPE_ONE_TV;
        } else {
            return TYPE_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size() + 1;
    }


    class OneTVgHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public OneTVgHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.startegy__clik_all_tv);
        }
    }


    class MyViewHodler extends RecyclerView.ViewHolder {
        private TextView contentTv, titleTv, authorTv;
        private ImageView img;

        public MyViewHodler(View itemView) {
            super(itemView);

            contentTv = (TextView) itemView.findViewById(R.id.item_startegy_contact_tv);
            titleTv = (TextView) itemView.findViewById(R.id.item_startegy_title_tv);
            authorTv = (TextView) itemView.findViewById(R.id.item_startegy_author_tv);
            img = (ImageView) itemView.findViewById(R.id.item_startegy_img);

        }
    }

}
