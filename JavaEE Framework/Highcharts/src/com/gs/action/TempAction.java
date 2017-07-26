package com.gs.action;

import java.util.ArrayList;
import java.util.List;

import com.gs.bean.LineBasic;
import com.opensymphony.xwork2.ActionSupport;

public class TempAction extends ActionSupport {

	private static final long serialVersionUID = -2948480989286558198L;
	
	private List<LineBasic> lineBasic;
	 
	public List<LineBasic> getLineBasic() {
		return lineBasic;
	}

	public String execute() {
		lineBasic = new ArrayList<LineBasic>();
		LineBasic t1 = new LineBasic();
		t1.setName("上海");
		t1.setData(new double[]{7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6});
		LineBasic t2 = new LineBasic();
		t2.setName("北京");
		t2.setData(new double[]{-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5});
		lineBasic.add(t1);
		lineBasic.add(t2);
		return SUCCESS;
	} 

}
