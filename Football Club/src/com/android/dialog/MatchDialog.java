package com.android.dialog;



import com.android.activity.MatchesActivity;
import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.service.BuildTOService;
import com.android.to.MatchTO;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 

public class MatchDialog extends Dialog {
	
	final public static String classPath = "com.android.dialog.MatchDialog";

	// EditText
	private EditText editTextRound;
	private EditText editTextScore;
	private EditText editTextCompetitor;
	public static EditText editTextDate = null;  
	public static EditText editTextTime = null; 
	
	
	
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
	
	public static EditText getEditTextDate() {
		return editTextDate;
	}
	
	public static EditText getEditTextTime() {
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
	
	public static void setEditTextDate(EditText editTextDate) {
		MatchDialog.editTextDate = editTextDate;
	}

	public static void setEditTextTime(EditText editTextTime) {
		MatchDialog.editTextTime = editTextTime;
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
			}
		});
        
        // Delete
        Button buttonConfirmRecover = (Button) findViewById(R.id.button_recover);
        buttonConfirmRecover.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View arg0) {
    			MatchesActivity.dialogType = ConstantVariable.DIALOG_DELETE;
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
}
