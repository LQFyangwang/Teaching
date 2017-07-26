package com.gs.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class ToIntArrayConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map actionContext, String[] values, Class desClass) {
		int[] intArray = null;
		if (values.length > 0) {
			String str = values[0];
			String[] strArray = str.split(",");
			
			intArray = new int[strArray.length];
			for (int i = 0, len = strArray.length; i < len; i++) {
				intArray[i] = Integer.parseInt(strArray[i]);
			}
		}
		return intArray;
	}

	@Override
	public String convertToString(Map actionContext, Object values) {
		int[] intArray = (int[]) values;
		String str = "";
		if (intArray.length > 0) {
			for (int a : intArray) {
				if (str.equals("")) {
					str = String.valueOf(a);
				} else {
					str += "," + String.valueOf(a);
				}
			}
		}
		return str;
	}

}
