package com.android.module.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.base.BitmapUtil;
import com.android.club.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {
	private ImageView[] mImages;		// ���浹ӰͼƬ������

	private Context mContext;
	public List<Map<String, Object>> list;
	

	public Integer[] imgs = { R.drawable.navigation_01, R.drawable.navigation_02, R.drawable.navigation_03,
							  R.drawable.navigation_04, R.drawable.navigation_05, R.drawable.navigation_06, R.drawable.navigation_07};
	

	public ImageAdapter(Context c) {
		this.mContext = c;
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imgs.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", imgs[i]);
			list.add(map);
		}
		mImages = new ImageView[list.size()];
	}

	/** ���䵹Ӱ */
	public boolean createReflectedImages() {
		final int reflectionGap = 4;
		//////////////////////////////////////////////
		final int Height = 180;
		int index = 0;
		for (Map<String, Object> map : list) {
			Integer id = (Integer) map.get("image");
			
			// original
			Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), id);	// ��ȡԭʼͼƬ
			originalImage = BitmapUtil.createFramedPhoto(originalImage,5,5);
			
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			float scale = Height / (float)height;
			
			Matrix sMatrix = new Matrix();
			sMatrix.postScale(scale, scale);
			
			// mini
			Bitmap miniBitmap = Bitmap.createBitmap(originalImage, 0, 0,
					originalImage.getWidth(), originalImage.getHeight(), sMatrix, true);
			miniBitmap = BitmapUtil.createFramedPhoto(miniBitmap,20,20);

			int mwidth = miniBitmap.getWidth();
			int mheight = miniBitmap.getHeight();
			
			
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);			// ͼƬ����任���ӵͲ��򶥲��ĵ�Ӱ��
			
			// reflection
			Bitmap reflectionImage = Bitmap.createBitmap(miniBitmap, 0, mheight/2, mwidth, mheight/2, matrix, false);	// ��ȡԭͼ�°벿��
			
			// mini + reflection  
			Bitmap bitmapWithReflection = Bitmap.createBitmap(mwidth, (mheight + mheight / 2), Config.ARGB_8888);			// ������ӰͼƬ���߶�Ϊԭͼ3/2��

			Canvas canvas = new Canvas(bitmapWithReflection);	// ���Ƶ�Ӱͼ��ԭͼ + ��� + ��Ӱ��
			canvas.drawBitmap(miniBitmap, 0, 0, null);		// ����ԭͼ
			
			
			Paint paint = new Paint();
			canvas.drawRect(0, mheight, mwidth, mheight + reflectionGap, paint);		// ����ԭͼ�뵹Ӱ�ļ��
			canvas.drawBitmap(reflectionImage, 0, mheight + reflectionGap, null);	// ���Ƶ�Ӱͼ

			paint = new Paint();
			LinearGradient shader = new LinearGradient(0, miniBitmap.getHeight(), 0, bitmapWithReflection.getHeight()
					+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			paint.setShader(shader);	// ���Խ���Ч��
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));		// ��Ӱ����Ч��
			canvas.drawRect(0, mheight, mwidth, bitmapWithReflection.getHeight() + reflectionGap, paint);		// ���Ƶ�Ӱ����ӰЧ��

			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection);		// ���õ�ӰͼƬ
//			imageView.setImageBitmap(BitmapUtil.createFramedPhoto(bitmapWithReflection, 20, 20));		// ���õ�ӰͼƬ
			
			imageView.setLayoutParams(new GalleryView.LayoutParams((int)(width * scale),
					(int)(mheight * 3 / 2.0 + reflectionGap)));
			imageView.setScaleType(ScaleType.MATRIX);
			mImages[index++] = imageView;
		}
		return true;
	}

	@Override
	public int getCount() {
		return imgs.length;
	}

	@Override
	public Object getItem(int position) {
		return mImages[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mImages[position];		// ��ʾ��ӰͼƬ����ǰ��ȡ���㣩
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}
