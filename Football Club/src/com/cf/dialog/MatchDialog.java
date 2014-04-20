package com.cf.dialog;



import com.android.club.R;
import com.cf.activity.MatchesActivity;
import com.cf.base.ConstantVariable;
import com.cf.service.BuildTOService;
import com.cf.to.MatchTO;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 

public class MatchDialog extends Dialog {
	
	// Class Path
	final public static String classPath = MatchDialog.class.getName();

	// EditText
	private EditText editTextRound;
	private EditText editTextScore;
	private EditText editTextCompetitor;
	public EditText editTextDate = null;  
	public EditText editTextTime = null; 
	
	
	
	private MatchDialog matchDialog;
	private MatchesActivity mathchesActivity;
	
	public static Button pickDate = null;  
	public static Button pickTime = null; 
	
	
	public EditText getEditTextRound() {
		return editTextRound;
	}

	public EditText getEditTextScore() {
		return editTextScore;
	}

	public EditText getEditTextCompetitor() {
		return editTextCompetitor;
	}
	
	public EditText getEditTextDate() {
		return editTextDate;
	}
	
	public EditText getEditTextTime() {
		return editTextTime;
	}
	
	
	public void setEditTextRound(String round) {
		this.editTextRound.setText(round);
	}

	public void setEditTextScore(String score) {
		this.editTextScore.setText(score);
	}

	public void setEditTextCompetitor(String competitor) {
		this.editTextCompetitor.setText(competitor);
	}
	
	public void setEditTextDate(EditText editTextDate) {
		this.editTextDate = editTextDate;
	}

	public void setEditTextTime(EditText editTextTime) {
		this.editTextTime = editTextTime;
	}
	
	
	
	public MatchDialog getMatchDialog() {
		return matchDialog;
	}

	public void setMatchDialog(MatchDialog matchDialog) {
		this.matchDialog = matchDialog;
	}
	
	
	public MatchDialog(Context context) {
		super(context);
		mathchesActivity = (MatchesActivity) context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_match);

		Button buttonConfirmPayment = (Button) findViewById(R.id.button_confirm_payment);
		
		editTextRound = (EditText)findViewById(R.id.edit_round);
		editTextScore = (EditText)findViewById(R.id.edit_score);
		editTextCompetitor = (EditText)findViewById(R.id.edit_competitor);
		
		buttonConfirmPayment.setOnClickListener(clickListener);
		
		editTextDate = (EditText) findViewById(R.id.showdate);  
		editTextTime = (EditText) findViewById(R.id.showtime);
        pickDate = (Button) findViewById(R.id.pickdate); 
        pickTime = (Button) findViewById(R.id.picktime);
        
        pickDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	           Message msg = new Message(); 
	           if (pickDate.equals((Button) v)) {  
	              msg.what = MatchesActivity.SHOW_DATAPICK;  
	           }  
	           mathchesActivity.dateandtimeHandler.sendMessage(msg); 
	           
	           MatchesActivity.onSetDateTimes = 0;
			}
		});
        
        pickTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
	           Message msg = new Message(); 
	           if (pickTime.equals((Button) v)) {  
	              msg.what = MatchesActivity.SHOW_TIMEPICK;  
	           }  
	           mathchesActivity.dateandtimeHandler.sendMessage(msg); 
	           
	           MatchesActivity.onSetTimeTimes = 0;
			}
		});
	}
	
	private View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View arg0) {
			MatchTO matchTO = (MatchTO)BuildTOService.buildTO(
					MatchDialog.classPath,MatchTO.classPath,matchDialog);
			mathchesActivity.back(matchTO);
			MatchDialog.this.dismiss();
			mathchesActivity.onCreate(null);
		}
	};
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}