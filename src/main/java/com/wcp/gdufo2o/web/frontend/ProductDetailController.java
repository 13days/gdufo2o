package com.wcp.gdufo2o.web.frontend;

import com.wcp.gdufo2o.entity.Product;
import com.wcp.gdufo2o.service.ProductService;
import com.wcp.gdufo2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 商品细节
 */
@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listproductdetailpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listProductDetailPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //获取前台传递过来的productId
        long productId = HttpServletRequestUtil.getLong(request,"productId");
        Product product = null;
        //空值判断
        if(productId!=-1){
            //根据productId获取商品信息，包括商品详情图列表
            product = productService.getProductById(productId);
            modelMap.put("product",product);
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
            modelMap.put("errMsg","empty productId");
        }
        return modelMap;
    }

}
