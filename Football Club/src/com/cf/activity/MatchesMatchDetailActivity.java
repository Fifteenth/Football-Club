package com.cf.activity;

import com.android.club.R;

import android.app.Activity;
import android.os.Bundle;

public class MatchesMatchDetailActivity extends Activity{
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		detailPlayed();
		
	}
	
	public void detailPlayed(){
		setContentView(R.layout.activity_matches_match_detail_played);
		
		
	}
	
	public void detailUnplay(){
		setContentView(R.layout.activity_matches_match_detail_unplay);
	}

}
