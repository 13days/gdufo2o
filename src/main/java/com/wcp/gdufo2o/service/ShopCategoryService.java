package com.wcp.gdufo2o.service;

import java.util.List;

import com.wcp.gdufo2o.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
