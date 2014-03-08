package com.android.dialog;

import com.android.base.ConstantVariable;
import com.android.club.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CostDialog extends Dialog{

	//定义回调事件，用于dialog的点击事件
    public interface OnCustomDialogListener{
            public void back(String name);
    }
    
   
    private String name;
    private int dialogType;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private OnCustomDialogListener customDialogListener;
    EditText editText;

    public CostDialog(Context context,OnCustomDialogListener customDialogListener) {
    	super(context);
    	this.customDialogListener = customDialogListener;
    }
    
   @Override
   protected void onCreate(Bundle savedInstanceState) { 
           

	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.dialog_payment);
	   //设置标题
	   editText = (EditText)findViewById(R.id.edit);
	   Button clickBtn = (Button) findViewById(R.id.clickbtn);
	   clickBtn.setOnClickListener(clickListener);
   }
    
   public void showDialog(int dialogType) {
	   switch(dialogType){
	 
	   case 0:
		   this.dialogType = ConstantVariable.FINANCE_TYPE_PAYMENT;
		   break;
	
	   case 1:
		   break;
		   
	   case 2:
		   break;
	   
	   }
	   
	   setTitle(name);
	   if(editText!=null){
		   editText.setText("");   
	   }
	   super.show();
   };

   private View.OnClickListener clickListener = new View.OnClickListener() {
            
           @Override
            public void onClick(View v) {
        	   customDialogListener.back(String.valueOf(editText.getText()));
        	   CostDialog.this.dismiss();
        	   
        	   // 
        	   switch(dialogType){
        		 
        	   case 0:
        		   
        		   break;
        	
        	   case 1:
        		   break;
        		   
        	   case 2:
        		   break;
        	   
        	   }
            }
    };
    
    
    

}
