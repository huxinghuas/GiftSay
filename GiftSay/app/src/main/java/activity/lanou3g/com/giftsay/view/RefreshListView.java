package activity.lanou3g.com.giftsay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import activity.lanou3g.com.giftsay.R;
import activity.lanou3g.com.giftsay.modle.InterFaces.OnRefreshListener;
import activity.lanou3g.com.giftsay.modle.net.VolleyResult;

/**
 * Created by dllo on 16/9/28.
 * 自定义下拉刷新
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {
    private View headerView; // 头布局对象
    private int firstVisibleItemPosition;//屏幕显示在第一个的item的索引
    private int headerViewHeight; // 头布局高度
    private ProgressBar mprogressBar;//头布局的进度条
    private Animation upAnimation; // 向上旋转的动画;
    private Animation downAnimation;//向下旋转的动画
  //  private ImageView ivArrow; // 头布局的图片
    private int downY;//按下时y轴的偏移量
    private final int DOWN_PULL_REFRESH = 0;//下拉刷新状态
    private final int RELEASE_REFRESH = 1;//松开刷新
    private final int REFRESHING = 2;//正在刷新中
    private int currentState = DOWN_PULL_REFRESH;//头布局状态:默认为下拉刷新状态
    private OnRefreshListener mOnRefreshListener;//需要自定义一个接口
    private boolean isScrollToBottom;//是否滑动到底部
    private boolean isLoadingMore = false;//是否正在加载更多中

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        this.setOnScrollListener(this);
    }


    /*
  初始化头布局
   */
    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.selective_refresh_headview, null);
        mprogressBar = (ProgressBar) headerView.findViewById(R.id.pb_listview_header);
     //   ivArrow = (ImageView) headerView.findViewById(R.id.img_listview_header);
        headerView.measure(0, 0);//系统会帮我们测量出headerView的高度
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        this.addHeaderView(headerView);
        initAnimation();

    }

    private void initAnimation() {
        upAnimation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);//动画结束后,停留在结束的位置上
        downAnimation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);//动画结束后,停留在结束的位置上

    }


    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                //移动中的y -按下的y=间距.
                int diff = (moveY - downY) / 2;
                //-头布局的高度 + 间距 = paddingTop
                int paddingTop = -headerViewHeight + diff;
                //如果: - 头布局的高度>paddingTop的值 执行super.onTouchEvent(ev);
                if (firstVisibleItemPosition == 0 && -headerViewHeight < paddingTop) {
                    if (paddingTop > 0 && currentState == DOWN_PULL_REFRESH) {//完全显示了
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {//没有显示完全
                        currentState = DOWN_PULL_REFRESH;
                        refreshHeaderView();
                    }
                    //下拉头布局
                    headerView.setPadding(0, paddingTop, 0, 0);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //判断当前的状态是松开刷新还是下拉刷新
                if (currentState == RELEASE_REFRESH) {
                    //把头布局设置为完全显示状态
                    headerView.setPadding(0, 0, 0, 0);
                    //进入到正在刷新中状态
                    currentState = REFRESHING;
                    refreshHeaderView();
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onDownPullRefresh();//调用使用者的监听方法
                    }
                } else if (currentState == DOWN_PULL_REFRESH) {
                    //隐藏头布局
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    /*
   根据currentState刷新头布局的状态
    */
    private void refreshHeaderView() {
        switch (currentState) {
            case DOWN_PULL_REFRESH://下拉刷新状态
                break;
            case RELEASE_REFRESH://松开刷新状态
                break;
            case REFRESHING://正在刷新中状态
                mprogressBar.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        firstVisibleItemPosition = firstVisibleItem;
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }


    /*
       设置刷新监听事件
       @param listener
        */
    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    /*
    隐藏头布局
     */
    public void hideHeaderView() {
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        mprogressBar.setVisibility(View.GONE);
        currentState = DOWN_PULL_REFRESH;
    }


}
