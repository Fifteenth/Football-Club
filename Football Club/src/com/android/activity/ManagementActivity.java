package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.club.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ManagementActivity extends Activity {
	
	
	String abouts[] = {"�����ƶ�","��Ա���ϼ���","��������ƶ�"};
	List<DetailEntity> list = new ArrayList<DetailEntity>();
	
	ListView lv;
	
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	this.setContentView(R.layout.activity_management);
	
//	Resources resources = this.getResources();
	// ��ֵʵ�������
	for (int i = 0; i < 3; i++) {
		DetailEntity de_1 = new DetailEntity();
		de_1.setLayoutID(R.layout.listview_management);
		de_1.setTitle(i+1+"."+abouts[i]);
		
		list.add(de_1);
	}
	
	lv = (ListView) this.findViewById(R.id.listView_my);
	
	// ʵ�����Զ���������
	MyAdapter ma = new MyAdapter(this, list);
	
	lv.setAdapter(ma);
	
	lv.setOnItemClickListener(new OnItemClickListener() {
	
		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
	//		Toast.makeText(TeamActivity.this, "ѡ�е���:" + position,
	//				Toast.LENGTH_SHORT).show();
			
			lv.setItemChecked(position, true);
			
			Intent aboutActivity = new Intent(ManagementActivity.this, ManagementAboutActivity.class);
			ManagementAboutActivity.about = "" +
					"Ŀ��:\n" +
					"    ���û����ƶ�ÿλ��Ա���ڡ��ٵ����������������" +
					"ÿ�����¸����ۼƷ�ֵ��ί���ȡ��Ӧ�ͷ���ʩ��" +
					"\n" +
					"ϸ��:\n" +
					"    ÿλ��Ա��ʼ����Ϊ0�֣���������һ��+5�֣�" +
					"�ٵ�20��������+2�֣��Ա�������Ϊ׼�����޹ʿ���һ��-5�֣�" +
					"��������ǰ���+1�֣�����������ٲ��Ӳ�����";
			startActivity(aboutActivity);
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

}