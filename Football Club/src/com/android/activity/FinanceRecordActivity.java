package com.android.activity;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.android.activity.MatchesActivity.DetailEntity;
import com.android.activity.MatchesActivity.MyAdapter;
import com.android.base.ConstantVariable;
import com.android.base.util.FileUtil;
import com.android.base.util.SDCardUtil;
import com.android.base.variable.XMLVariable;
import com.android.club.R;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FinanceRecordActivity extends Activity {

	public static String playerName;
	
	List<DetailEntity> list = new ArrayList<DetailEntity>();
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_finance_record);

		List <FinanceTO> financePaymentList= getFinanceTOList();

		//Resources resources = this.getResources();
		// ��ֵʵ�������
		for (int i = 0; i < financePaymentList.size(); i++) {
			DetailEntity de_1 = new DetailEntity();
			de_1.setLayoutID(R.layout.listview_finance_record);
			FinanceTO financeTO = financePaymentList.get(i);
			
			int amount = financeTO.getAmount();
			String amountString; 
			if(amount > 0 && amount < 10){
				amountString = "    " + amount + ConstantVariable.FINANCE_SYSMBOL;
			}else if(amount >= 10 && amount <100){
				amountString = "  " + amount + ConstantVariable.FINANCE_SYSMBOL;
			}else{
				amountString = ConstantVariable.SYSBOL_DOUBLE_QUOTES 
								+ amount 
								+ ConstantVariable.FINANCE_SYSMBOL;
			}
			de_1.setTitle(
					financeTO.getNumber()
					+ "-"
					+ financeTO.getName()
					+ "       "
					+ financeTO.getType()
					+ amountString);
			de_1.setText("   " + financeTO.getCurrentTime());
			list.add(de_1);
		}

		if(list!=null
				&&list.size()!=0){
			lv = (ListView) this.findViewById(R.id.listView_my);

			// ʵ�����Զ���������
			MyAdapter ma = new MyAdapter(this, list);

			lv.setAdapter(ma);

			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// Toast.makeText(TeamActivity.this, "ѡ�е���:" + position,
					// Toast.LENGTH_SHORT).show();

					lv.setItemChecked(position, true);
				}
			});
		}
	}

	/**
	 * �Զ���һ��Adapter(ʵ����ListAdapter�ӿ�)
	 * 
	 * @author Administrator
	 * 
	 */
	class MyAdapter implements ListAdapter {

		private List<DetailEntity> list;

		/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
		private LayoutInflater layoutInflater;

		public MyAdapter(Context context, List<DetailEntity> list) {
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
			if (convertView == null) {
				// ���ز���
				convertView = layoutInflater.inflate(list.get(position)
						.getLayoutID(), null);

				TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
				tv_1.setText(list.get(position).getTitle());
				
				TextView tv_3 = (TextView) convertView.findViewById(R.id.text);
				tv_3.setText(list.get(position).getText());

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
	class DetailEntity {
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
	}
	
	public List <FinanceTO> getFinanceTOList(){
		String sdCardRootPath = SDCardUtil.getRootPath();
		InputStream inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,XMLVariable.FINANCE_PAYMENT));
		List <FinanceTO> financeTOList = new ArrayList<FinanceTO>();;
		if(inputStreamFinance!=null){
			financeTOList = FinanceService.getFinanceTOList(inputStreamFinance,playerName);
		}
		inputStreamFinance = FileUtil.getFileInputStream(
				new File(sdCardRootPath,XMLVariable.FINANCE_DEDUCTION));
		if(inputStreamFinance!=null){
			financeTOList.addAll(FinanceService.getFinanceTOList(inputStreamFinance,playerName));
		}
		
		return financeTOList;
	}
}