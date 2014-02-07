package com.android.activity;

import com.android.club.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ClubActivity extends Activity {
	
	private Button btn_ai = null;
	private Button btn_pvp = null;
	private Button btn_about = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        btn_ai=(Button) this.findViewById(R.id.btn_ai);
        btn_ai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				new  AlertDialog.Builder(ClubActivity.this)    
//				    .setTitle("提示：" )
//				    .setMessage("此功能敬请期待!" )
//				    .setPositiveButton("确定" ,null).show();   
				
				Intent intent=new Intent(ClubActivity.this, NavigationActivit.class);			
				startActivity(intent);
			}
		});
        
        btn_pvp=(Button) this.findViewById(R.id.btn_pvp);
        btn_pvp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ClubActivity.this, StartActivity.class);
				intent.putExtra("type", "pvp");
				startActivity(intent);
			}
		});
        
        btn_about=(Button) this.findViewById(R.id.btn_about);
        btn_about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(ClubActivity.this, AboutActivity.class);			
				startActivity(intent);
			}
		});
    }
}