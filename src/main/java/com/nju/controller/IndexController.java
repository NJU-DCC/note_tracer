package com.nju.controller;

import com.nju.model.NoteModel;
import com.nju.service.NoteService;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by raychen on 2017/4/17.
 */
@Controller
public class IndexController {


//    @Autowired
//    UserService userService;
//    @Autowired
//    NoteService noteService;

    @GetMapping("/")
    public String loginForm(Model model){
//        model.addAttribute("login", new LoginMessage());
        return "login";
    }

}
