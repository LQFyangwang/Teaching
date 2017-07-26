package com.ht.dao;

import com.ht.bean.Pay;
import com.ht.bean.PayType;

public interface PayDAO extends BaseDAO<Pay> {

	public PayType queryByType(String typeName);
}
