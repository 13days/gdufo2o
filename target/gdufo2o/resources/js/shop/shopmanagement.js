$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/gdufo2o/shop/getshopmanagementinfo?shopId='+shopId;

    // 请求后端查看url是否合理是否许需要重定向
    $.getJSON(shopInfoUrl,function (data) {
        if(data.redirect){
            window.location.href = data.url;
        }else{
            if(data.shopId!=undefined && data.shopId != null){
                shopId = data.shopId;
            }
            $('#shopInfo').attr('href','/gdufo2o/shopadmin/shopoperation?shopId='+shopId);
        }
    });
});