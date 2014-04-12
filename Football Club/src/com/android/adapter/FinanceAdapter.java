package com.android.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.android.activity.FinanceActivity;
import com.android.activity.FinanceNoticeActivity;
import com.android.activity.FinanceRecordActivity;
import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.dialog.FinanceDialog;
import com.android.to.FinanceTO;

public class FinanceAdapter implements ListAdapter {

	private FinanceActivity financeActivity;
	private Resources resources;
	private List<FinanceTO> financeList;
	private FinanceTO financeTOSelected;
	
	private static int visibleIndex = -1;
	
	private FinanceDialog dialog;
	private Button buttonDownOrUp;
	
	/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
	private LayoutInflater layoutInflater;

	public FinanceAdapter(FinanceActivity financeActivity, List<FinanceTO> financeList,Resources resources) {
		this.financeList = financeList;
		this.layoutInflater = LayoutInflater.from(financeActivity);
		this.resources = resources;
		this.financeActivity = financeActivity;
		
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return financeList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return financeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * ����ITEM ��������
	 */
	@Override
	public View getView(final int position, View convertView,
			ViewGroup parent) {

		dialog = new FinanceDialog(financeActivity,
				new FinanceDialog.OnCustomDialogListener() {
			@Override
			public void back(String name) {
            }
		});
		
		if (convertView == null) {
			// ���ز���
			convertView = layoutInflater.inflate(R.layout.listview_finance, null);

			// ImageView
			ImageView imageviewAvatar = (ImageView) convertView.findViewById(R.id.img);
			int number = financeList.get(position).getNumber();
			String pngName;
			if(number<10){
				pngName = "player_avatar_0" + number;
			}else{
				pngName = "player_avatar_" + number;
			}
			int imageResourceId = resources.getIdentifier(pngName, 
	       			"drawable" , financeActivity.getPackageName());  
			imageviewAvatar.setBackgroundResource(imageResourceId);
			
			// Amount
			TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
			tv_1.setText(ConstantVariable.FINANCE_DIALOG_MONEY + financeList.get(position).getAmount());

			// Name
			TextView textviewName = (TextView) convertView.findViewById(R.id.text);
			textviewName.setText(financeList.get(position).getName());
			
			// Payment
			Button buttonPayment = (Button) convertView.findViewById(R.id.button_payment);
			buttonPayment.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(financeList == null){
						return;
					}
					System.out.println("financeList size:" + financeList.size());
					System.out.println("position:" + position);
					financeTOSelected = financeList.get(position);
					dialog.setName(financeTOSelected.getName()
							+"  "+ConstantVariable.FINANCE_DIALOG_MONEY);
					dialog.setFinanceList(financeList);
					dialog.setFinancePaymentList(financeActivity.getFinancePaymentList());
					dialog.setFinanceDeductionList(financeActivity.getFinanceDeductionList());
					dialog.setSelectedFinanceTO(financeTOSelected);
					dialog.setFinanceListSelectedIndex(position);
					dialog.showDialog(ConstantVariable.FINANCE_TYPE_PAYMENT);
					
				}
			});
			
			// Deduction
			Button buttonDeduction = (Button) convertView.findViewById(R.id.button_deduction);
			buttonDeduction.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(financeList == null){
						return;
					}
					System.out.println("financeList size:" + financeList.size());
					System.out.println("position:" + position);
					financeTOSelected = financeList.get(position);
					dialog.setName(financeTOSelected.getName()
							+"  "+ConstantVariable.FINANCE_DIALOG_MONEY);
					dialog.setFinanceList(financeList);
					dialog.setFinancePaymentList(financeActivity.getFinancePaymentList());
					dialog.setFinanceDeductionList(financeActivity.getFinanceDeductionList());
					dialog.setSelectedFinanceTO(financeTOSelected);
					dialog.setFinanceListSelectedIndex(position);
					dialog.showDialog(ConstantVariable.FINANCE_TYPE_DEDUCTION);
				}
			});
			

			// CostRecord
			Button buttonCostRecord = (Button) convertView.findViewById(R.id.button_costRecord);
			buttonCostRecord.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent financeRecordActivity = new Intent(
							financeActivity,FinanceRecordActivity.class);
					financeActivity.startActivity(financeRecordActivity);
					FinanceRecordActivity.playerName = financeList.get(position).getName();
				}
			});
			
			
			// Notice
			Button buttonNotice = (Button) convertView.findViewById(R.id.button_notice);
			buttonNotice.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent financeNoticeActivity = new Intent(
							financeActivity,FinanceNoticeActivity.class);
					financeActivity.startActivity(financeNoticeActivity);
				}
			});
			
			buttonDownOrUp = (Button) convertView.findViewById(R.id.button_down);
			final LinearLayout relativeLayoutDownOrUp = (LinearLayout) convertView.findViewById(R.id.linearyoutDown);
			if(visibleIndex == position){
				buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_up);
			}else{
				buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_down);
				relativeLayoutDownOrUp.setVisibility(View.GONE); // ����	
			}
			buttonDownOrUp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Set
					if(visibleIndex == position){
						buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_down);
						relativeLayoutDownOrUp.setVisibility(View.GONE); // ����	
						visibleIndex = -1;
					}else{
						buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_up);
						visibleIndex = position;
					}
					// Reflesh
					((FinanceActivity) financeActivity).onCreate(null);
				}
			});
		}
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return financeList.size();
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * �Ƿ�Item����
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * true������Ŀ��ѡ��ɵ��
	 */
	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * �Ƿ���ʾ�ָ���
	 */
	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return true;
	}

}
