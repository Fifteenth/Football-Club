package com.android.dialog;



import com.android.club.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
 

public class MatchDialog extends Dialog {

	public MatchDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
 
 
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		
		setContentView(R.layout.dialog_match);
		
	}
}
