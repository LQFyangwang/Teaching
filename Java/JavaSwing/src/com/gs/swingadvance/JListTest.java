package com.gs.swingadvance;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * serializable 序列化
 * 
 * 任何一个类如果提示你需要序列化，则说明这个类或者该类的父类（多个层次也行）实现了java.io.Serializable接口
 * 
 * java.io.Serializable接口是JDK提供的序列化接口，其是一个标识接口，内部没有任何方法
 * 
 * 如果实现了该接口的类，或继承自实现了该接口的类的子类，都应该添加序列化版本号，当加了序列化版本号，则警告消除
 *
 */
public class JListTest extends JFrame {

	// Add generated serial version ID
	private static final long serialVersionUID = 230582839290408371L;

	public JListTest() {
		setSize(400, 400);
		setLocationRelativeTo(null);

		/**
		 * 为什么有警告？
		 * JList类型于一个容器，这个容器存放的数据必须是同一种类型，在声明的时候，没有指定该容器可以存储何种类型的数据，则会出现警告
		 * 警告信息如下（如果以后看到类型的警告，则说明你应该在声明的时候就告诉它存放何种数据类型）： JList is a raw type.
		 * References to generic type JList<E> should be parameterized
		 * 在声明的时候就告诉该容器存放的数据类型， 在类的后面添加 <数据类型>
		 * 
		 * 泛型，泛型只适用于引用类型，而不能用基本数据类型，本质上是在编译阶段起作用
		 */
		String[] items = new String[] { "item1", "item2", "item3", "item4" };
		JList<String> list = new JList<String>(items);
		// list.setSelectedIndex(1); // 设置指定索引的项被选中
		list.setSelectedIndices(new int[] { 1, 2 }); // 设置多项选中
		list.setBorder(BorderFactory.createLineBorder(Color.RED, 1)); // 设置边框，使用javax.swing.BorderFactory边框工厂类创建指定类型的边框

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) { // 此事件监听处理调用了两次
				@SuppressWarnings({ "unchecked" }) // Java注解（注释），
													// 用于声明方法或类或变量的一些基本信息
													// @Override 说明方法重写，
													// @SuppressWarnings("unchecked")表示压制检查警告， unused表示压制未使用警告
				JList<String> list = (JList<String>) e.getSource();
				// int idx = list.getSelectedIndex(); // 获得所选项的索引
				int[] idxs = list.getSelectedIndices();
				@SuppressWarnings({ "unused", "deprecation" })
				String[] values = (String[]) list.getSelectedValues(); // 获取所有被选项的值
				for (int i : idxs) {
					System.out.println(items[i]);
				}
			}
		});

		add(list);

		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addListDataListener(new ListDataListener() {

			@Override
			public void intervalRemoved(ListDataEvent e) { // 从ListModel中移除某一项时触发
				System.out.println("removed");
			}

			@Override
			public void intervalAdded(ListDataEvent e) { // 向ListModel中添加数据时触发 
				System.out.println("added");
			}

			@Override
			public void contentsChanged(ListDataEvent e) { // 修改ListModel中的某一项时触发
				System.out.println("contents changed....");
			}
		});
		model.add(0, "item1");
		model.add(1, "item2");
		model.add(2, "item3");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList list1 = new JList(model);
		// model.removeAllElements(); // 移除所有项
		// model.remove(1); // 移除指定索引项
		model.removeElementAt(2);
		model.set(0, "new item1"); // 修改指定项

		add(list1);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new JListTest();
	}

}
