package com.jh.common;

import java.lang.reflect.Field;

public class ReflectUtil {

	public static Object getFieldValue(Class<?> clazz, Object obj, String fieldName) {
		Field field;
		try {
			field = clazz.getDeclaredField(fieldName);
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
