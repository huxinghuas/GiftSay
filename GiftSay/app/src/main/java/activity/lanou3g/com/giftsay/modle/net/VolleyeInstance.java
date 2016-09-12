package activity.lanou3g.com.giftsay.modle.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.ref.ReferenceQueue;

import activity.lanou3g.com.giftsay.ui.app.GiftSayApp;

/**
 * Created by dllo on 16/9/10.
 * 获取网路连接单例类
 */
public class VolleyeInstance {


    private static VolleyeInstance instance;
    // 请求队列
    private RequestQueue requestQueue;

    // 私有的构造方法

    private VolleyeInstance() {
        requestQueue = Volley.newRequestQueue(GiftSayApp.getContext());

    }

    public static VolleyeInstance getInstance() {
        if (instance == null) {
            // 全部线程同步扫描
            synchronized (VolleyeInstance.class) {
                // 如果对象还是空
                if (instance == null) {
                    instance = new VolleyeInstance();
                }
            }

        }
        // 如果查询到有这么一个对象,直接返回这个对象
        return instance;
    }

    // 对外提供方法

    /**
     * 方法中的两个参数
     * 参数1 获取 url
     * 参数2 请求结果
     *      1.成功的方法
     *      2.失败的方法
     * @param url
     * @param result
     */

    public  void  startRequest(String url,final VolleyResult result){
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                result.success(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                result.failure();
            }
        });

            requestQueue.add(sr);

    }



}



