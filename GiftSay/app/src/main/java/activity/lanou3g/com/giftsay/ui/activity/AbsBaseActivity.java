package activity.lanou3g.com.giftsay.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/8.
 */
public abstract class AbsBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置布局
        setContentView(setLayout());
        // 初始化组件
        initView();
        // 初始化数据
        initDatas();
    }

    /**
     * 设置布局文件
     * @return
     */

    protected abstract int setLayout();

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 跳转传值
     */

    protected  <T extends View> T byview(int resId){
       return  (T)findViewById(resId);
    }



    protected  void goTo(Context from,Class< ? extends  AbsBaseActivity> to,Bundle extras){
        startActivity(new Intent(from,to));
        Intent intent = new Intent(from,to);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * 跳转bu传值
     * @param from
     * @param to
     */

    protected  void goTo(Context from,Class< ? extends  AbsBaseActivity> to){
        startActivity(new Intent(from,to));
    }
}
