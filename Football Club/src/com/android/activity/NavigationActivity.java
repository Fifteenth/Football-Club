package com.android.activity;

import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.module.member.GalleryView;
import com.android.module.member.ImageAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationActivity extends Activity {

	private TextView tvTitle; 	
	private GalleryView gallery; 	
	private ImageAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_navigation);

		initRes();
	}
	
	private void initRes(){
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		gallery = (GalleryView) findViewById(R.id.mygallery);

		adapter = new ImageAdapter(this); 	
		adapter.createReflectedImages();	// ������ӰЧ��
		gallery.setAdapter(adapter);
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {	// ����ѡ���¼�����
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tvTitle.setText(ConstantVariable.titles[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {			// ���õ���¼�����
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Show Message
//				Toast.makeText(NavigationActivit.this, "img " + (position+1) 
//						+ " selected", Toast.LENGTH_SHORT).show();
			
				switch(position){
				case 0:
					Intent teamIntroductionActivity = new Intent(NavigationActivity.this, IntroductionActivity.class);
					startActivity(teamIntroductionActivity);
					break;
				case 1:
					Intent matchsActivity = new Intent(NavigationActivity.this, MatchesActivity.class);
					startActivity(matchsActivity);
					break;
				case 2:
					Intent team = new Intent(NavigationActivity.this, TeamActivity.class);
					startActivity(team);
					break;
				case 3:
					Intent startActivity = new Intent(NavigationActivity.this, TacticalActivity.class);
					FormationActivity.startActivity = startActivity;
					startActivity(startActivity);
					break;
				case 4:
					new  AlertDialog.Builder(NavigationActivity.this)    
				    .setTitle("��ʾ��" )
				    .setMessage("ת���г��ѹر�!" )
				    .setPositiveButton("ȷ��" ,null).show(); 
					break;
				case 5:
					Intent financeActivity = new Intent(NavigationActivity.this,FinanceActivity.class);
					startActivity(financeActivity);
					break;
				case 6:
					Intent managementActivity = new Intent(NavigationActivity.this,ManagementActivity.class);
					startActivity(managementActivity);
					break;
				
				default:
					new  AlertDialog.Builder(NavigationActivity.this)    
				    .setTitle("��ʾ��" )
				    .setMessage("�˹��ܾ����ڴ�!" )
				    .setPositiveButton("ȷ��" ,null).show(); 
					break;
				}
			}
		});
	}
}