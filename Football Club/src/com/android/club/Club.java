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
		
		//读取配置文件
		Player player = null;
        InputStream inputStream=null;    
        //获得XmlPullParser解析器
        XmlPullParser xmlParser = Xml.newPullParser();   
        try {
            //得到文件流，并设置编码方式
            inputStream=this.getClass().getClassLoader().getResourceAsStream("club.xml");
            xmlParser.setInput(inputStream, "utf-8");
            //获得解析到的事件类别，这里有开始文档，结束文档，开始标签，结束标签，文本等等事件。
            int evtType=xmlParser.getEventType();
         
            //一直循环，直到文档结束    
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
				// 如果xml没有结束，则导航到下一个节点
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
