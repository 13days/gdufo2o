package com.wcp.gdufo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2, list.size());
		ShopCategory shopCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(33L);
		shopCategory.setParent(parentCategory);
		List<ShopCategory> list1 = shopCategoryDao.queryShopCategory(shopCategory);
		assertEquals(1, list1.size());
		System.out.println(list1.get(0).getShopCategoryName());
	}
}
