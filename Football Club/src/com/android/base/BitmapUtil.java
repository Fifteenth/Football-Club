package com.android.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;  
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapUtil {
	public static String TYPE_ROUND = "round";
	public static String TYPE_OVAL = "oval";
	
	public static Bitmap toRoundBitmap(Bitmap bitmap,String roundType) {
		
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}
		
		if(!roundType.equals(TYPE_ROUND)){
			roundPx = 10;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0); 
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint); 
		return output;
	}
	
	
	
	public static Bitmap createFramedPhoto( Bitmap image, float rx,float ry) {  
        return createFramedPhoto(image,image.getWidth(),image.getHeight(),rx,ry);  
    } 
	
	
	public static Bitmap createFramedPhoto( Bitmap image, int right,int bottom,float rx,float ry) {  
        //根据源文件新建一个darwable对象  
        Drawable imageDrawable = new BitmapDrawable(image);  
        // 新建一个新的输出图片  
        Bitmap output = Bitmap.createBitmap(right, bottom, Bitmap.Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
  
        // 新建一个矩形  
        RectF outerRect = new RectF(0, 0, right, bottom);  
  
        // 产生一个红色的圆角矩形  
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  
        paint.setColor(Color.RED);  
        canvas.drawRoundRect(outerRect, rx, ry, paint);  
  
  
        // 将源图片绘制到这个圆角矩形上  
        //详解见http://lipeng88213.iteye.com/blog/1189452  
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));  
        imageDrawable.setBounds(0, 0, right, bottom);  
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);  
        imageDrawable.draw(canvas);  
        canvas.restore();  
  
        return output;  
    } 

}
