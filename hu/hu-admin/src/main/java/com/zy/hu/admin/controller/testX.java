package com.zy.hu.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName testX
 * @Decsription
 * @Author:HWB
 * @Date 2020/8/5
 **/



@Controller
@ResponseBody
public class testX {

    @RequestMapping(value = "/a")
    public String aa(){
        return "sd";
    }
}
