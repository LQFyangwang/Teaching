package com.ht.service;

import java.util.List;

import com.ht.bean.Goods;
import com.ht.bean.GoodsType;
import com.ht.bean.info.GoodsInfo;
import com.ht.common.bean.Pager4EasyUI;

public interface GoodsService extends BaseService<Goods>  {
	public List<GoodsType> queryNameAll();
	public Pager4EasyUI<GoodsInfo> queryPayInfoPage(Pager4EasyUI<GoodsInfo> pager);
	public Goods queryQuantity(String beanName, String goodsName);
	public void updateQuantity(String beanName, String goodsName, int count);
}
