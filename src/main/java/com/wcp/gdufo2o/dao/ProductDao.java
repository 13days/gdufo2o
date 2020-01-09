package com.wcp.gdufo2o.dao;


import com.wcp.gdufo2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品数据库接口
 */
public interface ProductDao {

    /**
     * 删除某个商品类别的时候,要把类别下的商品的商品类别Id设置为空
     * @param productCategoryId
     * @return
     */
    int updateProductCategoryToNull(long productCategoryId);


    /**
     * 更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    /**
     * 通过productId查询唯一的商品信息
     * @param productId
     * @return
     */
    Product queryProductById(long productId);

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 查询商品列表并分页，可输入的条件有：商品名（模糊），商品状态，店铺类别，商品类别
     * @param productCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") int rowIndex,
                                   @Param("pageSize") int pageSize);

    /**
     * 查询对应商品总数
     * @param productCondition
     * @return
     */
    int queryProductCount(@Param("productCondition") Product productCondition);
}
