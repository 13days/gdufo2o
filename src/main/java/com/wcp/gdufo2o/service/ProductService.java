package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.dto.ImageHolder;
import com.wcp.gdufo2o.dto.ProductExecution;
import com.wcp.gdufo2o.entity.Product;
import com.wcp.gdufo2o.exceptions.ProductOperationException;

import java.util.List;

/**i
 * 商品操作Servce接口
 */
public interface ProductService {
    /**
     * 查询商品列表并分页，可输入的条件有:商品名(模糊)，商品状态，店铺Id，商品类别
     * @param productCondition
     * @param pageIndex 返回哪一页数据
     * @param pageSize  页里面有多少条数据
     * @return
     */
    ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);


    /**
     * 修改商品信息及图片处理
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;


    /**
     * 通过商品ID查询唯一商品信息
     * @param ProductId
     * @return
     */
    Product getProductById(long ProductId);

    /**
     * 添加商品信息以及图片处理
     * @param product
     * 缩略图
     * @param thumbnail
     * 详情图
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product,
                                ImageHolder thumbnail,
                                List<ImageHolder> productImgList) throws ProductOperationException;
}
