package activity.lanou3g.com.giftsay.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by dllo on 16/9/13.
 * 获取手机屏幕高度或宽度的实体类
 */
public class ScreenSizeUtil {

    public static int getScreenWidth(Context context) {
        // 首先获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        // 将屏幕的尺寸设置给显示尺寸
        manager.getDefaultDisplay().getMetrics(metrics);
        // 返回屏幕宽度
        return metrics.widthPixels;
    }



    public static int getScreenHeight(Context context) {
        // 首先获取窗口管理者
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 创建显示尺寸类
        DisplayMetrics metrics = new DisplayMetrics();
        // 将屏幕的尺寸设置给显示尺寸
        manager.getDefaultDisplay().getMetrics(metrics);
        // 返回屏幕高度
        return metrics.heightPixels;
    }









   public  static  int getScreenSize(Context context,int state){
       WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
       DisplayMetrics metrics = new DisplayMetrics();
       manager.getDefaultDisplay().getMetrics(metrics);
       switch (state){
           case  1:
               return  metrics.widthPixels;

           case  2:
               return metrics.heightPixels;
           default:
               return metrics.heightPixels;
       }


   }
}
