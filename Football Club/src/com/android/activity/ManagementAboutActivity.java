package com.android.activity;

import com.android.club.R;

import android.app.Activity;
//import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class ManagementAboutActivity extends Activity {
	
	
	public static String about = "";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_management_about);
		
		
		TextView tv_1 = (TextView) findViewById(R.id.title);
		tv_1.setText(about);
	
	}
	
	
}