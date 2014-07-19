package com.af.view;

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
import android.view.MotionEvent;

import android.view.SurfaceView;
import android.view.View;
import android.widget.ListView;

import com.af.activity.TacticalActivity;
import com.as.view.FootballFieldViewSupport;
import com.cf.base.util.BitmapUtil;
import com.cf.base.util.DateUtil;
import com.cf.base.variable.ConstantVariable;
import com.cf.base.variable.SystemVariable;
import com.cf.club.Player;
import com.cf.fix.GlobleTacticalClass;
import com.cf.to.TacticalFileTO;
import com.cf.to.TacticalSettingTO;


public class FootballFieldView extends SurfaceView {
	
	// Screen
	private int viewHeight;
	private int viewWidth;
	
	private float coefficientHeight;
	private float coefficientWidth;
	
	// Current Point
	private float currentPointX;
	private float currentPointY;
	
    //���ʶ���
  	private final Paint paint = new Paint(Paint.DITHER_FLAG);
  	
  	//���С
    private static int pointSize = 38;
  	
    private Context context=null;
    private String showDisplay;//1.start 2.pause 3.stop

    private int stepsTotal = 0;
	private int stepNum = 0;
	
	private List <Player> playerList;
    private List <Player> playerAList;
	private List <Player> playerBList;
	private Player football;
	
	private boolean isIntercept = false;// ������
	private boolean isSlide = false;// ������
	
    private int moveAngle = 0;
    
    private Long lastActionTime;
    private Long currentActionTime;
    
	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	
    public Player getFootball() {
		return football;
	}

	public void setFootball(Player football) {
		this.football = football;
	}

    public int getStepsTotal() {
		return stepsTotal;
	}

	public void setStepsTotal(int stepsTotal) {
		this.stepsTotal = stepsTotal;
	}


	public String getShowDisplay() {
		return showDisplay;
	}

	public void setShowDisplay(String showDisplay) {
		this.showDisplay = showDisplay;
	}

    public List<Player> getPlayerAList() {
		return playerAList;
	}

	public void setPlayerAList(List<Player> playerAList) {
		this.playerAList = playerAList;
	}

	public List<Player> getPlayerBList() {
		return playerBList;
	}

	public void setPlayerBList(List<Player> playerBList) {
		this.playerBList = playerBList;
	}

    //���캯��
   	public FootballFieldView(final Context context, AttributeSet attrs) {
   		
		super(context, attrs);
		this.context =context;
		
		// Listener
		registerListener();
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
		// �õ���Ļ�ֱ���
		viewWidth = width;
		viewHeight = height;
		
		// ��Ӧ���ֱַ��� ϵ��
		coefficientWidth = (float)viewWidth/(float)SystemVariable.STANDARD_WIDTH;
		coefficientHeight = (float)viewHeight/(float)SystemVariable.STANDARD_HEIGHT;
		
//		Log.d("Log Info", "*****" + "�ֱ���:" + width +"*" + height + "*****");
		autoChangeSize(height);	//�Զ�ƥ�䲻ͬ�ֱ����µĴ�С
	}
	
	/*���� bitmap
	 * 
	 * Type:Round,RoundedRectangle
	 */
    public Bitmap drawBitmapForType(Drawable drawable,String type) {
    	
        Bitmap bitmap = Bitmap.createBitmap(pointSize, pointSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, pointSize, pointSize);
        drawable.draw(canvas);
        // Draw Circle
        return BitmapUtil.toRoundBitmap(bitmap,type);
    }
    
    /**
     * �ػ����
     */
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
		
		// Init
		// playerA
		{
			if(playerAList == null){
				// Player A
		        playerAList = GlobleTacticalClass.playerAList;
				GlobleTacticalClass.initPlayerAPosition(coefficientWidth, coefficientHeight);
				listBindBitmap(playerAList,ConstantVariable.PLAY_TYPE_A_,
						ConstantVariable.BITMAP_TYPE_ROUND);
			}
			drawTeam(playerAList,canvas);
		}
		
		// playerB
		if(GlobleTacticalClass.isDisplayCompetitor().equals("true")){
			if(playerBList == null){
				playerBList = GlobleTacticalClass.buildPlayerBList(coefficientWidth, coefficientHeight);
				listBindBitmap(playerBList,ConstantVariable.PLAY_TYPE_B_,ConstantVariable.BITMAP_TYPE_ROUND);
			}
			drawTeam(playerBList,canvas);
		}
    	
