package com.android.service;

import java.lang.reflect.Method;

import com.android.base.util.ReflectUtil;
import com.android.dialog.MatchDialog;
import com.android.to.MatchTO;

public class BuildTOService {
	
	

	public static Object buildTO(String classPahtDialog,String classPathTO,Object objectDialog){
		Object dynamicTO = null;
		
		try {
			Class<?> dynamicClassDialog = Class.forName(classPahtDialog);
			Class<?> dynamicClassTO = Class.forName(classPathTO);
			
			//dynamicClassDialog.newInstance();
			
			// TO
			dynamicTO = dynamicClassTO.newInstance();
			
			String methodParameters[] = {MatchDialog.classPath};
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
