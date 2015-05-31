package com.af.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.af.adapter.LeagueAdapter;
import com.android.club.R;
import com.ff.widgets.WaitingPointBar;
import com.http.HttpCilent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

public class LeagueActivity extends Activity{
	

	private ListView listView;
	private List<JSONObject> list = new ArrayList<JSONObject>();
	private LeagueAdapter adapter;
	private UIHandler uiHandler;
	private WaitingPointBar waitingPointBar;
	
    
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_league);
		listView = (ListView) this.findViewById(R.id.listview_league);
		adapter = new LeagueAdapter(this,list);
		uiHandler = new UIHandler();
		
		waitingPointBar = (WaitingPointBar) findViewById(R.id.waitingPointBar);
		// 请求数据
		new Thread(){

			@Override
			 public void run(){
				HttpCilent httpCilent = new HttpCilent();
				httpCilent.setUrl("http://5522s.cn/league/getLeague");
				try{
					String httpResult = httpCilent.HttpGet();
					
					try {
						JSONArray jsonArray = new JSONArray(httpResult);
						for(int i=0;i<jsonArray.length();i++){
							list.add((JSONObject)jsonArray.get(i));
						}
						if(list.size() > 0){
							uiHandler.sendEmptyMessage(0);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				catch(Exception e){
					
				}
			 }
		}.start();;
	}
	
	
	
	class UIHandler extends Handler{
		
		@Override
        public void handleMessage(Message msg) {
//			super.handleMessage(msg);
			// 刷新列表
			listView.setAdapter(adapter);
			// 隐藏
			waitingPointBar.setVisibility(View.GONE);
		}
	}
}
