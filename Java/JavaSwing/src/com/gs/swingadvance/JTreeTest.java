package com.gs.swingadvance;

import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class JTreeTest extends JFrame {

	private static final long serialVersionUID = -8600039913754696342L;

	public JTreeTest() {
		setSize(400, 400);
		setLocationRelativeTo(null);

		String[] items = new String[] { "item1", "item2", "item3" };
		JTree tree = new JTree(items);
		add(tree);

		// 这种方式代码量过多
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(); // 创建树节点
		treeNode.setUserObject("One"); // 设置树节点的显示文字 （根节点）
		DefaultMutableTreeNode treeNodeItem1 = new DefaultMutableTreeNode("item1"); // 子节点
		DefaultMutableTreeNode treeNodeItem2 = new DefaultMutableTreeNode("item2");
		treeNode.add(treeNodeItem1); // 在根节点对象上添加子节点
		treeNode.add(treeNodeItem2);
		JTree tree2 = new JTree(treeNode); // 创建树时指定创建好的根节点
		add(tree2);

		String[] items1 = new String[] { "a-item1", "a-item2", "a-item3" };
		Hashtable<String, String[]> t = new Hashtable<String, String[]>();
		t.put("One", items);
		t.put("Two", items1);
		JTree tree1 = new JTree(t);
		// tree1.setSelectionRow(1); // 指的是最外层的序号
		tree1.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				JTree tree = (JTree) e.getSource();
				TreePath path = tree.getSelectionPath(); // 先获取到javax.swing.tree.TreePath对象，由该对象可以获取到所选中的树中的某项
				System.out.println(path.getLastPathComponent()); // path.getLastPathComponent()获取到最后一次被选中的树的项
				JOptionPane.showMessageDialog(JTreeTest.this, "您选中了" + path.getLastPathComponent(), "已选中", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(tree1);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new JTreeTest();
	}

}