		// football
		{
			if(football == null){
				football = GlobleTacticalClass.buildFootball(coefficientWidth, coefficientHeight);
				// Bind
				bindBitmap(football, ConstantVariable.PLAY_TYPE_C_, ConstantVariable.BITMAP_TYPE_ROUND);
			}
			drawPlayer(canvas,football);
		}
		
		// Init PlayerList
		if(playerList == null){
			playerList = new ArrayList<Player>();
			playerList.addAll(playerAList);
			if(GlobleTacticalClass.isDisplayCompetitor().equals("true")){
				playerList.addAll(playerBList);
			}
			playerList.add(football);
		}
	}
	
	public void drawTeam(List<Player> list,Canvas canvas){
		for (int i = 0; i < list.size(); i++) {
			Player player = list.get(i);
			drawPlayer(canvas,player);
		}
	}
	
	/**
	 * 
	 */
	public void drawPlayer(Canvas canvas,Player player){
		// ��ѡ����Ա
		canvas.drawBitmap(player.getPlayerBitmap(),
				player.getPointX(), player.getPointY(),paint);
		
		// ѡ��Ч��
		if(player.gotIsSelect()){
			FootballFieldViewSupport.selectedEffect(canvas,player,pointSize,moveAngle);
		}
	}
	
	public void listBindBitmap(List<Player> list,String playerRole,String bitmapForType){
        for(int i=0;i<list.size();i++){
        	Player player = list.get(i);
        	bindBitmap(player,playerRole,bitmapForType);
        }
   	}
	
	public void bindBitmap(Player player,String playerRole,String bitmapForType){
		Resources resources = getContext().getResources();
		// �õ���̬��id
    	int id = context.getResources().getIdentifier(playerRole
    			+ player.getNumber(), "drawable" , context.getPackageName());  
    	player.setPlayerBitmap(drawBitmapForType(resources.getDrawable(id),bitmapForType));
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//	}
	
	
	/**
	 * 
	 */
	public boolean onTouchView(MotionEvent event){
			
		int touchX  = (int)event.getX();
		int touchY =  (int)event.getY();
		
		// player Selected
		Player playerSelected = gotSelectedPlayer(touchX,touchY);
		
		// �ƶ�
		boolean canMove = true;;
		if(playerSelected!=null){
			// �ƶ�Ч��
			Boolean moveEffect[] = FootballFieldViewSupport.moveEffect(
					isIntercept,isSlide,event,playerSelected);
			if(moveEffect != null){
				// �õ��Ƿ�Ϊ����
				isSlide = moveEffect[2];
				if(moveEffect[0]){
					// ˢ��
					refressCanvas();
				}
				// ��true���أ����س����¼���
				if(moveEffect[1]){
					return true;
				}
			}
			
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
				if(playerList==null){
					playerList.addAll(playerAList);
					
					if(GlobleTacticalClass.isDisplayCompetitor().equals("true")){
						playerList.addAll(playerBList);
					}
					playerList.add(football);
				}		
				canMove = !isOver(playerList,playerSelected,touchX,touchY);
			}
			
			// �����ƶ�
			if(canMove){
				// ������λ������
				{
					playerSelected.setPointX((int)touchX - pointSize/2);
					playerSelected.setPointY((int)touchY - pointSize/2);
				}
				
				// ˢ�� �ػ�λ��
				{
				    currentActionTime = System.currentTimeMillis();
			    	
				    // ת��Ч����������ת��
			    	if(lastActionTime == null || DateUtil.gotTimedifferenceBySecond(
							currentActionTime,lastActionTime) > 0.1){
			    		moveAngle += 45;
			    		lastActionTime = currentActionTime;
			    	}
					refressCanvas();
				}
				
				// ��¼�ƶ�λ��
				{
					// �õ��켣
					String stepX = playerSelected.getSteps()[0];
					String stepY = playerSelected.getSteps()[1];
					if(showDisplay!=null
							&&showDisplay.equals(ConstantVariable.menuStart)
							){
						if(stepX!=null){	
							// �켣  + ��ǰ����
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
		
		// ����
		if(event.getAction() == MotionEvent.ACTION_UP){
			moveAngle = 0;
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
	 			if(playerList!=null){
	 				for(int i=0;i<stepsTotal;i++){
	 					
	 					// �õ���Ա�ƶ��켣
		 				for(int j=0;j<playerList.size();j++){
		 					Player player = (Player)playerList.get(j);
		 					//steps
			 				if(player.getStepX() != null
			 						){
			 					String stepX = player.getStepX();
			 	 				String stepY = player.getStepY();
			 	 				
			 	 				if(stepX !=null
			 	 						&&!stepX.equals(ConstantVariable.SYSBOL_DOUBLE_QUOTES)){
			 	 					String pointX[] = stepX.split(ConstantVariable.SYSBOL_SEMICOLON);
			 	 					String pointY[] = stepY.split(ConstantVariable.SYSBOL_SEMICOLON);
			 	 					
			 	 					// �趨�µ����꣬�ƶ�
			 	 					if(pointX.length>i){
			 	 						player.setPointX(Integer.valueOf(pointX[i]));
			 	 						player.setPointY(Integer.valueOf(pointY[i]));
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
	public boolean isOver(List<Player> playerList,Player playerSelected,int touchX,int touchY){
		for(int i=0;i<playerList.size();i++){
			Player player = (Player)playerList.get(i);
			if(player!=playerSelected){
				int playerX = player.getPointX() + pointSize/2;
				int playerY = player.getPointY() + pointSize/2;
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


	/*
	 * Register Listener
	 */
	private void registerListener(){
		
		// OnLongClickListener
		setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
				// Long -> ��ʾ
				TacticalActivity tacticalActivity = (TacticalActivity)context;
				ListView listview = tacticalActivity.getListView();
				listview.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		// OnTouchListener
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				currentPointX = event.getX();
				currentPointY = event.getY();
//				Log.d("Log Info", "*****" + "currentPointX:" + currentPointX);
//				Log.d("Log Info", "*****" + "currentPointY:" + currentPointY);
				
				// DOWN -> ����
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					TacticalActivity tacticalActivity = (TacticalActivity)context;
					ListView listview = tacticalActivity.getListView();
					listview.setVisibility(View.GONE);
					listview.setFocusable(true);
					listview.setFocusableInTouchMode(true);
				}
					
				return onTouchView(event);
			}
		});
	}
	
	// �����������
	public void dealWithFileData(){
		int totalStep = 0;
		List <TacticalFileTO> tacticalFileTOList = GlobleTacticalClass.tacticalFileTOList;
		for(int i=0;i<tacticalFileTOList.size();i++){
			TacticalFileTO tacticalFileTO = tacticalFileTOList.get(i);
			String role = tacticalFileTO.getRole();
			int stepNum = 0;
			if(role.equals("a")){
				stepNum = sotStepAndGotStepNum(tacticalFileTO,playerAList);
			}else if(role.equals("b")){
				stepNum = sotStepAndGotStepNum(tacticalFileTO,playerBList);
			}else{
				String stepX = tacticalFileTO.getStepX();
				stepNum = stepX.split(";").length;
				football.setStepX(stepX);
				football.setStepY(tacticalFileTO.getStepY());
			}
			
			if(totalStep < stepNum){
				totalStep = stepNum;
			}
		}
		
		stepsTotal = totalStep;
	}
	
	public int sotStepAndGotStepNum(TacticalFileTO tacticalFileTO,List<Player> list){
		int step = 0;
		for(int i=0;i<list.size();i++){
			Player player = list.get(i);
			if(tacticalFileTO.getNumber().equals(player.getNumber())){
				String stepX = tacticalFileTO.getStepX();
				String stepY = tacticalFileTO.getStepY();
				
				player.setStepX(stepX);
				player.setStepY(stepY);
				
				String steps [] = {stepX,stepY};
				player.setSteps(steps);
				
				step = stepX.split(";").length;
			}
		}
		return step;
	}
	
	public Player gotSelectedPlayer(int touchX,int touchY){
		
		// playerAList
		for(int i=0;i<playerAList.size();i++){
			Player player = playerAList.get(i);
			// ͨ��λ�ã��жϱ�ѡ�е���Ա
			if(touchX >= player.getPointX() 
					&& touchX <= player.getPointX() + pointSize
					&& touchY >= player.getPointY() 
					&& touchY <= player.getPointY() + pointSize){
				return player;
			}
		}
		
		// playerBList
		List <TacticalSettingTO>list = GlobleTacticalClass.gotTacticalSetting();
		if(list.get(0).getDisplayCompetitor().equals("true")){
			for(int i=0;i<playerBList.size();i++){
				Player player = playerBList.get(i);
				if(touchX >= player.getPointX() 
						&& touchX <= player.getPointX() + pointSize
						&& touchY >= player.getPointY() 
						&& touchY <= player.getPointY() + pointSize){
					
					// ͨ��λ�ã��жϱ�ѡ�е���Ա
					return player;
				}
			}
		}
		
		// football
		if(touchX >= football.getPointX() 
				&& touchX <= football.getPointX() + pointSize
				&& touchY >= football.getPointY() 
				&& touchY <= football.getPointY() + pointSize){
			return football;
		}
		
		return null;
	}
	
}
