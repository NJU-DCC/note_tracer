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
    public ModelAndView handleLoginRequest(
            ModelAndView model,
            @RequestParam("username") String username,
            @RequestParam("password") String password,HttpSession session){
        System.err.println("in login");
        System.err.println(username);
        System.err.println(password);
        //user not exist = -1
        //password wrong = 0
        //right= userId
        int result=userService.login(username,password);
        System.err.println("service--login--result= "+result);

        //TODO invoke the real login method
        if(result>0){
            session.setAttribute("userId",result);
            return new ModelAndView("redirect:/note");
        }else {
            model.getModel().put("msg",result);
            return model;
        }

    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(
            ModelAndView model,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email")String email){
        System.err.println("in register");
        System.err.println(username);
        System.err.println(password);
        int result=userService.register(username,password,email);
        model.getModel().put("msg",result);
        model.setViewName("redirect:/login");
        return model;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("userId");
        return new ModelAndView("redirect:/login");
    }

}
