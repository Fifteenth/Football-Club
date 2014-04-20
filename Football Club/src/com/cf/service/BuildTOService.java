package com.cf.service;

import java.lang.reflect.Method;

import com.cf.base.util.ReflectUtil;
import com.cf.dialog.MatchDialog;
import com.cf.to.MatchTO;

public class BuildTOService {
	
	public static Object buildTO(String classPahtDialog,String classPathTO,Object objectDialog){
		Object dynamicTO = null;
		
		try {
			Class<?> dynamicClassDialog = Class.forName(classPahtDialog);
			Class<?> dynamicClassTO = Class.forName(classPathTO);
			
			// TO
			dynamicTO = dynamicClassTO.newInstance();
			String methodParameters[] = {classPahtDialog};
			String setMethodNames[] = ReflectUtil.getAllSetOrGetMethodNames(
					ReflectUtil.METHOD_START_SET,methodParameters,classPathTO);
			for(int methodIndex=0;methodIndex<setMethodNames.length;methodIndex++){
				Method setMethod = dynamicClassTO.getMethod(
						setMethodNames[methodIndex],dynamicClassDialog);
				setMethod.invoke(dynamicTO,objectDialog);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dynamicTO;
	}
}
