package com.cf.adapter;

import java.util.List;

import com.android.club.R;
import com.cf.activity.MatchesActivity;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class MatchesMatchDetailAdapter extends BaseAdapter{
	
	/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
	private List<MatchTO> list; 
	private Context context;

	public MatchesMatchDetailAdapter(Context context, List<MatchTO> list) {
		this.list = list;
		this.context = context;
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
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_matches_match_detail, null);
		}
		
		ImageView imageView = (ImageView) convertView.findViewById(R.id.img_goal);
		int id = convertView.getResources().getIdentifier(
				"img_goal", "drawable" , context.getPackageName()); 
		imageView.setBackgroundResource(id);
		
		TextView textviewGoalPlayer = (TextView) convertView.findViewById(
				R.id.textview_goal_player);
		textviewGoalPlayer.setText("2��");
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
