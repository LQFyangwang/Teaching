package com.ht.service;

import com.ht.bean.Pay;
import com.ht.bean.PayType;

public interface PayService extends BaseService<Pay> {

	public PayType queryByType(String typeName);
}
