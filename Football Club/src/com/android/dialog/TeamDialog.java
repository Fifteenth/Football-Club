package com.android.dialog;



import com.android.activity.TeamActivity;
import com.android.club.R;
import com.android.service.BuildTOService;
import com.android.to.PlayerTO;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 

public class TeamDialog extends Dialog {
	
	final public static String classPath = "com.android.dialog.TeamDialog";

	// EditText
	private EditText editTextNumber;
	private EditText editTextName;
	private EditText editTextPosition;
	
	private TeamDialog teamDialog;
	private TeamActivity teamActivity;
	
	public EditText getEditTextNumber() {
		return editTextNumber;
	}

	public EditText getEditTextName() {
		return editTextName;
	}

	public EditText getEditTextPosition() {
		return editTextPosition;
	}
	
	
	public void setEditTextNumber(String number) {
		this.editTextNumber.setText(number);
	}

	public void setEditTextName(String name) {
		this.editTextName.setText(name);
	}

	public void setEditTextPosition(String position) {
		this.editTextPosition.setText(position);
	}
	
	
	public TeamDialog getTeamDialog() {
		return teamDialog;
	}

	public void setTeamDialog(TeamDialog teamDialog) {
		this.teamDialog = teamDialog;
	}
	
	
	public TeamDialog(Context context) {
		super(context);
		teamActivity = (TeamActivity) context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_team);
		
		Button button_confirm_payment = (Button) findViewById(R.id.button_confirm_payment);
		
		editTextNumber = (EditText)findViewById(R.id.edit_number);
		editTextPosition = (EditText)findViewById(R.id.edit_position);
		editTextName = (EditText)findViewById(R.id.edit_name);
		
		button_confirm_payment.setOnClickListener(clickListener);
	}
	
	private View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View arg0) {
			PlayerTO playerTO = (PlayerTO)BuildTOService.buildTO(
					TeamDialog.classPath,PlayerTO.classPath,teamDialog);
			teamActivity.back(playerTO);
			TeamDialog.this.dismiss();
			teamActivity.onCreate(null);
		}
	};
}
