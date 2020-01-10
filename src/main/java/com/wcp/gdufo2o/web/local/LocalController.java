package com.wcp.gdufo2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/local")
public class LocalController {

    /**
     * 绑定账号页面路由
     * @return
     */
    @RequestMapping(value = "/accountbind",method = RequestMethod.GET)
    private String accountbind(){
        return "local/accountbind";
    }

    /**
     * 修改密码页面路由
     * @return
     */
    @RequestMapping(value = "/changepsw",method = RequestMethod.GET)
    private String changepsw(){
        return "local/changepsw";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    private String login(){
        return "local/login";
    }



}
