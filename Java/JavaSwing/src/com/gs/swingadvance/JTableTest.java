package com.gs.swingadvance;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class JTableTest extends JFrame {

	private static final long serialVersionUID = 6587144633611051053L;

	public JTableTest() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		//第一种方法
		JTable table = new JTable(3, 3);
		for (int i = 0, rows = table.getRowCount(); i < rows; i++) {
			for (int j = 0, columns = table.getColumnCount(); j < columns; j++) {
				table.setValueAt("row-" + (i + 1) + "-coloumn-" + (j + 1), i, j); // 行，列索引都是从0开始
			}
		}
		add(table);
		
		
		JTableData td = new JTableData();
		JTable table1 = new JTable(td.getData(), td.getColumnNames()); // 第 一个参数是所有行列数据，第二个参数是列名称
		add(table1);
		
		JScrollPane scrollPane = new JScrollPane(); // 滚动面板
		DefaultTableModel tableModel = new DefaultTableModel(); // 默认的TableModel每个单元格都是可以编辑的，如果单元格被修改了，则会触发javax.swing.event.TableModelListener中的tableChanged事件
		DefaultTableColumnModel tableColumnModel = new DefaultTableColumnModel(); // 指定每一个列
		tableColumnModel.addColumn(new TableColumn(0, 20));
		tableColumnModel.addColumn(new TableColumn(1, 120));
		tableColumnModel.addColumnModelListener(new TableColumnModelListener() {
			
			@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
				System.out.println("column  selection changed...");
				
			}
			
			@Override
			public void columnRemoved(TableColumnModelEvent e) {
				System.out.println("column removed...");
			}
			
			@Override
			public void columnMoved(TableColumnModelEvent e) {
				System.out.println("column moved...");
			}
			
			@Override
			public void columnMarginChanged(ChangeEvent e) {
				System.out.println("column  margin changed...");
			}
			
			@Override
			public void columnAdded(TableColumnModelEvent e) {
				System.out.println("column  added...");
			}
		});
		// TableModel必须先添加列，再添加行
//		tableModel.addColumn("aaa");
//		tableModel.addColumn("bbb");
//		tableModel.addColumn("ccc");
//		tableModel.addRow(new String[]{"row-1-column-1", "row-1-column-2", "row-1-column-3"});
//		tableModel.addRow(new String[]{"row-2-column-1", "row-2-column-2", "row-2-column-3"});
//		tableModel.addRow(new String[]{"row-3-column-1", "row-3-column-2", "row-3-column-3"});
		tableModel.addColumn("aaa", new String[]{"row-1-column-1", "row-2-column-1", "row-3-column-1"});
		tableModel.addColumn("bbb", new String[]{"row-1-column-2", "row-2-column-2", "row-3-column-2"});
		tableModel.addColumn("ccc", new String[]{"row-1-column-3", "row-2-column-3", "row-3-column-3"});
		tableModel.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println("table changed...");
			}
		});
		JTable table2 = new JTable(tableModel);
		// 如果两个都 设置为false，都不会有选中的情况
		// 如果前面为false, 后面为true，则整行选 中
		// 如果两个都为true,则只能选中被点击的那个单元格
		// 如果前面为True，后面为False，则整列  
		table2.setColumnSelectionAllowed(true); // 如果设置为false，整行选中，如果为ture，则点击任意一个单元格，该单元格选中
		table2.setRowSelectionAllowed(false); // 如果设置为false，则单元格不被选中，如果为ture，则点击任意一个单元格，这个行的所有单元格都会被选中
		scrollPane.getViewport().add(table2); // JScrollPane先获取JViewPort，再把JTable放在JViewPort上
		add(scrollPane);
		
//		MyTableModel myTableModel = new MyTableModel();
//		myTableModel.setColumnNames(new String[]{"ONE", "TWO", "THREE"});
//		myTableModel.setData(new String[][]{{"one", "two", "three"}, {"four", "five", "six"}, {"seven", "eight", "nine"}});
//		
//		JTable table3 = new JTable(myTableModel);
//		add(table3);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JTableTest();
	}

}
