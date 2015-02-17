package com.af.dialog;

import java.util.ArrayList;
import java.util.List;

import com.af.activity.TacticalActivity;
import com.android.club.R;
import com.cf.base.variable.ConstantVariable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class FormationSelectedDialog  extends Dialog{
	
	
	
	public final static int DEFAULT_SELECTION = -1;
	public static Spinner spinner; 
	public static ArrayAdapter<String> adapter;  
	
	public static int lastSelection = DEFAULT_SELECTION;
	
	public static OnItemSelectedListener onItemSelectedListener;
	
	private TacticalActivity tacticalActivity;
	
	public FormationSelectedDialog(Context context) {
		super(context);
		
		tacticalActivity = (TacticalActivity) context;
	}
	
	
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_formation_selected);
        
		setTitle(ConstantVariable.MENU_BUTTOM_FORMATION_SELECT);
		
        Window window = getWindow();  
        WindowManager.LayoutParams wl = window.getAttributes();  
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;  
        wl.alpha=0.6f;//����͸����,0.0Ϊ��ȫ͸����1.0Ϊ��ȫ��͸��  
        window.setAttributes(wl);
        
        // Formation
        List<String> list = new ArrayList<String>();     
        list.add(ConstantVariable.formation331);     
        list.add(ConstantVariable.formation322);     
        list.add(ConstantVariable.formation232);     
        list.add(ConstantVariable.formation241);     
        list.add(ConstantVariable.formation421);  
        
        spinner = (Spinner)findViewById(R.id.spinner_City);    
        
        //�ڶ�����Ϊ�����б���һ����������������õ���ǰ�涨���list��   
        adapter = new ArrayAdapter<String>(tacticalActivity,android.R.layout.simple_spinner_item, list);   
        //��������Ϊ���������������б�����ʱ�Ĳ˵���ʽ��   
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
        //���Ĳ�������������ӵ������б���   
        spinner.setAdapter(adapter);   
        //���岽��Ϊ�����б����ø����¼�����Ӧ���������Ӧ�˵���ѡ��   
        
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
        	
			public void onItemSelected(AdapterView arg0, View arg1, int currentSelection, long arg3) {
            
            	if(lastSelection == DEFAULT_SELECTION){
            		currentSelection = lastSelection;
            		lastSelection = 0;
            	}
            	
        		switch(currentSelection){
        			case -1:{
        				return;
        			}
	        		case 0:{
	        			TacticalActivity.formation = ConstantVariable.formation331;
	        			break;
	        		}
	        		case 1:{
	        			TacticalActivity.formation = ConstantVariable.formation322;
	        			break;
	        		}
	        		case 2:{
	        			TacticalActivity.formation = ConstantVariable.formation232;
	        			break;
	        		}
	        		case 3:{
	        			TacticalActivity.formation = ConstantVariable.formation241;
	        			break;
	        		}
	        		case 4:{
	        			TacticalActivity.formation = ConstantVariable.formation421;
	        			break;
	        		}
        		}
        		
        		// ѡ�����ͺ���Ҫ����
        		FormationSelectedDialog.this.dismiss();
        		tacticalActivity.onCreate(null);
            }   
			
			public void onNothingSelected(AdapterView arg0) {   
                // TODO Auto-generated method stub   
                //myTextView.setText("NONE");   
                arg0.setVisibility(View.VISIBLE);   
            }   
        }); 
        
        
        /*�����˵�����������ѡ����¼�����*/  
        spinner.setOnTouchListener(new Spinner.OnTouchListener(){   
            public boolean onTouch(View v, MotionEvent event) {   
                // TODO Auto-generated method stub   
                /* ��mySpinner ���أ�������Ҳ���ԣ����Լ�����*/  
                v.setVisibility(View.INVISIBLE);   
                return false;   
            }  
        });   
        
        /*�����˵�����������ѡ���ı��¼�����*/  
        spinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){   
        public void onFocusChange(View v, boolean hasFocus) {   
        	// TODO Auto-generated method stub   
            v.setVisibility(View.VISIBLE);   
        }   
        });    
	}

}
