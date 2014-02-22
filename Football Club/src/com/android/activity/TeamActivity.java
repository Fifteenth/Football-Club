package com.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.android.base.BitmapUtil;
import com.android.club.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �Զ���ListAdapter
 * 
 * @author Administrator
 * 
 */
public class TeamActivity extends Activity {

	String playerNames[] = {"����","2��","��ɿ�","�θ�","����","�⾭��","����","����","���"};
	String playerPositions[]={"BC","CF","BC","CF","DM","SB","CF","SB","SB"};
	List<DetailEntity> list = new ArrayList<DetailEntity>();
	
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.team);

		Resources resources = this.getResources();
		// ��ֵʵ�������
		for (int i = 0; i < 9; i++) {
			DetailEntity de_1 = new DetailEntity();
			de_1.setLayoutID(R.layout.myadatper);
			de_1.setText(playerNames[i]);
			de_1.setTitle(playerPositions[i]);
			String pngName = "player_avatar_0"+(i+1);
			int id = resources.getIdentifier(pngName, 
	       			"drawable" , getApplicationContext().getPackageName());  
			
			de_1.setBitmap(id);
			de_1.setBtnText("��ť" + i);
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
//				Toast.makeText(TeamActivity.this, "ѡ�е���:" + position,
//						Toast.LENGTH_SHORT).show();
				
				lv.setItemChecked(position, true);
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
				// ���ò�������
				ImageView iv = (ImageView) convertView.findViewById(R.id.img);
				iv.setBackgroundResource(list.get(position).getBitmap());
				
				Bitmap output = Bitmap.createBitmap(10, 10, Config.ARGB_8888);

				TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
				tv_1.setText(list.get(position).getTitle());

				TextView tv_2 = (TextView) convertView.findViewById(R.id.text);
				tv_2.setText(list.get(position).getText());
				
				
				ImageView ivc = (ImageView) convertView.findViewById(R.id.imgc);
				ivc.setBackgroundResource(R.drawable.player_caption);
				if(position!=0){
					ivc.setVisibility(View.INVISIBLE);
				}
				
//				Button btn = (Button) convertView.findViewById(R.id.button);

//				btn.setVisibility(View.INVISIBLE);
//				btn.setText(list.get(position).getBtnText());
//				btn.setFocusable(false);// ���ò����ս���,���㽻��View
//				btn.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						Toast.makeText(TeamActivity.this,
//								position + "�Ű�ť��������", Toast.LENGTH_SHORT).show();
//					}
//				});
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
