package com.android.dialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

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
    private FinanceTO selectedFinanceTO;
	private List<FinanceTO>financePaymentList;
    private List<FinanceTO>financeDeductionsList;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public FinanceTO getSelectedFinanceTO() {
		return selectedFinanceTO;
	}

	public void setSelectedFinanceTO(FinanceTO selectedFinanceTO) {
		this.selectedFinanceTO = selectedFinanceTO;
	}

	public List<FinanceTO> getFinancePaymentList() {
		return financePaymentList;
	}

	public void setFinancePaymentList(List<FinanceTO> financePaymentList) {
		this.financePaymentList = financePaymentList;
	}

	public List<FinanceTO> getFinanceDeductionsList() {
		return financeDeductionsList;
	}

	public void setFinanceDeductionsList(List<FinanceTO> financeDeductionsList) {
		this.financeDeductionsList = financeDeductionsList;
	}

	private OnCustomDialogListener customDialogListener;
    EditText editText;

    public CostDialog(Context context,OnCustomDialogListener customDialogListener) {
    	super(context);
    	this.customDialogListener = customDialogListener;
    	
    	//
    	
    	
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
        	   
        	   try {
        		   switch(dialogType){
	        	   case 0:
	        		   Context context = getContext();
	        		   // 看不到文件
	        		   File file = new File(context.getFilesDir(), "/finance_payment.xml"); 
	        		   InputStream inputStream = new FileInputStream(file.getPath());
	        		   StringBuffer out = new StringBuffer();
	        		   byte[] b = new byte[4096];
	        		   try {
	        			   for (int n; (n = inputStream.read(b)) != -1;) {
	        				   out.append(new String(b, 0, n));
	        			   }
	        		   } catch (IOException e) {
	        			   // TODO Auto-generated catch block
	        			   e.printStackTrace();
	        		   } 
	        		   out.toString(); 
	        		   
	        		   FileOutputStream outStream = new FileOutputStream(file);
	        		   int amount = Integer.valueOf(editText.getText().toString());
	        		   selectedFinanceTO.setAmount(amount);
	        		   financePaymentList.add(selectedFinanceTO);
	        		   // 重绘XML
	        		   FinanceService.getOutString(financePaymentList, outStream);
	                   
	        		   break;
	        	
	        	   case 1:
	        		   break;
	        		   
	        	   case 2:
	        		   break;
        		   } 
        	   }
        	   
        	   catch(Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
    };
    
    
    

}
