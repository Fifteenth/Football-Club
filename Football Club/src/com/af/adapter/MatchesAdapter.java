package com.af.adapter;

import java.util.List;

import com.af.activity.MatchesActivity;
import com.android.club.R;
import com.cf.support.MatchesSupport;
import com.cf.to.MatchTO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class MatchesAdapter extends BaseAdapter{
	
	/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
	private LayoutInflater layoutInflater;
	private int layoutInt;
	private List<MatchTO> list; 
	private Button button;
	private MatchesActivity matchesActivity;
	
	Button buttonMatchDel;
	
	public Button getButton() {
		return button;
	}

	public MatchesAdapter(Context context, List<MatchTO> list,int layoutInt) {
		this.list = list;
		this.layoutInt = layoutInt;
		this.layoutInflater = LayoutInflater.from(context);
		this.matchesActivity = (MatchesActivity)context;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
		}
		
//		convertView.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//
//				if(event.getAction() == MotionEvent.ACTION_DOWN){
//					System.out.println("----------------------Adapt:ACTION_DOWN");
//					return false;
//				}
//				if(event.getAction() == MotionEvent.ACTION_UP){
//					System.out.println("----------------------ACTION_UP");
//					return false;
//				}
//				return false;
//			}
//		});
		
		// Bind Data
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
		
		buttonMatchDel = (Button)convertView.findViewById(R.id.button_matchDel);
		buttonMatchDel.setBackgroundResource(R.drawable.button_delete);
		// Del Match
		buttonMatchDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// AvoidResponseOnTouch
//				matchesActivity.setAvoidResponseOnTouch(true);
				// Remove
				list.remove(position);
				
				// Reflesh
				notifyDataSetChanged();
				
				// hidden Button
				matchesActivity.hiddenRight(null);
				
				// Save
				MatchesSupport.WriteMatches(list);
			}
		});
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
