package activity.lanou3g.com.giftsay.modle.InterFaces;

import activity.lanou3g.com.giftsay.modle.bean.DayRecomedBean;

/**
 * Created by dllo on 16/9/26.
 * 每日推荐行布局点击接口
 */
public interface DayRecomedOnRvItemClick {
    void  OnRvItemClickLisner(int position,DayRecomedBean.DataBean.ItemsBean bean);
}
