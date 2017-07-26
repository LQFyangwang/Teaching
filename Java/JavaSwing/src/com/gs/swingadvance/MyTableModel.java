package com.gs.swingadvance;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MyTableModel implements TableModel {
	
	private String[][] data;
	private String[] columnNames = new String[]{};
	
	public void setData(String[][] data) {
		this.data = data;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (rowIndex == 1 && columnIndex == 1) {
			return true;
		}
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = (String) aValue;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
