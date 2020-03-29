package com.example.t2016_11_2_4mychartview;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.t2016_11_2_4mychartview.MyChartView.Mstyle;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.widget.TextView;
/* * @author 王树伟
 * 
 * 曲线图 mychartview是提供的
 */
public class MainActivity extends Activity {
	private TextView humid,temp;
	private MyChartView myChartView;
	private HashMap<Double, Double> map ;
	private List<Double> list;
	private Handler handler = null; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handler = new Handler();
        handler.post(runnable);
    }
    Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.postDelayed(runnable, 2000);
			Random random = new Random();
			int parseInt = Integer.parseInt(temp.getText().toString());
			list.add((double) parseInt);
			for(int i=list.size();i>0;i--){
//				Toast.makeText(getApplicationContext(), list.get(i-1)+"", 0).show();
				System.out.println("");
				map.put((double) i, list.get(i-1));
			}
			if(list.size()>=7){
				list.remove(0);
			}
			myChartView.postInvalidate();
		}
	};
	private void initView() {
		// TODO Auto-generated method stub
		humid = (TextView) findViewById(R.id.humid);
		temp = (TextView) findViewById(R.id.temp);
		myChartView = (MyChartView) findViewById(R.id.myCharview);
		myChartView.SetTuView(map, 30, 10, "ms", "℃", false);
		myChartView.setTotalvalue(30);
		myChartView.setPjvalue(3);
		myChartView.setMstyle(Mstyle.Curve);
		map = new HashMap<Double, Double>();
		myChartView.setMap(map);
		list = new ArrayList<Double>(7);	
	}
}