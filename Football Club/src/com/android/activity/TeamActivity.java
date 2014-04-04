package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.activity.support.TeamActivitySupport;
import com.android.adapter.TeamAdapter;
import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.dialog.TeamDialog;
import com.android.to.MatchTO;
import com.android.to.TeamTO;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * 自定义ListAdapter
 * 
 * @author Administrator
 * 
 */
public class TeamActivity extends Activity {

	private List<TeamTO> listTeamTO = new ArrayList<TeamTO>();
	private static int listIndex = -1;
	private ListView listViewTeam;
	private int layoutInt = R.layout.listview_team;
	private TeamDialog teamDialog;
	int dialogType = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_team);
		
		teamDialog = new TeamDialog(this);
		teamDialog.setTeamDialog(teamDialog);
		
		Resources resources = this.getResources();
		listViewTeam = (ListView) this.findViewById(R.id.listView_my);
		listTeamTO = TeamActivitySupport.ReadTeam();
		// 实例化自定义适配器
		TeamAdapter adapter = new TeamAdapter(this,listTeamTO,layoutInt,resources);
		if(listTeamTO.size()>0){
			listViewTeam.setAdapter(adapter);
		}
		listViewTeam.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				listViewTeam.setItemChecked(position, true);
			}
		});
	
		listViewTeam.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int listIndex, long arg3) {
				TeamActivity.listIndex = listIndex;
				teamDialog.show();
				TeamTO teamTO = listTeamTO.get(listIndex);
				teamDialog.setEditTextNumber(teamTO.getNumber());
				teamDialog.setEditTextPosition(teamTO.getPosition());
				teamDialog.setEditTextName(teamTO.getName());
				dialogType = ConstantVariable.DIALOG_UPDATE;
				return false;
			}
		});
		
		
		// Add Match
		Button buttonMatchAdd = (Button) findViewById(R.id.button_team_add);
		buttonMatchAdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				teamDialog.show();
				teamDialog.setEditTextNumber("");
				teamDialog.setEditTextPosition("");
				teamDialog.setEditTextName("");
				dialogType = ConstantVariable.DIALOG_ADD;
			}
		});
	}
	
	
	// Dialog Back
	public void back(TeamTO teamTO) {
		switch (dialogType) {
		case ConstantVariable.DIALOG_ADD: {
			listTeamTO.add(teamTO);
			break;
		}
		case ConstantVariable.DIALOG_UPDATE: {
			listTeamTO.set(listIndex, teamTO);
			break;
		}
		}
		// Set Default
		dialogType = ConstantVariable.DIALOG_DEFAULT;
		// Write
		TeamActivitySupport.WriteTeam(listTeamTO);
	}

}
