package com.nju.controller;

import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by disinuo on 17/4/18.
 */
@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView showLoginPage(){
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(){
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(@RequestParam("username") String username,
                                           @RequestParam("password") String password,HttpSession session){
        int userId=0;//TODO invoke the real login method

        System.err.println("in login");
        System.err.println(username);
        System.err.println(password);

        session.setAttribute("userId",userId);
        return new ModelAndView("redirect:/note/");
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("email")String email){
        System.err.println("in register");
        System.err.println(username);
        System.err.println(password);
        userService.register(username,password,email);

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("userId");
        return new ModelAndView("redirect:/login");
    }

}
