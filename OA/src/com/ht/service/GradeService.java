package com.ht.service;

import com.ht.bean.Grade;
import com.ht.common.bean.Pager4EasyUI;

public interface GradeService extends BaseService<Grade> {
	
	public Pager4EasyUI<Grade> queryByGradeName(Pager4EasyUI<Grade> pager, String gradeName);

}
