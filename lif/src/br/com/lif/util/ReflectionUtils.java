package br.com.lif.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.lif.exceptions.MethodNotDeclaredExcepiton;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ReflectionUtils{

	private ReflectionUtils(){}
	
	public static Object newInstance(String nameClass){
		return newInstance(nameClass, new Class[0], new Object[0]);
	}
	
	public static Object newInstance(String nameClass, Class parametersTypesConstruct[], Object argListConstruct[]){
		Class clazz = getClass(nameClass);
		Constructor constructor = getConstructor(clazz, parametersTypesConstruct);
		Object object = getObjectInstance(constructor, argListConstruct);
		return object;
	}
	
	public static Object invokeMethod(Object objController, String nameMethod){	
		return invokeMethod(objController, new Class[0], new Object[0], nameMethod);
	}
	
	public static Object invokeMethod(Object objController, Class parametersTypesMethod[], Object argListMethod[], String nameMethod){		
		return invokeMethod(objController,  new Class[0], new Object[0], parametersTypesMethod, argListMethod, nameMethod);
	}
	
	public static Object invokeMethod(Object objController, Class parametersTypesConstruct[], Object argListConstruct[], 
			Class parametersTypesMethod[], Object argListMethod[], String nameMethod){
		Method method = getMethod(objController.getClass(), nameMethod, 0, parametersTypesMethod);
		Object objReturnMethod = invokeMethod(method, objController, argListMethod);
		return objReturnMethod;
	}
	
	public static Class getClass(String nameClass){
		Class clazz = null;
		try {
			clazz = Class.forName(nameClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	public static Constructor getConstructor(Class clazz, Class parametersTypesConstruct[]){		
		Constructor constructor = null;
		try {
			constructor = clazz.getConstructor(parametersTypesConstruct);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}		
		return constructor;
	}
	
	public static Object getObjectInstance(Constructor constructor, Object argListConstruct[]){
		Object obj = null;
		try {
			obj = constructor.newInstance(argListConstruct);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Method getMethod(Class clazz, String nameMethod){
		return getMethod(clazz, nameMethod, 0);
	}
	
	public static Method getMethod(Class clazz, String nameMethod, int nivelSuperClass){
		return getMethod(clazz, nameMethod, nivelSuperClass, new Class[0]);
	}
	
	public static Method getMethod(Class clazz, String nameMethod, int nivelSuperClass, Class parametersTypesMethod[]){
		Method method = null;
		for (int nivel = 0; nivel < nivelSuperClass; nivel++) {
			clazz = clazz.getSuperclass();
		}
		try {
			method =  clazz.getDeclaredMethod(nameMethod, parametersTypesMethod);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			throw new MethodNotDeclaredExcepiton(MessageUtils.getMessage(Constants.KEY_MSG_METHOD_NOT_DECLARED, nameMethod));
		}
		return method;
	}
	
	public static Object invokeMethod(Method method, Object object){
		return invokeMethod(method, object, new Object[0]);
	}
	
	public static Object invokeMethod(Method method, Object object, Object argListMethod[]) {
		Object obj = null;
		try {
			obj = method.invoke(object, argListMethod);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return obj;
	}
}