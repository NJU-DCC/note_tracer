package com.nju.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raychen on 2017/4/17.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "Hello world";
    }

}
