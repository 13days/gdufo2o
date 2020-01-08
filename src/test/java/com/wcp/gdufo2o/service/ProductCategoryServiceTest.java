package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testGetShopCategoryList(){
        List<ProductCategory> productCategoryL = productCategoryService.getProductCategoryList(37L);
        for (ProductCategory productCategory : productCategoryL){
            System.out.println(productCategory.getProductCategoryName());
        }
    }
}
