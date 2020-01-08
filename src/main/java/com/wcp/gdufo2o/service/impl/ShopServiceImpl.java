package com.wcp.gdufo2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wcp.gdufo2o.dao.ShopDao;
import com.wcp.gdufo2o.dto.ImageHolder;
import com.wcp.gdufo2o.dto.ShopExecution;
import com.wcp.gdufo2o.enmus.ShopStateEnum;
import com.wcp.gdufo2o.entity.Shop;
import com.wcp.gdufo2o.exceptions.ShopOperationException;
import com.wcp.gdufo2o.service.ShopService;
import com.wcp.gdufo2o.util.ImageUtil;
import com.wcp.gdufo2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	 @Autowired
	    private ShopDao shopDao;

	    @Override
	    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
//	        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
//	        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
//	        int count = shopDao.queryShopCount(shopCondition);
//	        ShopExecution se = new ShopExecution();
//	        if (shopList != null) {
//	            se.setShopList(shopList);
//	            se.setCount(count);
//	        } else {
//	            se.setState(ShopStateEnum.INNER_ERROR.getState());
//	        }
//	        return se;
	    	return null;
	    }

	    @Override
	    public Shop getByShopId(long shopId) {
	        return shopDao.queryByShopId(shopId);
	    }

	    @Override
	    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
	        //判断店铺信息是否为空
	        if (shop == null || shop.getShopId() == null) {
	            return new ShopExecution(ShopStateEnum.NULL_SHOP);
	        } else {
	            try {
	                //1、判断是否需要处理图片
	                if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
	                    Shop themShop = shopDao.queryByShopId(shop.getShopId());
	                    if (themShop.getShopImg() != null) {
	                        //删除原有图片
	                        ImageUtil.deleteFileOrPath(themShop.getShopImg());
	                    }
	                    //添加新图片
	                    addShopImg(shop, thumbnail);
	                }

	                //2、更新店铺信息
	                shop.setLastEditTime(new Date());
	                int effectedNum = shopDao.updateShop(shop);
	                if (effectedNum <= 0) {
	                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
	                } else {
	                    shop = shopDao.queryByShopId(shop.getShopId());
	                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
	                }
	            } catch (Exception e) {
	                throw new ShopOperationException("modifyShop error：" + e.getMessage());
	            }

	        }
	    }

	    @Override
	    @Transactional
	    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {

	        //空值判断（还需要对ShopCategory Area进行非空判断）//TO DO
	        if (shop == null) {
	            return new ShopExecution(ShopStateEnum.NULL_SHOP);
	        }

	        try {
	            //给店铺信息赋初始值
	            shop.setEnableStatus(0);
	            shop.setCreateTime(new Date());
	            shop.setLastEditTime(new Date());
	            //添加店铺信息
	            int effectedNum = shopDao.insertShop(shop);
	            if (effectedNum <= 0) {
	                //抛出 终止事务执行    为何不用Exception？当且仅当抛出ShopOperationException，事务才能终止并回滚
	                throw new ShopOperationException("店铺创建失败");
	            } else {
	                if (thumbnail.getImage() != null) {
	                    //存储图片
	                    try {
	                        addShopImg(shop, thumbnail);
	                    } catch (Exception e) {
	                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
	                    }
	                    //更新店铺的图片地址
	                    effectedNum = shopDao.updateShop(shop);
	                    if (effectedNum <= 0) {
	                        throw new ShopOperationException("更新图片地址失败");
	                    }
	                }
	            }

	        } catch (Exception e) {
	            throw new ShopOperationException("addShop error:" + e.getMessage());
	        }
	        return new ShopExecution(ShopStateEnum.CHECK, shop);
	    }

	    private void addShopImg(Shop shop, ImageHolder thumbnail) {
	        //获取shop图片目录的相对值路径,某店铺存的图片文件夹
	        String dest = PathUtil.getShopImagePath(shop.getShopId());
	        //处理缩略图，并返回新生成图片的相对路径
	        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail,dest);
	        shop.setShopImg(shopImgAddr);
	    }

}
