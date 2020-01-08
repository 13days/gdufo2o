package com.wcp.gdufo2o.dao;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.ProductCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;


    @Test
    @Ignore
    public void testBatchInsertProductCategory(){
        ProductCategory p1 = new ProductCategory();
        p1.setCreateTime(new Date());
        p1.setPriority(1);
        p1.setShopId(37L);
        p1.setProductCategoryName("甜品");

        ProductCategory p2 = new ProductCategory();
        p2.setCreateTime(new Date());
        p2.setPriority(2);
        p2.setShopId(37L);
        p2.setProductCategoryName("饮料");

        ProductCategory p3 = new ProductCategory();
        p3.setCreateTime(new Date());
        p3.setPriority(3);
        p3.setShopId(37L);
        p3.setProductCategoryName("披萨");

        List<ProductCategory> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        int effectNum = productCategoryDao.batchInsertProductCategory(list);
        System.out.println(effectNum);
    }

    @Test
    @Ignore
    public void testQueryProductCategoryList(){
        List<ProductCategory> list = productCategoryDao.queryProductCategoryList(37L);
        for (ProductCategory productCategory : list){
            System.out.println(productCategory.getProductCategoryName());
        }
    }

    @Test
    @Ignore
    public void testDeleteProductCategory(){
        int effectNum = productCategoryDao.deleteProductCategory(4L, 37L);
        assertEquals(effectNum,1);
    }
}
