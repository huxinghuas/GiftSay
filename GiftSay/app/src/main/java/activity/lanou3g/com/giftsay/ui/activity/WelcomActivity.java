package activity.lanou3g.com.giftsay.ui.activity;

import android.os.AsyncTask;
import android.widget.TextView;

import activity.lanou3g.com.giftsay.R;

/**
 * Created by dllo on 16/9/10.
 * 欢迎页面
 */
public class WelcomActivity extends AbsBaseActivity {

    private TextView showTv;
    private WelcomTask  welcomTask;

    protected int setLayout() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initView() {

        showTv = byview(R.id.welcom_tv);
    }

    @Override
    protected void initDatas() {
        welcomTask = new WelcomTask();
        welcomTask.execute(0);


    }


    private  class  WelcomTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {

            int all = params[0]; //计数
            int now = 5;

            while (now > all ){
                publishProgress(now);
                now-- ;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            goTo(WelcomActivity.this,MainActivity.class);
            finish();
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            showTv.setText(values[0]+"");
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showTv.setText(s);
        }
    }


}
