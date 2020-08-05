package com.zy.hu.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName testX
 * @Decsription
 * @Author:HWB
 * @Date 2020/8/5
 **/



@Controller
public class testX {

    @RequestMapping(value = "/a")
    public String aa(){
        return "sd";
    }
}
