package com.wcp.gdufo2o.util;

/**
 * 
 * @author WuChuPeng
 *
 */
public class PathUtil {

    //获取操作系统分隔符
    private static String seperator = System.getProperty("file.separator");

    /**
         * 根据不同操作系统设定储存图片文件不同的根目录
     */
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        /*按照windows路径的存储方法*/
        if(os.toLowerCase().startsWith("win")){
            basePath = "C:/Users/WuChuPeng/data_for_wuchupeng/eclipse_project/image"; //根据自己的实际路径进行设置
        }else{
            basePath = "/home/wuchupeng/image";  //根据自己的实际路径进行设置
        }

        //更换分隔符
        basePath = basePath.replace("/",seperator);

        return basePath;
    }

    /**
         * 在上面的基础上  获取店铺图片的存储路径（分别存储在各自店铺的路径下）
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/",seperator);
    }
    
    public static void main(String[] args) {
    	System.out.println(System.getProperty("os.name")); //Windows 10
    }
}
