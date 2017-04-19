package com.nju.controller;

import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by raychen on 2017/4/17.
 */
@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String home(){
        Integer id = userService.login("cr1", "123");
        return id.toString();
    }

}
