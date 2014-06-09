package com.cf.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;

import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.cf.activity.FormationActivity;
import com.cf.activity.TacticalActivity;
import com.cf.base.BitmapUtil;
import com.cf.base.ConstantVariable;
import com.cf.club.Club;
import com.cf.club.Player;
import com.cf.club.Point;


public class FootballFieldView extends SurfaceView {
	
	private int viewHeight;
	private int viewWidth;
	
	private float currentPointX;
	private float currentPointY;
	
	
	
	// STANDARD
	final int STANDARD_WIDTH = 480;
	final int STANDARD_HEIGHT = 778;
    
    //���ʶ���
  	private final Paint paint = new Paint(Paint.DITHER_FLAG);
  	
  	//���С
    private static int pointSize = 38;
    //��ͬ��ɫ������
  	private Bitmap[] playerAPointArray;
  	private Bitmap[] playerBPointArray;
  	
  	
    private Context context=null;
    
    
    private Club club;

    private List <Point> pointList;
    
    private List <Player> playerAList;
    private List <Player> playerBList;
    
    private Point football;

    private int stepsTotal = 0;
    private int stepNum = 0;
    private String showDisplay;//1.start 2.pause 3.stop
    
    
    
    private Toast toast;
    
    
    
	public String getShowDisplay() {
		return showDisplay;
	}

	public void setShowDisplay(String showDisplay) {
		this.showDisplay = showDisplay;
	}


