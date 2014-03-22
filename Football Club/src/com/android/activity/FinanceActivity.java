package com.android.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import com.android.base.ConstantVariable;
import com.android.base.util.FileUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.variable.XMLVariable;
import com.android.club.R;
import com.android.dialog.CostDialog;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FinanceActivity extends Activity{

	public List<FinanceTOEntity> list = new ArrayList<FinanceTOEntity>();
	
	private ListView listView;
	private CostDialog dialog;
	private List <FinanceTO>financeList;
	private List <FinanceTO>financePaymentList;
	private List <FinanceTO>financeDeductionList;
	
	private FinanceTO selectedFinanceTO;
	private Button buttonDownOrUp;
	
	//private static boolean initFlag = false;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_finance);
		
		dialog = new CostDialog(this,
				new CostDialog.OnCustomDialogListener() {
			@Override
			public void back(String name) {
            }
		});
		 
		// Init list&financeList
//		if(!initFlag){
			//initFlag = true;
					
			InputStream inputStreamFinance = null;
			InputStream inputStreamFinancePayment = null;
			InputStream inputStreamFinanceDeduction = null;
			try {
				/*
				 * this.getBaseContext().getFilesDir()
				 */
				String sdCardRootPath = SDCardUtil.getRootPath();
				inputStreamFinance = FileUtil.getFileInputStream(
						new File(sdCardRootPath,XMLVariable.FINANCE));
				inputStreamFinancePayment = FileUtil.getFileInputStream(
						new File(sdCardRootPath,XMLVariable.FINANCE_PAYMENT));
				inputStreamFinanceDeduction = FileUtil.getFileInputStream(
						new File(sdCardRootPath,XMLVariable.FINANCE_DEDUCTION));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//ͨ��Resources�����XmlResourceParserʵ��   
			if(inputStreamFinance!=null){
				financeList = FinanceService.getFinanceTOList(inputStreamFinance);
				financePaymentList = FinanceService.getFinanceTOList(inputStreamFinancePayment);
				financeDeductionList = FinanceService.getFinanceTOList(inputStreamFinanceDeduction);
			}else{
				financeList = new ArrayList<FinanceTO>();
			}
			
			// ��ֵʵ�������
			if(list.size()==0
					&&financeList!=null
					&&financeList.size()>0){
				setListForListView();
			}
			
			if(financeList.size() == 0 
					|| list.size() == 0){
				// Default
				inputStreamFinance = getClass().getClassLoader().getResourceAsStream("finance.xml");
				financeList = FinanceService.getFinanceTOList(inputStreamFinance);
				setListForListView();
			}
//		}

		listView = (ListView) this.findViewById(R.id.listView_my);
		// ʵ�����Զ���������
		MyAdapter ma = new MyAdapter(this, list);
		listView.setAdapter(ma);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				listView.setItemChecked(position, true);
			}
		});
	}

	/**
	 * �Զ���һ��Adapter(ʵ����ListAdapter�ӿ�)
	 * 
	 * @author Administrator
	 * 
	 */
	class MyAdapter implements ListAdapter {

		private List<FinanceTOEntity> list;

		/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
		private LayoutInflater layoutInflater;

		public MyAdapter(Context context, List<FinanceTOEntity> list) {
			this.list = list;
			layoutInflater = LayoutInflater.from(context);
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
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
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
			final FinanceTOEntity financeTOEntity = list.get(position);
			
			if (convertView == null) {
				// ���ز���
				convertView = layoutInflater.inflate(
						financeTOEntity.getLayoutID(), null);
				// ���ò�������
				ImageView iv = (ImageView) convertView.findViewById(R.id.img);
				iv.setBackgroundResource(list.get(position).getBitmap());
				
				TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
				tv_1.setText(list.get(position).getTitle());

				TextView tv_2 = (TextView) convertView.findViewById(R.id.text);
				tv_2.setText(list.get(position).getText());
				
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
						selectedFinanceTO = financeList.get(position);
						dialog.setName(selectedFinanceTO.getName()
								+"  "+ConstantVariable.FINANCE_DIALOG_MONEY);
						dialog.setFinanceList(financeList);
						dialog.setFinancePaymentList(financePaymentList);
						dialog.setFinanceDeductionList(financeDeductionList);
						dialog.setSelectedFinanceTO(selectedFinanceTO);
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
						selectedFinanceTO = financeList.get(position);
						dialog.setName(selectedFinanceTO.getName()
								+"  "+ConstantVariable.FINANCE_DIALOG_MONEY);
						dialog.setFinanceList(financeList);
						dialog.setFinancePaymentList(financePaymentList);
						dialog.setFinanceDeductionList(financeDeductionList);
						dialog.setSelectedFinanceTO(selectedFinanceTO);
						dialog.setFinanceListSelectedIndex(position);
						dialog.showDialog(ConstantVariable.FINANCE_TYPE_DEDUCTION);
					}
				});
				

				// Deduction
				Button buttonCostRecord = (Button) convertView.findViewById(R.id.button_costRecord);
				buttonCostRecord.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent financeRecordActivity = new Intent(FinanceActivity.this, 
								FinanceRecordActivity.class);
						startActivity(financeRecordActivity);
					}
				});
				
				
				// Deduction
				Button buttonNotice = (Button) convertView.findViewById(R.id.button_notice);
				buttonNotice.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent financeNoticeActivity = new Intent(FinanceActivity.this, 
								FinanceNoticeActivity.class);
						startActivity(financeNoticeActivity);
					}
				});
				
				final LinearLayout relativeLayoutDownOrUp = (LinearLayout) convertView.findViewById(R.id.linearyoutDown);
				relativeLayoutDownOrUp.setVisibility(financeTOEntity.getLinearyoutVissble()); // ����	
				buttonDownOrUp = (Button) convertView.findViewById(R.id.button_down);

				if(financeTOEntity.getLinearyoutVissble() == View.GONE){
					buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_down);
				}else{
					buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_up);
				}
				buttonDownOrUp.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if(financeTOEntity.getLinearyoutVissble() == View.GONE){
							buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_up);
							financeTOEntity.setLinearyoutVissble(View.VISIBLE);
						}else{
							buttonDownOrUp.setBackgroundResource(R.drawable.button_hidden_down);
							financeTOEntity.setLinearyoutVissble(View.GONE);
						}
						relativeLayoutDownOrUp.setVisibility(financeTOEntity.getLinearyoutVissble()); 
						onCreate(null);
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
			return list.size();
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

	/**
	 * ListView����ʵ����
	 * 
	 * @author Administrator
	 * 
	 */
	class FinanceTOEntity {
		/** ����ID */
		private int layoutID;

		/** ͼƬID */
		private int bitmap;

		/** ���� */
		private String title;

		/** ���� */
		private String text;

		/** ��ť���� */
		private String BtnText;
		
		private int linearyoutVissble = View.GONE;

		public String getBtnText() {
			return BtnText;
		}

		public void setBtnText(String btnText) {
			BtnText = btnText;
		}

		public int getLayoutID() {
			return layoutID;
		}

		public int getBitmap() {
			return bitmap;
		}

		public void setBitmap(int bitmap) {
			this.bitmap = bitmap;
		}

		public void setLayoutID(int layoutID) {
			this.layoutID = layoutID;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		public int getLinearyoutVissble() {
			return linearyoutVissble;
		}

		public void setLinearyoutVissble(int linearyoutVissble) {
			this.linearyoutVissble = linearyoutVissble;
		}
	}
	
	
	public void setListForListView(){
		Resources resources = this.getResources();
		for (int i = 0; i < 9; i++) {
			FinanceTO financeTO = financeList.get(i);
			
			FinanceTOEntity financeTOEntity = new FinanceTOEntity();
			financeTOEntity.setLayoutID(R.layout.listview_finance);
			financeTOEntity.setText(financeTO.getName());
			financeTOEntity.setTitle(ConstantVariable.FINANCE_DIALOG_MONEY + financeTO.getAmount());
			String pngName = ConstantVariable.PLAYER_AVATAR+(i+1);
			int id = resources.getIdentifier(pngName, 
	       			"drawable" , getApplicationContext().getPackageName());  
			
			financeTOEntity.setBitmap(id);
			financeTOEntity.setBtnText(ConstantVariable.FINANCE_DIALOG_MONEY);
			list.add(financeTOEntity);
		}
	}
}
