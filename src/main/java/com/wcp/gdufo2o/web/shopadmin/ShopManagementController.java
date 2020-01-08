package com.wcp.gdufo2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcp.gdufo2o.dto.ImageHolder;
import com.wcp.gdufo2o.dto.ShopExecution;
import com.wcp.gdufo2o.enmus.ShopStateEnum;
import com.wcp.gdufo2o.entity.Area;
import com.wcp.gdufo2o.entity.PersonInfo;
import com.wcp.gdufo2o.entity.Shop;
import com.wcp.gdufo2o.entity.ShopCategory;
import com.wcp.gdufo2o.service.AreaService;
import com.wcp.gdufo2o.service.ShopCategoryService;
import com.wcp.gdufo2o.service.ShopService;
import com.wcp.gdufo2o.util.CodeUtil;
import com.wcp.gdufo2o.util.HttpServletRequestUtil;
/**
 * 店铺管理
 * @author WuChuPeng
 *
 */
@Controller
@RequestMapping("/shop")
public class ShopManagementController {
	@Autowired
	ShopService shopService;
	
	@Autowired
    private ShopCategoryService shopCategoryService;
	
	@Autowired
    private AreaService areaService;
	
	// 获取店铺信息并展示到前端供方法修改
    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    // 获取店铺初始化信息
	@RequestMapping(value = "getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
	}
	
	// 注册店铺
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request){

		Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }

        //获取前端传过来的店铺信息，并转换成实体类
        //1、接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //获取前端传过来的文件流，传到shopImg中
        //图片处理(Spring 使用Spring自带的CommonsMultipartFile)
        CommonsMultipartFile shopImg = null;
        //从上下文中获取相关文件上传内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不得为空");
            return modelMap;
        }

        //2、注册店铺
        if (shop != null && shopImg != null) {
            //店铺信息越少依赖于前端越好
            //Session 用户登陆时把用户信息添加到Session中 key是user
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
        	// TODO Session
            // PersonInfo owner = new PersonInfo();
            // owner.setUserId(12L);
            //set到即将要添加的owner信息中去
            shop.setOwner(owner);
            ShopExecution se = null;

            try {
                se = shopService.addShop(shop, new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream()));
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // 该用户可以操作的店铺列表(一个onwer可以对应多个店铺)
                    // 这里的技术和购物车样例是一样的
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        //第一次则创建店铺
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(se.getShop());
                    // 存会session
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "请输入店铺信息");
            }
            return modelMap;//3、返回结果

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
	}

	// 修改店铺
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyShop(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码输入错误");
            return modelMap;
        }

        //获取前端传过来的店铺信息，并转换成实体类
        //1、接受并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //获取前端传过来的文件流，传到shopImg中
        //图片处理(Spring 使用Spring自带的CommonsMultipartFile)
        CommonsMultipartFile shopImg = null;
        //从上下文中获取相关文件上传内容
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        //2、修改店铺信息
        if (shop != null && shop.getShopId() != null) {
            //店铺信息越少依赖于前端越好
            ShopExecution se = null;

            try {
                if (shopImg == null) {
                    se = shopService.modifyShop(shop, null);
                } else {
                    se = shopService.modifyShop(shop,new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream()));
                }

                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;//3、返回结果

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺ID");
            return modelMap;
        }
    }
}
