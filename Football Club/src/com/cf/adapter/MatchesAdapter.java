package com.cf.adapter;

import java.util.List;

import com.android.club.R;
import com.cf.to.MatchTO;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MatchesAdapter implements ListAdapter{
	
	/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
	private LayoutInflater layoutInflater;
	private int layoutInt;
	private List<MatchTO> list; 
	private Button button;
	
	public Button getButton() {
		return button;
	}

	public MatchesAdapter(Context context, List<MatchTO> list,int layoutInt) {
		this.list = list;
		this.layoutInt = layoutInt;
		this.layoutInflater = LayoutInflater.from(context);
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
			convertView = layoutInflater.inflate(layoutInt, null);
			

			TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
			tv_1.setText(
					  "��" 
					+ list.get(position).getRound()
					+ "��"
					+ "    " 
					+ "JAVA�����"
					+ " "
					+ list.get(position).getScore()
					+ " "
					+ list.get(position).getCompetitor());
						
		}
		
		Button button = (Button)convertView.findViewById(R.id.button_matchDel);
		button.setBackgroundResource(R.drawable.button);
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
