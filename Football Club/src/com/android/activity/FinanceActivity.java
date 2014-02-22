package com.android.activity;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FinanceActivity extends Activity{

	String playerNames[] = {"豪哥","2哥","大可可","涛哥","曹五","吴经理","海波","建华","叉哥"};
	
	String string = "余额:0$";
	List<DetailEntity> list = new ArrayList<DetailEntity>();
	
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_finance);

		Resources resources = this.getResources();
		// 赋值实体类对象
		for (int i = 0; i < 9; i++) {
			DetailEntity de_1 = new DetailEntity();
			de_1.setLayoutID(R.layout.listview_finance);
			de_1.setText(playerNames[i]);
			de_1.setTitle(string);
			String pngName = "player_avatar_0"+(i+1);
			int id = resources.getIdentifier(pngName, 
	       			"drawable" , getApplicationContext().getPackageName());  
			
			de_1.setBitmap(id);
			de_1.setBtnText("缴费");
			list.add(de_1);
		}

		lv = (ListView) this.findViewById(R.id.listView_my);

		// 实例化自定义适配器
		MyAdapter ma = new MyAdapter(this, list);

		lv.setAdapter(ma);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				lv.setItemChecked(position, true);
			}
		});
		
	}

	/**
	 * 自定义一个Adapter(实现了ListAdapter接口)
	 * 
	 * @author Administrator
	 * 
	 */
	class MyAdapter implements ListAdapter {

		private List<DetailEntity> list;

		/** 实例及其对应的视图布局的XML文件 */
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
		 * 控制ITEM 布局内容
		 */
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			if (convertView == null) {
				// 加载布局
				convertView = layoutInflater.inflate(list.get(position)
						.getLayoutID(), null);
				// 设置布局内容
				ImageView iv = (ImageView) convertView.findViewById(R.id.img);
				iv.setBackgroundResource(list.get(position).getBitmap());
				
				Bitmap output = Bitmap.createBitmap(10, 10, Config.ARGB_8888);

				TextView tv_1 = (TextView) convertView.findViewById(R.id.title);
				tv_1.setText(list.get(position).getTitle());

				TextView tv_2 = (TextView) convertView.findViewById(R.id.text);
				tv_2.setText(list.get(position).getText());
				
				Button btn = (Button) convertView.findViewById(R.id.button);
//				btn.setVisibility(View.INVISIBLE);
				btn.setText(list.get(position).getBtnText());
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
		 * 是否Item监听
		 */
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * true所有项目可选择可点击
		 */
		@Override
		public boolean areAllItemsEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		/**
		 * 是否显示分割线
		 */
		@Override
		public boolean isEnabled(int position) {
			// TODO Auto-generated method stub
			return true;
		}

	}

	/**
	 * ListView内容实体类
	 * 
	 * @author Administrator
	 * 
	 */
	class DetailEntity {
		/** 布局ID */
		private int layoutID;

		/** 图片ID */
		private int bitmap;

		/** 标题 */
		private String title;

		/** 内容 */
		private String text;

		/** 按钮名称 */
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
