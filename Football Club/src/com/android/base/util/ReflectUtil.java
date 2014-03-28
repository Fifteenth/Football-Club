package com.android.base.util;

import java.lang.reflect.Method;

import com.android.base.ConstantVariable;

public class ReflectUtil {

	public static String METHOD_START_SET = "set";
	public static String METHOD_START_GET = "get";
	
	public static String[] getAllSetOrGetMethodNames(String methodStart,String classPath){
		StringBuilder setMethodNames = new StringBuilder();
		Class<?> c;
		try {
			c = Class.forName(classPath);
			Method methods[] = c.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if(methodName.startsWith(methodStart)){
					if(setMethodNames.toString().equals(ConstantVariable.SYSBOL_DOUBLE_QUOTES)){
						setMethodNames.append(methodName);
					}else{
						setMethodNames.append(ConstantVariable.SYSBOL_COMMA);
						setMethodNames.append(methodName);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return setMethodNames.toString().split(ConstantVariable.SYSBOL_COMMA);
	}
}
