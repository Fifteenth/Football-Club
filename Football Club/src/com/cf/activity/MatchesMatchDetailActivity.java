package com.cf.activity;

import com.android.club.R;
import com.cf.base.ConstantVariable;
import com.cf.to.MatchTO;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MatchesMatchDetailActivity extends Activity{
	
	final public static int MATCH_STATE_UNPLAY = 0;
	final public static int MATCH_STATE_PLAYED = 1;
	
	public static MatchTO matchTO;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String score = matchTO.getScore();
		
//		if(score.contains(ConstantVariable.SYSBOL_COLON)){
			detailPlayed();
//		}else if(score.contains(ConstantVariable.SYSBOL_VS)){
//			detailUnplay();
//		}
		
		// Head
		TextView textViewCompetitor = (TextView) findViewById(R.id.textview_competitor);
		textViewCompetitor.setText(ConstantVariable.myTeam + score + matchTO.getCompetitor());
		
		// 
		TextView textViewDate = (TextView) findViewById(R.id.textview_date_time);
		textViewDate.setText(matchTO.getCompetitionDate() + 
				ConstantVariable.SYSBOL_SPAN_TWO + matchTO.getCompetitionTime());
	}
	
	public void detailPlayed(){
		setContentView(R.layout.activity_matches_match_detail_played);
		
		
	}
	
	public void detailUnplay(){
		setContentView(R.layout.activity_matches_match_detail_unplay);
	}

}
