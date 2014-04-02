package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.activity.support.MatchesActivitySupport;
import com.android.adapter.MatchesAdapter;
import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.dialog.FinanceDialog;
import com.android.dialog.MatchDialog;
import com.android.to.MatchTO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MatchesActivity extends Activity{
	

	private List <MatchTO> listMatchTO = new ArrayList<MatchTO>();
	private static int listIndex = -1;
	private ListView listViewMatch;
	private int layoutInt = R.layout.listview_matches;
	private MatchDialog matchDialog;
	int dialogType = 0;

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_matchs);
		
		matchDialog = new MatchDialog(this);
		matchDialog.setMatchDialog(matchDialog);

		listViewMatch = (ListView) this.findViewById(R.id.listView_my);
		
		listMatchTO = MatchesActivitySupport.ReadMatches();
		// 实例化自定义适配器
		MatchesAdapter adapter = new MatchesAdapter(this,listMatchTO,layoutInt);

		if(listMatchTO.size() > 0){
			listViewMatch.setAdapter(adapter);
		}
		

		listViewMatch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				 * API
				 * 
				 * Toast.makeText(TeamActivity.this, "选中的是:" + position,
				 *		Toast.LENGTH_SHORT).show();*/
				
				listViewMatch.setItemChecked(position, true);
			}
		});
		
		
		listViewMatch.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int listIndex, long arg3) {
				MatchesActivity.listIndex = listIndex;
				matchDialog.show();
				MatchTO matchTO = listMatchTO.get(listIndex);
				matchDialog.setEditTextRound(matchTO.getRound());
				matchDialog.setEditTextScore(matchTO.getScore());
				matchDialog.setEditTextCompetitor(matchTO.getCompetitor());
				dialogType = ConstantVariable.DIALOG_UPDATE;
				return false;
			}
		});
		
		
		
		// Add Match
		Button buttonMatchAdd = (Button) findViewById(R.id.button_matchAdd);
		buttonMatchAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				matchDialog.show();
				dialogType = ConstantVariable.DIALOG_ADD;
			}
		});
		
	}
	
	// Dialog Back
	public void back(MatchTO matchTO) {
		switch (dialogType) {
		case ConstantVariable.DIALOG_ADD: {
			listMatchTO.add(matchTO);
			break;
		}
		case ConstantVariable.DIALOG_UPDATE: {
			listMatchTO.set(listIndex, matchTO);
			break;
		}
		}
		// Set Default
		dialogType = ConstantVariable.DIALOG_DEFAULT;
		// Write
		MatchesActivitySupport.WriteMatches(listMatchTO);
	}
	
	
}
