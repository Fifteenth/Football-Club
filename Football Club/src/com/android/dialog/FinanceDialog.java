package com.android.dialog;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.android.activity.FinanceActivity;
import com.android.activity.ManagementAboutActivity;
import com.android.activity.ManagementActivity;
import com.android.activity.NavigationActivity;
import com.android.base.ConstantVariable;
import com.android.base.util.DateUtil;
import com.android.base.util.FileUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.util.ValidateUtil;
import com.android.base.util.XMLUtil;
import com.android.base.variable.XMLVariable;
import com.android.club.R;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FinanceDialog extends Dialog{

	//定义回调事件，用于dialog的点击事件
    public interface OnCustomDialogListener{
            public void back(String name);
    }
    
    
    private FinanceActivity financeActivity;
   
    private String name;
    private FinanceTO selectedFinanceTO;
    private List<FinanceTO>financeList;
	private List<FinanceTO>financePaymentList;
    private List<FinanceTO>financeDeductionList;
    private int financeListSelectedIndex;

    public int dialogType;
    
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
	
	public List<FinanceTO> getFinanceList() {
		return financeList;
	}

	public void setFinanceList(List<FinanceTO> financeList) {
		this.financeList = financeList;
	}

	public List<FinanceTO> getFinancePaymentList() {
		return financePaymentList;
	}

	public void setFinancePaymentList(List<FinanceTO> financePaymentList) {
		this.financePaymentList = financePaymentList;
	}

	public List<FinanceTO> getFinanceDeductionList() {
		return financeDeductionList;
	}

	public void setFinanceDeductionList(List<FinanceTO> financeDeductionList) {
		this.financeDeductionList = financeDeductionList;
	}
	
	public int getFinanceListSelectedIndex() {
		return financeListSelectedIndex;
	}

	public void setFinanceListSelectedIndex(int financeListSelectedIndex) {
		this.financeListSelectedIndex = financeListSelectedIndex;
	}

	private OnCustomDialogListener customDialogListener;
    EditText edittextAmount;
    EditText edittextDescription;

    public FinanceDialog(Context context,OnCustomDialogListener customDialogListener) {
    	super(context);
    	this.customDialogListener = customDialogListener;
    	financeActivity = (FinanceActivity) context;
    }
    
   @Override
   protected void onCreate(Bundle savedInstanceState) { 
           

	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.dialog_finance);
	   //设置标题
	   edittextAmount = (EditText)findViewById(R.id.edittext_amount);
	   edittextDescription = (EditText)findViewById(R.id.edittext_description);
	   Button clickBtn = (Button) findViewById(R.id.buttonConfirmPayment);
	   clickBtn.setOnClickListener(clickListener);
   }
    
   public void showDialog(int dialogType) {
	   switch(dialogType){
	 
	   case 1:
		   this.dialogType = ConstantVariable.FINANCE_TYPE_PAYMENT;
		   break;
	
	   case 2:
		   this.dialogType = ConstantVariable.FINANCE_TYPE_DEDUCTION;
		   break;
		   
	   case 3:
		   this.dialogType = ConstantVariable.FINANCE_TYPE_RECORD;
		   break;
	   
	   }
	   
	   setTitle(name);
	   if(edittextAmount!=null){
		   edittextAmount.setText("");   
	   }
	   super.show();
   };

	private View.OnClickListener clickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			customDialogListener.back(String.valueOf(edittextAmount.getText()));
			FinanceDialog.this.dismiss();

			int amountText = 0;
			String amountString = "";
			String describition = "";
			Editable editableAmount = edittextAmount.getText();
			Editable editableDescribition = edittextDescription.getText();
			if(editableAmount != null){
				amountString = editableAmount.toString();
			}
			if(amountString.equals("")){
				return;
			}
			if(editableDescribition != null){
				describition = editableDescribition.toString();
			}
			selectedFinanceTO.setDescripition(describition);
			
			if(!ValidateUtil.isNumeric(amountString)){
				new  AlertDialog.Builder(financeActivity)    
			    .setTitle("提示：" )
			    .setMessage("请输入数字!" )
			    .setPositiveButton("确定" ,null).show(); 
				return;
			}
			
			try {
				switch (dialogType) {
				case 1:
					
					amountText = Integer.valueOf(amountString);
					financeAction(dialogType,amountText);
					break;

				case 2:
					
					amountText = Integer.valueOf(amountString);
					financeAction(dialogType,amountText);
					break;

				case 3:

					break;
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    };
    
    
    public void financeAction(int dialogType,int amountText) throws Exception{
    	String sdCardRootPath = SDCardUtil.getRootPath();
		
		File financeXMLFile = new File(sdCardRootPath,XMLVariable.FINANCE);
		
		int amountOld = selectedFinanceTO.getAmount();
		selectedFinanceTO.setAmount(amountText);
		// Replace 
		// Add Amount
		int amountTotal;
		selectedFinanceTO.setCurrentTime(DateUtil.getCurrentTimeYYYYMMDDhhmmss());
		if(dialogType==ConstantVariable.FINANCE_TYPE_PAYMENT){
			selectedFinanceTO.setType("+");
			financePaymentList.add(selectedFinanceTO);
			amountTotal = amountOld + amountText;
			// finance_payment.xml
			File financePaymentXmlFile = new File(sdCardRootPath,XMLVariable.FINANCE_PAYMENT);
			saveFinanceXML(financePaymentList,financePaymentXmlFile);
		}else{
			selectedFinanceTO.setType("-");
			financeDeductionList.add(selectedFinanceTO);
			amountTotal = amountOld - amountText;
			// finance_deduction.xml
			File financeDeductionXmlFile = new File(sdCardRootPath,XMLVariable.FINANCE_DEDUCTION);
			saveFinanceXML(financeDeductionList,financeDeductionXmlFile);
		}
		 
		financeList.get(financeListSelectedIndex).setAmount(amountTotal);
		
		
		// Finance.xml
		saveFinanceXML(financeList,financeXMLFile);
		// Refresh
		RefreshFinanceActivity();
    }
    
    public void RefreshFinanceActivity(){
    	financeList = null;
		financeActivity.list.clear();
		financeActivity.onCreate(null);
    }
    
    public void saveFinanceXML(List<FinanceTO> financeTOList,File financeXMLFile) 
    		throws Exception{
    	FileOutputStream financePaymentXmlOutputStream = FileUtil.getFileOutputStream(financeXMLFile);
		FinanceService.getWriteXMLAndSave(financeTOList, financePaymentXmlOutputStream);
    }
    
    

}
