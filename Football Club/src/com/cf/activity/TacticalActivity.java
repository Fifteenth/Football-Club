package com.cf.activity;

import com.android.club.R;
import com.cf.base.ConstantVariable;
import com.cf.dialog.FormationSelectedDialog;
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
        	
        setContentView(R.layout.activity_tactical);
        footballField=(FootballFieldView) this.findViewById(R.id.footballField);
    	footballField.setBackgroundDrawable(getResources().getDrawable(R.drawable.background_footballfield));
    	
//    	if(formation==null){
//    		formation = ConstantVariable.formation331;
//    	}
//    	footballField.initPlayersPosition("player",formation);
    }
    
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
  				FormationSelectedDialog.lastSelection = FormationSelectedDialog.DEFAULT_SELECTION;
  				FormationSelectedDialog formationSelectedDialog = 
  					new FormationSelectedDialog(this);
  				
  				formationSelectedDialog.show();
  				break;
  				
  				
  			default:
  				break;
  		}
  		return true;
  	}
  	
}