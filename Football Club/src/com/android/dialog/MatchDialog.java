package com.android.dialog;



import com.android.activity.MatchesActivity;
import com.android.club.R;
import com.android.service.BuildTOService;
import com.android.to.MatchTO;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 

public class MatchDialog extends Dialog {
	
	final public static String classPath = "com.android.dialog.MatchDialog";

	// EditText
	private EditText editTextRound;
	private EditText editTextScore;
	private EditText editTextCompetitor;
	
	private MatchDialog matchDialog;
	private MatchesActivity mathchesActivity;
	
	public EditText getEditTextRound() {
		return editTextRound;
	}

	public EditText getEditTextScore() {
		return editTextScore;
	}

	public EditText getEditTextCompetitor() {
		return editTextCompetitor;
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
		
		Button button_confirm_payment = (Button) findViewById(R.id.button_confirm_payment);
		
		editTextRound = (EditText)findViewById(R.id.edit_round);
		editTextScore = (EditText)findViewById(R.id.edit_score);
		editTextCompetitor = (EditText)findViewById(R.id.edit_competitor);
		
		button_confirm_payment.setOnClickListener(clickListener);
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
