package com.android.adapter;

import java.util.List;

import com.android.club.R;
import com.android.to.TeamTO;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class TeamAdapter implements ListAdapter{
	
	/** ʵ�������Ӧ����ͼ���ֵ�XML�ļ� */
	private Context context;
	private LayoutInflater layoutInflater;
	private int layoutInt;
	private List<TeamTO> list; 
	private Resources resources;
	
	public TeamAdapter(Context context, List<TeamTO> list,int layoutInt,Resources resources) {
		this.context = context;
		this.layoutInflater = LayoutInflater.from(context);
		this.list = list;
		this.layoutInt = layoutInt;
		this.resources = resources;
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
			
			// Number Bitmap
			ImageView imageView = (ImageView) convertView.findViewById(R.id.image_team_number);
			
			String number = list.get(position).getNumber();
			String pngName;
			if(number.length()==1){
				pngName = "player_avatar_0" + number;
			}else{
				pngName = "player_avatar_" + number;
			}
			
			int id = resources.getIdentifier(pngName, "drawable" , context.getPackageName()); 
			
			imageView.setBackgroundResource(id);
			// Position
			TextView tv_1 = (TextView) convertView.findViewById(R.id.textview_team_position);
			tv_1.setText(list.get(position).getPosition());
			// Name
			TextView tv_2 = (TextView) convertView.findViewById(R.id.textview_team_name);
			tv_2.setText(list.get(position).getName());
			// Captain Bitmap
			ImageView ivc = (ImageView) convertView.findViewById(R.id.image_team_captain);
			ivc.setBackgroundResource(R.drawable.player_caption);
			if(position!=0){
				ivc.setVisibility(View.INVISIBLE);
			}
						
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
