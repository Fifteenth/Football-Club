package com.cf.activity;

import com.android.club.R;
import com.cf.base.ConstantVariable;
import com.cf.view.FootballFieldView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class TacticalActivity extends Activity {
	
	public FootballFieldView footballField;
	public static String formation;
	
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ws", "StartActivity-onCreate");
        	
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tactical);
        footballField=(FootballFieldView) this.findViewById(R.id.footballField);
    	footballField.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_footballfield));
    	
    	if(formation==null){
    		formation = ConstantVariable.formation331;
    	}
    	footballField.initPlayersPosition("player",formation);
    }
    
    //创建Menu
  	@Override
  	public boolean onCreateOptionsMenu(Menu menu) {
  		super.onCreateOptionsMenu(menu);
  		menu.add(0, 11, 0, ConstantVariable.menuStartDes); 
  		menu.add(0, 12, 1, ConstantVariable.menuPauseDes); 
  		menu.add(0, 13, 1, ConstantVariable.menuStopDes); 
  		menu.add(1, 21, 1, ConstantVariable.menuTacticalDisplayDes); 
  		menu.add(1, 22, 2, ConstantVariable.menuFormationDes); 
  		return true;
  	}
  	
  	//Menu点击事件
  	@Override
  	public boolean onOptionsItemSelected(MenuItem item) {
  		
  		switch (item.getItemId()){
  			case 11:
  				footballField.setShowDisplay(ConstantVariable.menuStart);
  				break;
  				
  			case 12:
  				footballField.setShowDisplay(ConstantVariable.menuPause);
  				break;
  			case 13:
  				footballField.setShowDisplay(ConstantVariable.menuStop);
  				break;
  				
  			case 21:
  				footballField.displayTactics();
	  			break;
  				
  			case 22:
  				Intent formationActivity = null;
  				if(FormationActivity.formationActivity==null){
  					formationActivity = new Intent(TacticalActivity.this, FormationActivity.class);
  					FormationActivity.formationActivity = formationActivity;
  				}else{
  					formationActivity = FormationActivity.formationActivity;
  				}
  				
  				// Spinner Activerd
  				FormationActivity.callOnItemSelectedType = FormationActivity.CALL_ON_ITEM_SELECTED_TYPE_SPINNER_ACTIVED;
  				
  				// FormationActivity
  				formationActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); 
  				startActivity(formationActivity);
  				break;
  				
  				
  			default:
  				break;
  		}
  		return true;
  	}
  	
  /*	@Override
	protected void onStart() {
		super.onStart();
		
		//注册广播
		IntentFilter filter = new IntentFilter();
        filter.addAction(GAME_OVER);
//		registerReceiver(changeItem, filter);
	}*/
	
	/*@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销广播
		unregisterReceiver(changeItem);
	}*/
  	
    // 确定是退出还是继续
 	/*@Override
 	public boolean onKeyDown(int keyCode, KeyEvent event) {
 		
 		if (keyCode == KeyEvent.KEYCODE_BACK) {
 		}
 		return super.onKeyDown(keyCode, event);
 	}*/
 	
 	//广播
 /*	private BroadcastReceiver changeItem = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
		
			if (intent.getAction().equals(GAME_OVER)){				
				finish();				
			}
		}
 		
 	};*/
 	
 	
 	
 	
}