	//����
//    class Line{
//    	float xStart,yStart,xStop,yStop;
//    	
//		public Line(float xStart, float yStart, float xStop, float yStop) {
//			this.xStart = xStart;
//			this.yStart = yStart;
//			this.xStop = xStop;
//			this.yStop = yStop;
//		}
//    }
    
    
    //���캯��
   	public FootballFieldView(final Context context, AttributeSet attrs) {
   		
		super(context, attrs);
		this.context =context;
		setFocusable(true);//ʹView��ȡ����
		
		setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Log.d("Log Info", "*****" + "onLongClick");
				
				toast = Toast.makeText(context,
					    "�ֱ���:" + viewWidth +"*" + viewHeight + "\n" +
						"x:" + currentPointX + "\n" +
						"y:" + currentPointY, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return false;
			}
		});
		
		
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.d("Log Info", "*****" + "onTouch:" + event.getAction());
				
				currentPointX = event.getX();
				currentPointY = event.getY();
				
					
				return onTouchView(event);
			}
		});
		
		
        if(playerAList == null){
        	if(club == null){
        		club = new Club(ConstantVariable.SYSBOL_DOUBLE_QUOTES);
        	}
        	
        	//��ʼ����Ա������Ϣ
        	club.initClub();
        	playerAList = club.playerAList;
		}
	}
   	
   	//�Զ�ƥ�䲻ͬ�ֱ����µ����Ӵ�С
    public void autoChangeSize (int h){

		if (1800 < h && h < 2000) {// 1920
			pointSize = 95;
		} else if (1100 < h && h < 1800) {// 1280
			pointSize = 70;
		} else if (900 < h && h < 1100) {// 960
			pointSize = 55;
		} else if (700 < h && h < 900) {// 800
			pointSize = 38;
		} else {
			pointSize = 24;
		}
    }
   	
	//���¿�ʼ
	public void restart_Club(){
		restart();
	}
	
	@Override
	protected void onSizeChanged(int width, int height, int oldw, int oldh) {
		viewWidth = width;
		viewHeight = height;
		Log.d("Log Info", "*****" + "�ֱ���:" + width +"*" + height + "*****");
		autoChangeSize(height);	//�Զ�ƥ�䲻ͬ�ֱ����µĴ�С
		
		// �õ��ֱ��ʺ����趨��ʼλ��
		if(TacticalActivity.formation!=null){
			initPlayersPosition("player",TacticalActivity.formation);
		}else{
			initPlayersPosition("player",ConstantVariable.formation331);
		}
		
    	initPlayersPosition("playerB",ConstantVariable.SYSBOL_DOUBLE_QUOTES);
    	
    	football = new Point();
    	football.setPointX(222*viewWidth/STANDARD_WIDTH);
       	football.setPointY(367*viewHeight/STANDARD_HEIGHT);
	}
	
	/*���� bitmap
	 * 
	 * Type:Round,RoundedRectangle
	 */
    public Bitmap setBitmapShape(Drawable drawable,String type) {
    	
        Bitmap bitmap = Bitmap.createBitmap(pointSize, pointSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, pointSize, pointSize);
        drawable.draw(canvas);
        
        /*
         * API:
        if(type.equals(ConstantVariable.BITMAP_TYPE_ROUND)){
            return BitmapUtil.toRoundBitmap(bitmap,BitmapUtil.TYPE_ROUND);
        }else{
        	return BitmapUtil.createFramedPhoto(bitmap, 10, 10);//����Bitmap��������
        }
        */
        
        return BitmapUtil.toRoundBitmap(bitmap,BitmapUtil.TYPE_ROUND);
    }
    
    
    
    
	public void refressCanvas(){
		//����onDraw����
        invalidate();
	}
	
	//���¿�ʼ
	private void restart() {
		//ˢ��һ��
		refressCanvas();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
    	//����ԱͼƬ
		BindBitmap(playerAList,ConstantVariable.BITMAP_TYPE_ROUND);
		BindBitmap(playerBList,ConstantVariable.BITMAP_TYPE_ROUND_RECTANGLE);
    			
		// ��Ա
		drawTeam(playerAList,playerAPointArray,canvas);
		// ����
		drawTeam(playerBList,playerBPointArray,canvas);
		// ����
		drawFootball(canvas);
		
		
		
		// ���
//		paint.setColor(Color.RED);
//		canvas.drawPoint((float)20.0, (float)30.0,paint);
//		canvas.drawPoint((float)25.0, (float)30.0,paint);
//		canvas.drawPoint((float)30.0, (float)30.0,paint);
//		canvas.drawPoint((float)35.0, (float)30.0,paint);
//		canvas.drawPoint((float)40.0, (float)30.0,paint);
//		canvas.drawPoint((float)45.0, (float)30.0,paint);
//		canvas.drawPoint((float)45.0, (float)35.0,paint);
//		canvas.drawPoint((float)45.0, (float)40.0,paint);
		
	}
	
	public void drawFootball(Canvas canvas){
	
		Resources resources = this.getContext().getResources();
       	int id = context.getResources().getIdentifier("football", 
       			"drawable" , context.getPackageName());  

       	Bitmap footballBitmap = setBitmapShape(resources.getDrawable(id),ConstantVariable.BITMAP_TYPE_ROUND);
        
        drawPoint(canvas,footballBitmap,football.getPointX(),football.getPointY());
	}
	
	public void drawPoint(Canvas canvas,Bitmap bitmap,int x,int y){
		canvas.drawBitmap(bitmap,x,y, paint);
	}
	
	
	public void drawTeam(List<Player> list,Bitmap[] bitmap,Canvas canvas){
		for (int i = 0; i < list.size(); i++) {
			Point player = list.get(i);
			drawPoint(canvas,bitmap[i], 
					player.getPointX(), player.getPointY());
		}
	}
	
	public void BindBitmap(List<Player> list,String type){
   		Resources resources = getContext().getResources();
   		if(type.equals(ConstantVariable.BITMAP_TYPE_ROUND)){
   			playerAPointArray = new Bitmap[list.size()];
   	        for(int i=0;i<list.size();i++){
   	        	Player player = list.get(i);
   	        	
   	        	// �õ���̬��id
   	        	int id = context.getResources().getIdentifier(ConstantVariable.PLAY_TYPE_A_+player.getNumber(), 
   	        			"drawable" , context.getPackageName());  
   	        	playerAPointArray[i] = setBitmapShape(resources.getDrawable(id),type);
   	        }
   		}else{
   			playerBPointArray = new Bitmap[list.size()];
   	        for(int i=0;i<list.size();i++){
   	        	// �õ���̬��id
   	        	int id = context.getResources().getIdentifier(ConstantVariable.PLAY_TYPE_B_+"0"+i, 
   	        			"drawable" , context.getPackageName());  
   	        	playerBPointArray[i] = setBitmapShape(resources.getDrawable(id),type);
   	        }
   		}
   	}
	
	
	/*public void playerBBitmap(Bitmap[]bitmaps,List<Player> list,String role){
   		Resources r = this.getContext().getResources();
   		bitmaps = new Bitmap[list.size()];
   		String iString = "" ;
        for(int i=0;i<list.size();i++){
        	Player player = list.get(i);
        	
        	if(i<9){
        		iString = "0" + i;
        	}else{
        		iString = i + "";
        	}
        	// �õ���̬��id
        	int id = context.getResources().getIdentifier(role+iString, 
        			"drawable" , context.getPackageName());  
        	fillPointArrays(i,r.getDrawable(id),bitmaps);
        }
   	}*/
	/*public void drawLine(Canvas canvas){
		// ��Ļ�߿�
		paint.setColor(Color.WHITE);
		// �������£������Ͻ�Ϊԭ��
		canvas.drawLine(0 + margins, 0 + margins, 0 + margins, screenHeight - margins, paint);
		canvas.drawLine(screenWidth - margins, 0 + margins, screenWidth - margins, screenHeight - margins, paint);
		canvas.drawLine(0 + margins, 0 + margins, screenWidth - margins, 0 + margins, paint);
		canvas.drawLine(0 + margins, screenHeight-margins, screenWidth - margins, screenHeight - margins, paint);
		
		
		// ��������
		canvas.drawLine(0 + margins, screenHeight/2, screenWidth - margins, screenHeight/2, paint);
		canvas.drawLine(screenWidth/2, 0+margins, screenWidth/2, screenHeight-margins, paint);
		
		
		// ��Ϣ�������������½ǣ�
		// ��������
		paint.setColor(Color.RED);
		canvas.drawLine(screenWidth - margins*2, screenHeight/2, screenWidth - margins*2, screenHeight - margins*2, paint);
		canvas.drawLine(screenWidth - margins*0, screenHeight/2, screenWidth - margins*0, screenHeight - margins*2, paint);
		canvas.drawLine(screenWidth - margins*2, screenHeight/2, screenWidth - margins*0, screenHeight/2, paint);
		canvas.drawLine(screenWidth - margins*2, screenHeight - margins*2, screenWidth - margins*0, screenHeight - margins*2, paint);
		
		
		paint.setColor(Color.WHITE);
	}
	*/
	
	/*
	 *	�趨λ��
	 */
	/*
	public void initMembersPosition(int pointXStart,int pointYStart,int pointSize){
		// ����
//		int coachSize = coachList.size();
//		List <Coach> tempCoachList = new ArrayList<Coach>();
//		for(int i=0;i<coachSize;i++){
//			Coach coach = coachList.get(i);
//			
//			coach.setPointX(pointXStart);
//			coach.setPointY(pointYStart - (i+1) * pointSize);
//			
//			tempCoachList.add(coach);
//		}
//		// ˢ��λ��
//		coachList.clear();
//		coachList = tempCoachList;
		
		// ��Ա
		int playerSize = playerAList.size();
		List <Player> tempplayerAList = new ArrayList<Player>();
		for(int i=0;i<playerSize;i++){
			Player player = playerAList.get(i);
			
			player.setPointX(pointXStart);
			player.setPointY(i * pointSize + pointYStart);
			
			tempplayerAList.add(player);
		}
		
		// ˢ��λ��
		playerAList.clear();
		playerAList = tempplayerAList;
		
	}*/
	
	
	/**
	 * 
	 * @param formation
	 */
	public void initPlayersPosition(String role,String formation){
		int playerposition[][] = {};
		
		if(role.equals("player")){
			
			if(formation.equals(ConstantVariable.formation331)){
				playerposition = ConstantVariable.playerPosition331;
			}else if(formation.equals(ConstantVariable.formation322)){
				playerposition = ConstantVariable.playerPosition322;
			}else if(formation.equals(ConstantVariable.formation232)){
				playerposition = ConstantVariable.playerPosition232;
			}else if(formation.equals(ConstantVariable.formation241)){
				playerposition = ConstantVariable.playerPosition241;
			}else if(formation.equals(ConstantVariable.formation421)){
				playerposition = ConstantVariable.playerPosition421;
			}
			if(playerAList==null){
				playerAList = new ArrayList<Player>();
			}
			for(int i=0;i<8;i++){
				playerAList.get(i).setPointX(playerposition[i][0]*viewWidth/STANDARD_WIDTH);
				playerAList.get(i).setPointY(playerposition[i][1]*viewHeight/STANDARD_HEIGHT);
			}
		}else{
			if(playerBList==null){
				playerBList = new ArrayList<Player>();
			}
			playerBList.clear();
			playerposition = ConstantVariable.playerBPosition331;
			for(int i=0;i<8;i++){
				Player player = new Player();
				player.setPointX(playerposition[i][0]*viewWidth/STANDARD_WIDTH);
				player.setPointY(playerposition[i][1]*viewHeight/STANDARD_HEIGHT);
				playerBList.add(player);
			}
		}
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//	}
	
	
	public boolean onTouchView(MotionEvent event){
			
		Point playerSelected = null;
		int touchX  = (int)event.getX();
		int touchY =  (int)event.getY();
		
		// λ����Ϣ
		Log.d("INFO", "x:" + touchX + "----" + "y:" + touchY);
		
		// playerAList
		for(int i=0;i<playerAList.size();i++){
			Point player = playerAList.get(i);
			// ͨ��λ�ã��жϱ�ѡ�е���Ա
			if(touchX >= player.getPointX() 
					&& touchX <= player.getPointX() + pointSize
					&& touchY >= player.getPointY() 
					&& touchY <= player.getPointY() + pointSize){
				playerSelected = player;
			}
		}
		
		// playerBList
		for(int i=0;i<playerBList.size();i++){
			Point player = playerBList.get(i);
			if(touchX >= player.getPointX() 
					&& touchX <= player.getPointX() + pointSize
					&& touchY >= player.getPointY() 
					&& touchY <= player.getPointY() + pointSize){
				
				// ͨ��λ�ã��жϱ�ѡ�е���Ա
				playerSelected = player;
			}
		}
		
		// football
		if(touchX >= football.getPointX() 
				&& touchX <= football.getPointX() + pointSize
				&& touchY >= football.getPointY() 
				&& touchY <= football.getPointY() + pointSize){
			playerSelected = football;
		}
		
		// �ƶ�
		boolean canMove = true;;
		if(playerSelected!=null){
			
            // 1.�ƶ�����1���߽�
			if(!(touchX - pointSize/2 > 0
					&& touchX + pointSize/2 < this.viewWidth)){
				canMove = false;
			}
			if(!(touchY - pointSize/2 > 0
					&& touchY + pointSize/2 < this.viewHeight)){
				canMove = false;
			}
			
			// �Ƿ񸲸�
			if(canMove){
				if(pointList==null){
					pointList = new ArrayList<Point>();
					pointList.addAll(playerAList);
					pointList.addAll(playerBList);
					pointList.add(football);
				}		
				canMove = !isOver(pointList,playerSelected,touchX,touchY);
			}
			
			// �����ƶ�
			if(canMove){
				// ������λ������
				{
					playerSelected.setPointX((int)touchX - pointSize/2);
					playerSelected.setPointY((int)touchY - pointSize/2);
				}
				
				// ˢ�� �ػ�λ��
				refressCanvas();
				
				// ��¼�ƶ�λ��
				{
					String stepX = playerSelected.getSteps()[0];
					String stepY = playerSelected.getSteps()[1];
					if(showDisplay!=null
							&&showDisplay.equals(ConstantVariable.menuStart)
							){
						if(stepX!=null){
							String[] step = {stepX + ";" + playerSelected.getPointX(),
									stepY + ";" + playerSelected.getPointY()};
							playerSelected.setSteps(step);
						}else{
							// init step
							String[] step = {playerSelected.getPointX()+ConstantVariable.SYSBOL_DOUBLE_QUOTES,
									playerSelected.getPointY()+ConstantVariable.SYSBOL_DOUBLE_QUOTES};
							playerSelected.setSteps(step);
						}
						stepNum++;
					}
				}
			}
			
			if(event.getAction() == MotionEvent.ACTION_UP){
				playerSelected = null;
				if(stepNum > stepsTotal){
					stepsTotal = stepNum;
				}
			}
		}else{
			canMove = false;
		}
		
		return canMove;
	}
	

	
	
	/*
 	 *	����ս��
 	 */
 	public void displayTactics(){
 		new RefreshThread().start();
 	}
 	
	public class RefreshThread extends Thread {

		@Override
		public void run() {

			try {
	 			if(pointList!=null){
	 				for(int i=0;i<stepsTotal;i++){
	 					// �õ���Ա�ƶ��켣
		 				for(int j=0;j<pointList.size();j++){
		 					Point point = pointList.get(j);
		 					//steps
		 					String steps[] = point.getSteps();
			 				if(steps != null){
			 					String stepX = steps[0];
			 	 				String stepY = steps[1];
			 	 				
			 	 				if(stepX !=null
			 	 						&&!stepX.equals(ConstantVariable.SYSBOL_DOUBLE_QUOTES)){
			 	 					String pointX[] = stepX.split(ConstantVariable.SYSBOL_SEMICOLON);
			 	 					String pointY[] = stepY.split(ConstantVariable.SYSBOL_SEMICOLON);
			 	 					
			 	 					// �ƶ��켣
			 	 					if(pointX.length>i){
			 	 						point.setPointX(Integer.valueOf(pointX[i]));
			 	 						point.setPointY(Integer.valueOf(pointY[i]));
			 	 					}
			 	 				}
			 				}
		 				}
	 					
	 					// ˢ��
	 					Thread.sleep(200);
						try {
							postInvalidate();
						} catch (Exception e) {
							e.printStackTrace();
						}
	 				}
	 				
	 				// 
	 				if(stepsTotal>0){
//	 					Toast.makeText(context, "��ʾ�����", Toast.LENGTH_SHORT );
	 					Looper.prepare();
	 					new  AlertDialog.Builder(context)    
					    .setTitle("��ʾ��" )
					    .setMessage("�������ʾ!" )
					    .setPositiveButton("ȷ��" ,null).show();  
	 					Looper.loop(); 
	 				}
	 			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * �ж��Ƿ��ص�
	 */
	public boolean isOver(List<Point> playerList,Point playerSelected,int touchX,int touchY){
		for(int i=0;i<playerList.size();i++){
			// Playerת��Point
			Point point = playerList.get(i);
			if(point!=playerSelected){
				int playerX = point.getPointX() + pointSize/2;
				int playerY = point.getPointY() + pointSize/2;
				int distanceX = playerX - touchX;
				int distanceY = playerY - touchY;
				//  ���ɶ���
				if(distanceX * distanceX + distanceY * distanceY < pointSize * pointSize){
					return true;
				}
			}
		}
		return false;
	}

}
