package com.android.club;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.android.base.ConstantVariable;

import android.util.Xml;

public class Club {
	
	
	public String clubName;
	public List<Player> playerAList = new ArrayList<Player>();
	public List<Player> playerBList = new ArrayList<Player>();
	
	public Club(String clubName){
		this.clubName = clubName;
	}

	public void initClub(){
		
		//��ȡ�����ļ�
		Player player = null;
        InputStream inputStream=null;    
        //���XmlPullParser������
        XmlPullParser xmlParser = Xml.newPullParser();   
        try {
            //�õ��ļ����������ñ��뷽ʽ
            inputStream=this.getClass().getClassLoader().getResourceAsStream("club.xml");
            xmlParser.setInput(inputStream, "utf-8");
            //��ý��������¼���������п�ʼ�ĵ��������ĵ�����ʼ��ǩ��������ǩ���ı��ȵ��¼���
            int evtType=xmlParser.getEventType();
         
            //һֱѭ����ֱ���ĵ�����    
			while (evtType != XmlPullParser.END_DOCUMENT) {
				switch (evtType) {
				case XmlPullParser.START_TAG:
					if(xmlParser.getName().equals(ConstantVariable.roleMember)){
						String role = xmlParser.getAttributeValue(1);
						
						if(role.equals(ConstantVariable.rolePlayer)){
							player = new Player();
							String number = xmlParser.getAttributeValue(0);
							player.setNumber(number);
						} 
					}
					if(player!=null
							&&xmlParser.getName().equals(ConstantVariable.playerPosition)){
						player.setPosition(xmlParser.nextText());
					}
					if(player!=null
							&&xmlParser.getName().equals(ConstantVariable.playerDescription)){
						player.setDescription(xmlParser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					if(xmlParser.getName().equals(ConstantVariable.roleMember)){
						if(player!=null){
							playerAList.add(player);
							player = null;
						}
					}
					break;
				}
				// ���xmlû�н������򵼺�����һ���ڵ�
				evtType = xmlParser.next();
			}
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
