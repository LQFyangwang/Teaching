package com.gs.reflect;

import java.lang.reflect.Field;

import org.junit.Test;

public class DBGenerator {
	
	public String generate(String className) throws ClassNotFoundException {
		Class c = Class.forName(className);
		Field[] fields = c.getDeclaredFields();
		String sql = "create table table_name(";
		for (Field f : fields) {
			if (f.getType() == String.class) {
				sql += f.getName() + " varchar(100), ";
			} else if (f.getType() == int.class) {
				sql += f.getName() + " int, ";
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += ")";
		return sql;
	}
	
	@Test
	public void testGenerator() throws ClassNotFoundException {
		DBGenerator g = new DBGenerator();
		System.out.println(g.generate("com.gs.reflect.Account"));
	}

}
