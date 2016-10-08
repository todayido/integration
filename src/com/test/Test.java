package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/9/24.
 */
@Controller
public class Test {

    @RequestMapping(value = "test")
    public String test(){
        // 跳转到：/views/main.jsp 页面
        return "main";
    }
}
