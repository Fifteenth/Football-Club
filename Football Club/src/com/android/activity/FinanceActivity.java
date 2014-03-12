package com.android.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.android.base.ConstantVariable;
import com.android.club.R;
import com.android.dialog.CostDialog;
import com.android.service.FinanceService;
import com.android.to.FinanceTO;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Xml;
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

	List<FinanceTOEntity> list = new ArrayList<FinanceTOEntity>();
	
	private ListView listView;
	private CostDialog dialog;
	private List <FinanceTO>financeTOList;
	private List <FinanceTO>financePaymentTOList;
	private List <FinanceTO>financeDeductionsTOList;
	
	private FinanceTO selectedFinanceTO;
	private Button buttonDownOrUp;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_finance);
		
		dialog = new CostDialog(this,
				new CostDialog.OnCustomDialogListener() {
			@Override
			public void back(String name) {
                     
            }
		});
		
		File xmlFile = new File(getFilesDir(), "/person1.xml");  
		
		try {
			InputStream inputStream = new FileInputStream(xmlFile.getPath());
			
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
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {  
            FileOutputStream outStream = new FileOutputStream(xmlFile);  
            FileOutputStream fos = null;  
            
            StringBuffer str = new StringBuffer();  
	           str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"  
	                   + "<resources>");  
	           str.append("</resources>"); 
	           
	          try {  
	              fos = new FileOutputStream(xmlFile);  
	              fos.write(str.toString().getBytes());  
	          } catch (FileNotFoundException e) {  
	              e.printStackTrace();  
	          } catch (IOException e) {  
	              e.printStackTrace();  
	          } finally {  
	              if (fos != null) {  
	                  try {  
	                      fos.flush();  
	                      fos.close();  
	                  } catch (IOException e) {  
	                      e.printStackTrace();  
	                  }  
	              }  
	          } 
            
            
//            FinanceService.writeXML(getc,financePaymentList, outStream);
            System.out.println("д��ɹ�");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
		 
		Resources resources = this.getResources();
		InputStream inputStreamFinance = getClass().getClassLoader().getResourceAsStream("finance.xml");
		InputStream inputStreamFinancePayment = getClass().getClassLoader().getResourceAsStream("finance_payment.xml");
		InputStream inputStreamFinanceDeductions = getClass().getClassLoader().getResourceAsStream("finance_deductions.xml");
		//ͨ��Resources�����XmlResourceParserʵ��   
		financeTOList = FinanceService.getFinanceTOList(inputStreamFinance);
		financePaymentTOList = FinanceService.getFinanceTOList(inputStreamFinancePayment);
		financeDeductionsTOList = FinanceService.getFinanceTOList(inputStreamFinanceDeductions);
		// ��ֵʵ�������
		if(list.size()==0){
			for (int i = 0; i < 9; i++) {
				FinanceTO financeTO = financeTOList.get(i);
				
				FinanceTOEntity financeTOEntity = new FinanceTOEntity();
				financeTOEntity.setLayoutID(R.layout.listview_finance);
				financeTOEntity.setText(financeTO.getName());
				financeTOEntity.setTitle(ConstantVariable.FINANCE_LISTVIEW_BALANCE
						+financeTO.getAmount() + ConstantVariable.FINANCE_SYSMBOL);
				String pngName = ConstantVariable.PLAYER_AVATAR+(i+1);
				int id = resources.getIdentifier(pngName, 
		       			"drawable" , getApplicationContext().getPackageName());  
				
				financeTOEntity.setBitmap(id);
				financeTOEntity.setBtnText(ConstantVariable.FINANCE_DIALOG_MONEY);
				list.add(financeTOEntity);
			}
		}
		

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
				
				//Bitmap output = Bitmap.createBitmap(10, 10, Config.ARGB_8888);

				TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
				tv_1.setText(list.get(position).getTitle());

				TextView tv_2 = (TextView) convertView.findViewById(R.id.text);
				tv_2.setText(list.get(position).getText());
				
				//
				Button buttonPayment = (Button) convertView.findViewById(R.id.button_payment);
				buttonPayment.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						selectedFinanceTO = financeTOList.get(position);
						dialog.setName(selectedFinanceTO.getName()
								+"  "+ConstantVariable.FINANCE_DIALOG_MONEY);
						dialog.setFinancePaymentList(financePaymentTOList);
						dialog.setFinanceDeductionsList(financeDeductionsTOList);
						dialog.setSelectedFinanceTO(selectedFinanceTO);
						dialog.showDialog(ConstantVariable.FINANCE_TYPE_PAYMENT);
					}
				});
				
				final RelativeLayout relativeLayoutDownOrUp = (RelativeLayout) convertView.findViewById(R.id.linearyoutDown);
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
}
