package com.wcp.gdufo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.dto.ImageHolder;
import com.wcp.gdufo2o.dto.ShopExecution;
import com.wcp.gdufo2o.enmus.ShopStateEnum;
import com.wcp.gdufo2o.entity.Area;
import com.wcp.gdufo2o.entity.PersonInfo;
import com.wcp.gdufo2o.entity.Shop;
import com.wcp.gdufo2o.entity.ShopCategory;
import com.wcp.gdufo2o.exceptions.ShopOperationException;
@Service
public class ShopServiceTest extends BaseTest{
	@Autowired
    private ShopService shopService;
	
	@Test
	public void testModifyShop()throws ShopOperationException, FileNotFoundException{
		Shop shop = shopService.getByShopId(43L);
		shop.setShopName("修改后点店铺名称1");
		File shopImg = new File("C:\\Users\\WuChuPeng\\Pictures\\Screenshots\\屏幕截图(8).png");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.modifyShop(shop,new ImageHolder(shopImg.getName(),is));
        assertEquals(ShopStateEnum.SUCCESS.getState(), se.getState());
	}
	
	@Test
	@Ignore
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("test2");
        shop.setShopAddr("test2");
        shop.setPhone("test2");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("C:\\Users\\WuChuPeng\\Pictures\\Saved Pictures\\微信图片_20191214230901.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution se = shopService.addShop(shop,new ImageHolder(shopImg.getName(),is));
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }
}
