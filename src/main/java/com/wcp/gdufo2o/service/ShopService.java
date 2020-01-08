package com.wcp.gdufo2o.service;

import com.wcp.gdufo2o.dto.ImageHolder;
import com.wcp.gdufo2o.dto.ShopExecution;
import com.wcp.gdufo2o.entity.Shop;
import com.wcp.gdufo2o.exceptions.ShopOperationException;

public interface ShopService {
	 /**
     * 根据ShopCondition分页返回相应店铺列表
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

    /**
     * 通过店铺ID获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 更新店铺信息，包括对图片进行处理
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    //ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
    ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;

    /**
     * 注册店铺信息 包括图片处理
     * @param shop
     * @param thumbnail
     * @return
     * @throws ShopOperationException
     */
    //ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
