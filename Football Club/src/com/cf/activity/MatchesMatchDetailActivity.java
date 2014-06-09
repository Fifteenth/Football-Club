package com.cf.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.club.R;
import com.cf.adapter.MatchesMatchDetailAdapter;
import com.cf.base.ConstantVariable;
import com.cf.to.MatchTO;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
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
		
		// Competitor
		TextView textViewCompetitor = (TextView) findViewById(R.id.textview_competitor);
		textViewCompetitor.setText(ConstantVariable.myTeam + score + matchTO.getCompetitor());
		
		// DateTime
		TextView textViewDate = (TextView) findViewById(R.id.textview_date_time);
		textViewDate.setText(
				"����ʱ��:  "+
				matchTO.getCompetitionDate() + 
				ConstantVariable.SYSBOL_SPAN_TWO + matchTO.getCompetitionTime());
	}
	
	public void detailPlayed(){
		setContentView(R.layout.activity_matches_match_detail_played);
		
		
		// Competition
		TextView textViewCompetitionList = (TextView) findViewById(
				R.id.textview_competition_list);
		textViewCompetitionList.setText(
				"(322):13-�󱣽�/6-��硢26-���硢28-����/16-�⾭��15-���塢" +
				"11-2��/14-�θ�");
		
		// ListView
		ListView listViewGoal = (ListView) this.findViewById(R.id.listview_goal);
		
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		MatchesMatchDetailAdapter adapter = new MatchesMatchDetailAdapter(this,list);
		
		listViewGoal.setAdapter(adapter);
	}
	
	public void detailUnplay(){
		setContentView(R.layout.activity_matches_match_detail_unplay);
	}

}
