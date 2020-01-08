package com.wcp.gdufo2o.dao;

import com.wcp.gdufo2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品类别数据库操作接口
 */
public interface ProductCategoryDao {
    /**
     * 根据店铺Id查询商品类型列表
     * @param shopId
     * @return List<ProductCategoryDao>
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);

    /**
     * 删除指定商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * 两个参数myBatis识别不了，所以需要@Param这个注解来标明参数名
     */
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
