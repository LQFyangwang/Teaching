package com.gs.common;

import java.lang.reflect.Field;

public class ReflectUtil {
	
	/**
	 * 在指定类的对象上去获取指定名称的属性的值
	 * @param clazz
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Class<?> clazz, Object obj, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName); // 获取所有声明的属性，包括private
			field.setAccessible(true); // 设置属性可访问 
			return field.get(obj); // 从指定对象上获取其属性的值 
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

}
