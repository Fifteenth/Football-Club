package com.android.activity;

import com.android.club.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TeamIntroductionActivity extends Activity{
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.about);
	}
}



