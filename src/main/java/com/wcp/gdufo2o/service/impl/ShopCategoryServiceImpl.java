package com.wcp.gdufo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcp.gdufo2o.dao.ShopCategoryDao;
import com.wcp.gdufo2o.entity.ShopCategory;
import com.wcp.gdufo2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
