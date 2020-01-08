package com.wcp.gdufo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.Area;
import com.wcp.gdufo2o.entity.PersonInfo;
import com.wcp.gdufo2o.entity.Shop;
import com.wcp.gdufo2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	
	@Autowired
	private ShopDao shopDao;
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(12L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(33L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺1");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int t = shopDao.insertShop(shop);
		assertEquals(1, t);
	}
	
	
	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(29L);

		shop.setShopAddr("测试修改");
		shop.setAdvice("继续审核中");
		shop.setLastEditTime(new Date());
		int t = shopDao.updateShop(shop);
		assertEquals(1, t);
	}
}
