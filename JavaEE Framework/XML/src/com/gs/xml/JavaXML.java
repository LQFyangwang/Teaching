package com.gs.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JavaXML {
	
	/**
	 * 什么叫节点？
	 * 对于xml文档来说，元素，元素的内容，属性都称之为节点
	 * 节点包含有名称，值，类型
	 * @param args
	 */
	public static void main(String[] args) {
		// 对于一个工厂类来说，构造方法是私有的，不能通过构造方法来创建工厂类的实例
		// 工厂类是专门用来创建其他类型的对象，所以通过直接提供static的方法用来创建其他类型
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new FileInputStream(new File("schema/car.xml"))); // Document是xml所对应的文档树对象，包含有xml文件中所有的元素，元素的属性
			Element cars = getRootElement(doc);
			System.out.println(cars.getTagName()); // 获取元素的名称
			System.out.println(cars.getAttribute("xmlns:xsi")); // 根据属性的名称获取属性值
			getRootElementAttrs(cars);
			getCarElement(doc, cars);
			
			saveNewFile(cars);
		} catch (ParserConfigurationException e) { // 解析配置异常
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) { // 在读取特定的xml文件时，产生的异常
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	private static Element getRootElement(Document doc) {
		return doc.getDocumentElement(); // 获取根元素
	}
	
	private static void getRootElementAttrs(Element cars) {
		NamedNodeMap attrs = cars.getAttributes();
		for (int i = 0, len = attrs.getLength(); i < len; i++) {
			Node node = attrs.item(i); // 包含有名称和值的一个节点对象
			System.out.println("cars attr: " + node.getNodeName()); // 节点的名称
			System.out.println("cars attr value: " + node.getNodeValue()); // 节点的值
			System.out.println("cars attr type: " + node.getNodeType()); // 返回2，表示此节点为属性节点   2   ATTRIBUTE_NODE   1    ELEMENT_NODE 3    TEXT_NODE    8   COMMENT_NODE
		}
	}
	
	private static void getCarElement(Document doc, Element cars) {
		NodeList carList = cars.getChildNodes(); // 获取某个元素下的所有子节点（第一个级别的子节点）
		for (int i = 0, len = carList.getLength(); i < len; i++) {
			Node car = carList.item(i);
			if (car.getNodeType() == Node.ELEMENT_NODE) { // 如果节点类型是元素节点，则输出元素的名称。如果没有判断，则元素与元素间的文本节点也会输出
				System.out.println("car" + i + ": " + car.getNodeName());
				getCarElementAttrs(doc, car);
				getCarEngineBrand(car);
			}
		}
	}
	
	private static void getCarElementAttrs(Document doc, Node car) {
		NamedNodeMap carAttrs = car.getAttributes();
		Element ele = doc.createElement("test"); // 创建一个元素
		car.appendChild(ele); // 在指定元素后面追加一个子元素
		for (int ii = 0, len1 = carAttrs.getLength(); ii < len1; ii++) {
			Node attrNode = carAttrs.item(ii);
			attrNode.setNodeValue("aaaaaaaaaaaaaaaaaaaaa");
			System.out.println(attrNode.getNodeName() + ":" + attrNode.getNodeValue());
		}
	}
	
	private static void getCarEngineBrand(Node car) {
		NodeList nodeList = car.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				// 
			}
		}
	}
	
	private static void saveNewFile(Element root) {
		try {
			TransformerFactory tfactory = TransformerFactory.newInstance();
			Transformer transformer = tfactory.newTransformer(); // transformer是把dom资源转化成文件保存的一个对象
			DOMSource source = new DOMSource(root); // 根据根元素来获取整个Dom资源
			File file = new File("schema/test.xml");
			StreamResult result = new StreamResult(file); // 创建输出流
			transformer.transform(source, result); // 把DomSource转化为文件（StreamResult指定）保存
		} catch (TransformerException e) {

		}
	}

}
