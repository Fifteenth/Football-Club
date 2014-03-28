package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.activitySupport.MatchesActivitySupport;
import com.android.club.R;
import com.android.dialog.FinanceDialog;
import com.android.dialog.MatchDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MatchesActivity extends Activity{
	
	String matchs[] = {"��һ��           JAVA�����VS��������      2014/02/22",
					   "�ڶ���           JAVA�����VS�ֵܶ�           2014/03/01",
					   "������           JAVA�����VS��Ǳͧ           2014/03/01",
					   "������           JAVA�����VS�����           2014/03/01",
					   "������           JAVA�����VS�����           2014/03/01",
					   "������           JAVA�����VS�ǲ���˹      2014/03/01",
					   "������           JAVA�����VS����Ī           2014/03/01",
					   "�ڰ���           JAVA�����VS����                2014/03/01",
					   "�ھ���           JAVA�����VS����                2014/03/01",
					   "��ʮ��           JAVA�����VS����                2014/03/01",
					   "��ʮһ��      JAVA�����VS��ɭ��           2014/03/01",
					   "��ʮ����      JAVA�����VS������           2014/03/01",
					   "��ʮ����      JAVA�����VS�ж���           2014/03/01",
					   "��ʮ����      JAVA�����VS�ȴ�                2014/03/01",
					   "��ʮ����      JAVA�����VS����                2014/03/01",
					   "��ʮ����      JAVA�����VS�����ɵ�      2014/03/01",
					   "��ʮ�� ��     JAVA�����VS��������      2014/02/22",
					   "��ʮ����      JAVA�����VS�ֵܶ�           2014/03/01",
					   "��ʮ����      JAVA�����VS��Ǳͧ           2014/03/01",
					   "�ڶ�ʮ��      JAVA�����VS�����           2014/03/01",
					   "�ڶ�ʮһ�� JAVA�����VS�����           2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS�ǲ���˹      2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS����Ī           2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS����                2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS����                2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS����                2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS��ɭ��           2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS������           2014/03/01",
					   "�ڶ�ʮ���� JAVA�����VS�ж���           2014/03/01",
					   "����ʮ��      JAVA�����VS�ȴ�                2014/03/01",
					   "����ʮһ�� JAVA�����VS����                2014/03/01",
					   "����ʮ���� JAVA�����VS�����ɵ�      2014/03/01",		
	};
	
	// Get Matches
//	List 
	
	List<DetailEntity> list = new ArrayList<DetailEntity>();
	ListView listViewMatch;
	MatchDialog dialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		MatchesActivitySupport.WriteMatches();
		MatchesActivitySupport.ReadMatches();
		
		dialog = new MatchDialog(this);

		this.setContentView(R.layout.activity_matchs);

		Resources resources = this.getResources();
		// ��ֵʵ�������
		for (int i = 0; i < 32; i++) {
			DetailEntity de_1 = new DetailEntity();
			de_1.setLayoutID(R.layout.listview_matches);
			de_1.setTitle(matchs[i]);
			
			list.add(de_1);
		}

		listViewMatch = (ListView) this.findViewById(R.id.listView_my);

		// ʵ�����Զ���������
		MyAdapter ma = new MyAdapter(this, list);

		listViewMatch.setAdapter(ma);

		listViewMatch.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(TeamActivity.this, "ѡ�е���:" + position,
//						Toast.LENGTH_SHORT).show();
				
				listViewMatch.setItemChecked(position, true);
			}
		});
		
		
		listViewMatch.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				dialog.show();
				return false;
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

	@Override
  	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
