package com.ht.common.bean;

import java.util.List;

/**
 * EasyUI的分页类
 * @author Administrator
 *
 * @param <T>
 */
public class Pager4EasyUI<T> {

	private int pageSize; // 页面的大小
	private int pageNo; // 第几页
	
	private long total; // 页面的总页数
	private List<T> rows; // 返回到页面的结果
	
	public int getBeginIndex() {
		return (pageNo - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
