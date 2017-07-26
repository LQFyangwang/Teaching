package com.gs.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class DOM4J {

	public static void main(String[] args) {
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new FileInputStream(new File("schema/car.xml")));
			Element root = doc.getRootElement(); // 获取根元素
			System.out.println(root.getName()); // 获取标签的名称
			Namespace ns = root.getNamespace(); // 获取命名空间
			System.out.println(ns.getName() + ":" + ns.getStringValue()); // 命名空间的名称和值
			getCarsElement(root);
			save(doc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) { // Doucment解析异常   xml文件到Document对象时产生的异常
			e.printStackTrace();
		}
	}
	
	private static void getCarsElement(Element root) {
		@SuppressWarnings("unchecked")
		List<Element> eles = root.elements();
		for (Element e : eles) {
			System.out.println(e.getName());
			getCarElement(e);
			getCarAttribute(e);
		}
	}
	
	private static void getCarElement(Element car) {
		setCarElement(car);
		@SuppressWarnings("unchecked")
		List<Element> els = car.elements();
		for (Element e : els) {
			System.out.println(e.getName());
		}
		System.out.println("********************");
		System.out.println(car.element("wheels").element("count").getText()); // 直接在某个父标签下根据子标签的名称直接获取子标签，
		System.out.println(car.elementText("test_e")); // 获取指定标签的内容
	}
	
	private static void getCarAttribute(Element car) {
		setCarAttribute(car);
		@SuppressWarnings("unchecked")
		List<Attribute> attrs = car.attributes();
		for (Attribute a : attrs) {
			System.out.println(a.getName() + ": " + a.getStringValue() + ", " + a.getText()); // getName()获取属性的名称，getStringValue()获取属性的值，getText()获取属性值，也可以用于获取元素内部的内容
		}
		System.out.println("**************************");
		Attribute idAttr = car.attribute("id");
		System.out.println(idAttr.getStringValue());
	}
	
	private static void setCarAttribute(Element car) {
		car.attribute("id").setValue("new 001");
		car.add(new DOMAttribute(new QName("test"), "test value")); // DOMAttribute(QName, String); 第一个参数表示属性的名称，第二个参数是属性的值
	}
	
	private static void setCarElement(Element car) {
		Element e = new DOMElement("test_e");
		e.setText("文本"); // 给标签设置文本内容
		car.add(e); // 添加元素
		car.add(new DOMElement(new QName("test_ee")));
	}
	
	private static void save(Document doc) {
		OutputFormat format = OutputFormat.createPrettyPrint(); // 好看的格式
		try {
			XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("schema/test_1.xml"), "utf-8"), format);
			writer.write(doc);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
