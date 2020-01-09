package com.wcp.gdufo2o.dao;

import com.wcp.gdufo2o.BaseTest;
import com.wcp.gdufo2o.entity.Product;
import com.wcp.gdufo2o.entity.ProductCategory;
import com.wcp.gdufo2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testQueryProductList(){
        Product product = new Product();
        product.setProductName("测试");
        List<Product> products = productDao.queryProductList(product, 0, 10);
        int count = productDao.queryProductCount(product);
        System.out.println("总数:"+count);
        System.out.println("分页查询数量:"+products.size());
        for (Product product1 : products){
            System.out.println(product1.getProductName());
        }
    }

    @Test
    @Ignore
    public void testQueryProductById(){
        Product product = productDao.queryProductById(16L);
        System.out.println(product.getProductName());
    }

    @Test
    @Ignore
    public void testUpdateProductCategoryToNull(){
        int effect = productDao.updateProductCategoryToNull(2L);
        System.out.println(effect);
    }

    @Test
    @Ignore
    public void testUpdateProduct(){
        Product product = new Product();
        product.setProductId(16L);
        product.setLastEditTime(new Date());
        product.setProductName("测试修改1");
        product.setProductDesc("test1");
        product.setImgAddr("addr1");
        product.setNormalPrice("1211");
        product.setPromotionPrice("111");
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2l);
        product.setProductCategory(productCategory);
        product.setPriority(1);
        product.setEnableStatus(1);
        Shop shop = new Shop();
        shop.setShopId(37L);
        product.setShop(shop);

        int effectNum = productDao.updateProduct(product);
        System.out.println(effectNum);
    }

    @Test
    @Ignore
    public void testInsertProduct(){
        Product product = new Product();
        product.setCreateTime(new Date());
        product.setProductName("测试插入");
        product.setProductDesc("test");
        product.setImgAddr("addr");
        product.setNormalPrice("121");
        product.setPromotionPrice("11");
        product.setLastEditTime(new Date());
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2l);
        product.setProductCategory(productCategory);
        product.setPriority(1);
        product.setEnableStatus(1);
        Shop shop = new Shop();
        shop.setShopId(37L);
        product.setShop(shop);
        int effectNum = productDao.insertProduct(product);
        System.out.println(effectNum);
    }
}
