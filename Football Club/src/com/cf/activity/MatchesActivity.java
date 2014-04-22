package com.cf.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.android.club.R;
import com.cf.adapter.MatchesAdapter;
import com.cf.base.ConstantVariable;
import com.cf.dialog.FinanceDialog;
import com.cf.dialog.MatchDialog;
import com.cf.support.MatchesSupport;
import com.cf.to.MatchTO;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MatchesActivity extends Activity{
	

	private List <MatchTO> listMatchTO = new ArrayList<MatchTO>();
	private static int listIndex = -1;
	private ListView listViewMatch;
	private int layoutInt = R.layout.listview_matches;
	private MatchDialog matchDialog;
	
	public static int dialogType = 0;
	
	
	
	public static final int SHOW_DATAPICK = 0; 
	public static final int DATE_DIALOG_ID = 1;  
	public static final int SHOW_TIMEPICK = 2;
	public static final int TIME_DIALOG_ID = 3;
    
    private int mYear;  
    private int mMonth;
    private int mDay; 
    private int mHour;
    private int mMinute;

    
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_matches);
		
		matchDialog = new MatchDialog(this);
		matchDialog.setMatchDialog(matchDialog);

		listViewMatch = (ListView) this.findViewById(R.id.listView_my);
		listMatchTO = MatchesSupport.ReadMatches();
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
				// Dialog Set
				matchDialog.setEditTextRound(matchTO.getRound().trim());
				matchDialog.setEditTextScore(matchTO.getScore());
				matchDialog.setEditTextCompetitor(matchTO.getCompetitor());
				matchDialog.setEditTextDate(matchTO.getCompetitionDate());
				matchDialog.setEditTextTime(matchTO.getCompetitionTime());
				// Dialog Type
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
				matchDialog.setEditTextRound("");
				matchDialog.setEditTextScore("");
				matchDialog.setEditTextCompetitor("");
				dialogType = ConstantVariable.DIALOG_ADD;
			}
		});
		
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);  
        mMonth = c.get(Calendar.MONTH);  
        mDay = c.get(Calendar.DAY_OF_MONTH);
        
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        
        setDateTime(); 
        setTimeOfDay();
		
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
			case ConstantVariable.DIALOG_DELETE: {
				listMatchTO.remove(matchTO);
				break;
			}
		}
		// Set Default
		dialogType = ConstantVariable.DIALOG_DEFAULT;
		// Write
		MatchesSupport.WriteMatches(listMatchTO);
	}
	
	
    /**
     * 设置日期
     */
	private void setDateTime(){
       final Calendar c = Calendar.getInstance();  
       
       mYear = c.get(Calendar.YEAR);  
       mMonth = c.get(Calendar.MONTH);  
       mDay = c.get(Calendar.DAY_OF_MONTH); 
  
       updateDateDisplay(); 
	}
	
	/**
	 * 更新日期显示
	 */
	private void updateDateDisplay(){
		EditText editTextDate = matchDialog.getEditTextDate();
		if(editTextDate != null){
			editTextDate.setText(new StringBuilder().append(mYear).append("-")
					.append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
					.append((mDay < 10) ? "0" + mDay : mDay)); 
		}
       
	}
	
    /** 
     * 日期控件的事件 
     */  
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {  
  
       
    	@Override
    	public void onDateSet(DatePicker view, int year, int monthOfYear,  
              int dayOfMonth) {  
    		
			mYear = year;  
			mMonth = monthOfYear;  
			mDay = dayOfMonth;  
			updateDateDisplay();
    	}  
    }; 
	
	/**
	 * 设置时间
	 */
	private void setTimeOfDay(){
	   final Calendar c = Calendar.getInstance(); 
       mHour = c.get(Calendar.HOUR_OF_DAY);
       mMinute = c.get(Calendar.MINUTE);
       updateTimeDisplay();
	}
	
	/**
	 * 更新时间显示
	 */
	private void updateTimeDisplay(){
		EditText editTextTime = matchDialog.getEditTextTime();
		if(editTextTime != null){
			editTextTime.setText(new StringBuilder().append(mHour).append(":")
		               .append((mMinute < 10) ? "0" + mMinute : mMinute)); 
		}
	}
    
    /**
     * 时间控件事件
     */
    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateTimeDisplay();
		}
	};
    
    @Override  
    protected Dialog onCreateDialog(int id) {  
       switch (id) {  
       case DATE_DIALOG_ID:  
           return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,mDay);
       case TIME_DIALOG_ID:
    	   return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, true);
       }
       return null;  
    }  
  
    @Override  
    protected void onPrepareDialog(int id, Dialog dialog) {  
       switch (id) {  
       case DATE_DIALOG_ID:  
           ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);  
           break;
       case TIME_DIALOG_ID:
    	   ((TimePickerDialog) dialog).updateTime(mHour, mMinute);
    	   break;
       }
    } 
	
	
	/** 
     * 处理日期和时间控件的Handler 
     */  
    public Handler dateandtimeHandler = new Handler() {
  
       @Override  
       public void handleMessage(Message msg) {  
           switch (msg.what) {  
           case SHOW_DATAPICK:  
               showDialog(DATE_DIALOG_ID);
               break; 
           case SHOW_TIMEPICK:
        	   showDialog(TIME_DIALOG_ID);
        	   break;
           }  
       }  
    }; 
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    }
}
