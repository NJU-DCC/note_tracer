package com.nju.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by disinuo on 17/4/18.
 */
@RestController
@RequestMapping("")
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView showLoginPage(){
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView showRegisterPage(){
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView handleLoginRequest(HttpSession session,String name,String password){
        int userId=0;//TODO invoke the real login method
        session.setAttribute("userId",userId);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(String name,String password){
        //TODO invoke the real register method
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("userId");
        return new ModelAndView("redirect:/login");
    }

}
