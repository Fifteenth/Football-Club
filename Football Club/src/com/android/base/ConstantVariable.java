package com.android.base;

public class ConstantVariable {
	
	//menu
	public final static String menuStart = "start";
	public static String menuPause = "pause";
	public static String menuStop = "stop";
	public static String menuFormation ="formation";
	public static String menuTacticalDisplay = "display";
	
	public final static String menuStartDes = "��ʼ";
	public static String menuPauseDes = "��ͣ";
	public static String menuStopDes = "����";
	public static String menuFormationDes ="����";
	public static String menuTacticalDisplayDes = "ս������";
	
	public static String roleMember = "member";
	public static String rolePlayer = "player";
	
	public static String playerAttrName = "name";
	public static String playerPosition ="position";
	public static String playerDescription = "description";
	
	//fomation
	public static String formation331 = "3-3-1";
	public static String formation322 = "3-2-2";
	public static String formation232 = "2-3-2";
	public static String formation241 = "2-4-1";
	public static String formation421 = "4-2-1";
	
	//public static String formationB331 = "3-3-1";
	/*
	 * ���ϵ��£�������
	 * fomation-player-xy
	 */
	public static int playerPosition331[][]={
		{223,422},//cf
		{97,480},{223,519},{342,478},//lsmf,dm,rsmf
		{102,602},{222,615},{343,601},//lsb,cb,rsb
		{224,688}};//gk
	public static int playerPosition322[][]={
		{186,417},{271,418},//lcf,rcf
		{190,503},{264,522},//ldm,rdm
		{102,602},{222,615},{343,601},//lsb,cb,rsb
		{224,688}};//gk
	public static int playerPosition232[][]={
		{186,417},{271,418},//lcf,rcf
		{97,480},{223,519},{342,478},//lsmf,dm,rsmf
		{128,615},{266,613},
		{224,688}};//gk
	public static int playerPosition241[][]={
		{223,422},//cf
		{40,503},{128,503},{266,503},{340,503},
		{128,615},{266,613},
		{224,688}};//gk
	public static int playerPosition421[][]={
		{223,422},//cf
		{190,503},{264,522},//ldm,rdm
		{128,615},{190,613},{266,615},{340,615},
		{224,688}};//gk
	
	
	public static int playerBPosition331[][]={
		{223,60},//cf
		{97,160},{223,140},{342,160},//lsmf,dm,rsmf
		{102,240},{222,220},{343,240},//lsb,cb,rsb
		{224,300}};//gk
	
	public static String[] titles = { "��Ӽ��", "������Ϣ", "�������", "ս������", "ת���г�", "�������", "��ӹ���"};
	
	public static String BITMAP_TYPE_ROUND = "round";
	public static String BITMAP_TYPE_ROUND_RECTANGLE = "roundedRectangle";
	
	public static String PLAY_TYPE_A_ = "player_a_";
	public static String PLAY_TYPE_B_ = "player_b_";
	
	

}