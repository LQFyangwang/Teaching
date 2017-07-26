package com.xk.common;

import java.lang.reflect.Field;

public class ReflectUtil {
	public static Object getFieldValue(Class<?> clazz,Object obj, String fildName){
		try {
			Field field = clazz.getDeclaredField(fildName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
