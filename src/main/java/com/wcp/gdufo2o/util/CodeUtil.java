package com.wcp.gdufo2o.util;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;
/**
 * 
 * @author WuChuPeng
 * @Annotation:判断输入的验证码和系统所给的验证码是否相同
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request){
        //系统生成的验证码
        String verifyCodeExpected = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //所输入的验证码
        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
}
