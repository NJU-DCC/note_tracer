package com.nju.controller;

import com.nju.model.NoteModel;
import com.nju.service.NoteService;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by raychen on 2017/4/17.
 */
@RestController
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    NoteService noteService;

    @RequestMapping("/")
    public String home(){
        NoteModel note = new NoteModel();
        note.setId(1);
        note.setContent("test content");
        note.setDirId(1);
        note.setUserId(1);
        note.setTitle("test");
        note.setCreateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        note.setUpdateAt(new Date(Calendar.getInstance().getTimeInMillis()));
        return String.valueOf(noteService.updateNote(note));
    }

}
