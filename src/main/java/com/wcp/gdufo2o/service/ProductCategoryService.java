package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.dto.ProductCategoryExecution;
import com.wcp.gdufo2o.entity.ProductCategory;
import com.wcp.gdufo2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 根据店铺Id查询商品类别
     * @param shopId
     * @return 返回店铺下的所有商品类别
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    /**
     *  批量添加商品类别
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;

    /**
     * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
            throws ProductCategoryOperationException;

}
