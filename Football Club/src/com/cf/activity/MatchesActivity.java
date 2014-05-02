package com.cf.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.android.club.R;
import com.cf.adapter.MatchesAdapter;
import com.cf.base.ConstantVariable;
import com.cf.dialog.MatchDialog;
import com.cf.support.MatchesSupport;
import com.cf.to.MatchTO;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MatchesActivity extends Activity{
	

	private List <MatchTO> listMatchTO = new ArrayList<MatchTO>();
	private static int listIndex = -1;
	private ListView listViewMatch;
	private int layoutInt = R.layout.listview_matches;
	private MatchDialog matchDialog;
	private MatchesAdapter adapter;
	
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

    
    private float listviewTouchStartX = -1;
    private boolean isTouchMove = false;
    private boolean isTouchMoveFlag = false;
    private boolean isAvoidResponseOnLongClick = false;
    private View currentItemView;
    
    private RelativeLayout rl_right;
    
    
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_matches);
		
		matchDialog = new MatchDialog(this);
		matchDialog.setMatchDialog(matchDialog);

		listViewMatch = (ListView) this.findViewById(R.id.listView_my);
		listMatchTO = MatchesSupport.ReadMatches();
		
		if(listMatchTO == null){
			listMatchTO = new ArrayList();
		}
		// ʵ�����Զ���������
		adapter = new MatchesAdapter(this,listMatchTO,layoutInt);
		if(listMatchTO.size() > 0){
			listViewMatch.setAdapter(adapter);
		}
		
		listViewMatch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if(isTouchMoveFlag == true){
					// Set Init State
					isTouchMoveFlag = false;
					// 
					Button button = adapter.getButton();
					if(button != null){
						adapter.getButton().setBackgroundResource(R.drawable.button);
					}
					return;
				}
				
				/*
				 * API
				 * 
				 * Toast.makeText(TeamActivity.this, "ѡ�е���:" + position,
				 *		Toast.LENGTH_SHORT).show();*/
				
				listViewMatch.setItemChecked(position, true);
			}
		});
		
		
		listViewMatch.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int listIndex, long arg3) {
				
				// Wish To Flicker
				listViewMatch.setSelector(android.R.color.transparent);
				
				// Intercept
				if(isAvoidResponseOnLongClick){
					// Set Init State
					isAvoidResponseOnLongClick = false;
					return true;
				}

				// Intercept
				if(isAvoidResponseOnLongClick){
					// Set Init State
					isAvoidResponseOnLongClick = false;
					return true;
				}
				
				// Set Init State
				isTouchMoveFlag = false;
				
				//// 
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
				return true;
			}
		});
		
		
		listViewMatch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				boolean returnFlag = false;
				float currentViewX = event.getX();
				
				
				
				// Default Selector
				if(!isTouchMove){
					listViewMatch.setSelector(R.color.blue);
				}
				
				if(listviewTouchStartX == -1){
					// Init
					listviewTouchStartX = currentViewX;
				}
				
				System.out.println("listviewTouchStartX:" + listviewTouchStartX
						+ "----" + "currentViewX:" + currentViewX);
				
				if(listviewTouchStartX - currentViewX > 4){
					showRight(currentItemView);
				}
				if(currentViewX - listviewTouchStartX > 4){
					hiddenRight(currentItemView);
				}
				

				if(event.getAction() == MotionEvent.ACTION_DOWN){
					System.out.println("++++++++++++++++++++++ACTION_DOWN");

					/*
					 * API Comment
							 	��View������ӦACTION_DOWN�¼���������һ��booleanֵ�������������жϣ�
					            a������True����ʾ��View���ܴ˰��¶���������˵�����������İ��²�������ֹ��
					            Ȼ�������ӦACTION_UP�¼�����������İ��²�����ACTION_DOWN����֮��ͽ����ˣ�����
					            ֮���OnClick/OnLongClick�¼��Ͳ�����Ӧ�ˡ�
					            b������false����ʾ��View�����ܴ˰��¶�������Ӧ��֮�󣬰��²����������·���
					            ֮������ӦACTION_UP�¼�����������һ���жϣ�
					 */
					// Look API Comment
					isTouchMoveFlag = false;
					
					// Set Init State
					returnFlag = false;
					
					

					float lastX = event.getX();
					float lastY = event.getY();
					int motionPosition = listViewMatch.pointToPosition((int) lastX, (int) lastY);
					
					if (motionPosition >= 0) {
						currentItemView = listViewMatch.getChildAt(motionPosition - 
								listViewMatch.getFirstVisiblePosition());
						
						rl_right = (RelativeLayout)currentItemView.findViewById(R.id.item_right);

					}
				}
				
				if(event.getAction() == MotionEvent.ACTION_UP){
					
					System.out.println("++++++++++++++++++++++ACTION_UP");
					
					// Reflesh
//					if(!isTouchMove){
//						listViewMatch.setSelector(android.R.color.transparent);
//					}
					
					// Set Init State
					listviewTouchStartX = -1;
					
					// Return 
					if(isTouchMove){
						// Set Init State
						isTouchMove = false;
						isTouchMoveFlag = true;
						
						
						/* 
						 * 1.
						 * return false
						 * Will not Response OnLongClick
						 * Will Response OnClick
						 */
						returnFlag = false;
						
						
						
						/*
						 * 2.
						 * return true
						 * Will Response OnLongClick
						 * Will not Response OnClick
						 */
						/*
						isAvoidResponseOnLongClick = true;
						returnFlag = true;
						*/
						
						
					}else{
						// Will Response OnLongClick
						isAvoidResponseOnLongClick = true;
						returnFlag = true;
					}
				}
				
				
				if(event.getAction() == MotionEvent.ACTION_MOVE){
					
					System.out.println("++++++++++++++++++++++ACTION_MOVE");
					
					isTouchMove = true;
					
					// Selector
					listViewMatch.setSelector(android.R.color.transparent);
				}
				return returnFlag;
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
     * ��������
     */
	private void setDateTime(){
       final Calendar c = Calendar.getInstance();  
       
       mYear = c.get(Calendar.YEAR);  
       mMonth = c.get(Calendar.MONTH);  
       mDay = c.get(Calendar.DAY_OF_MONTH); 
  
       updateDateDisplay(); 
	}
	
	/**
	 * ����������ʾ
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
     * ���ڿؼ����¼� 
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
	 * ����ʱ��
	 */
	private void setTimeOfDay(){
	   final Calendar c = Calendar.getInstance(); 
       mHour = c.get(Calendar.HOUR_OF_DAY);
       mMinute = c.get(Calendar.MINUTE);
       updateTimeDisplay();
	}
	
	/**
	 * ����ʱ����ʾ
	 */
	private void updateTimeDisplay(){
		EditText editTextTime = matchDialog.getEditTextTime();
		if(editTextTime != null){
			editTextTime.setText(new StringBuilder().append(mHour).append(":")
		               .append((mMinute < 10) ? "0" + mMinute : mMinute)); 
		}
	}
    
    /**
     * ʱ��ؼ��¼�
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
     * �������ں�ʱ��ؼ���Handler 
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
    
    
    
    private void showRight(View rightView) {
		rl_right.setVisibility(View.VISIBLE);
	}
	
	private void hiddenRight(View rightView) {
		rl_right.setVisibility(View.GONE);
	}
	
}
