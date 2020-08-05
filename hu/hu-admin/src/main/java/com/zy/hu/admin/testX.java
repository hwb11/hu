package com.zy.hu.admin;

/**
 * @ClassName testX
 * @Decsription
 * @Author:HWB
 * @Date 2020/8/5
 **/

import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testX {

    @RequestMapping(value = "/a")
    public String aa(){
        return "sd";
    }
}
