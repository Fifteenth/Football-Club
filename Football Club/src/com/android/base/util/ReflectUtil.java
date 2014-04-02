package com.android.base.util;

import java.lang.reflect.Method;

import com.android.base.ConstantVariable;

public class ReflectUtil {

	public static String METHOD_START_SET = "set";
	public static String METHOD_START_GET = "get";
	
	/*
	 * 
	 */
	public static String[] getAllSetOrGetMethodNames(String methodStart,
			String []classPathParameters,String classPathDynamicTO){
		StringBuilder methodNames = new StringBuilder();
		Class<?> c;
		try {
			c = Class.forName(classPathDynamicTO);
			Method methods[] = c.getDeclaredMethods();
			for (Method method : methods) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				String methodName = method.getName();
				if(methodName.startsWith(methodStart)){
					if(classPathParameters==null
							||(classPathParameters!=null
								&&classPathParameters.length==parameterTypes.length
								&&matchParameters(classPathParameters,parameterTypes))){
						if(methodNames.toString().equals(ConstantVariable.SYSBOL_DOUBLE_QUOTES)){
							methodNames.append(methodName);
						}else{
							methodNames.append(ConstantVariable.SYSBOL_COMMA);
							methodNames.append(methodName);
						}
					}
				}
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return methodNames.toString().split(ConstantVariable.SYSBOL_COMMA);
	}
	
	
	public static boolean matchParameters(String []parameters,Class<?> []classParameters){
		if(parameters!=null){
			for(int i=0;i<parameters.length;i++){
				if(!parameters[i].equals(classParameters[i].getName())){
					return false;
				}
			}
		}
		return true;
	}
}
