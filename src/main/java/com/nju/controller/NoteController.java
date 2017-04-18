package com.nju.controller;

import com.nju.vo.DirectoryVO;
import com.nju.vo.NoteDetailVO;
import com.nju.vo.NoteInfoVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    @RequestMapping("/getDirectories")
    public List<DirectoryVO> getAllDirectories(HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<DirectoryVO> directorys=null;//TODO invoke real method
        return directorys;
    }
    @RequestMapping("/search")
    public List<NoteInfoVO> search(@RequestParam String keyword, HttpSession session){
        int userId=(int)session.getAttribute("userid");
        List<NoteInfoVO> notes=null;//TODO
        return notes;
    }
    @RequestMapping("/get")
    public NoteDetailVO showNote(@RequestParam int noteId,HttpSession session){
        int userId=(int)session.getAttribute("userid");
        NoteDetailVO note=null;//TODO
        return note;
    }
    @RequestMapping("/update")
    public int update(@RequestParam NoteDetailVO noteDetailVO,HttpSession session){
        int userId=(int)session.getAttribute("userid");
        //TODO
        return 1;
    }
    @RequestMapping("/add")
    public int add(@RequestParam NoteDetailVO noteDetailVO,HttpSession session){
        int userId=(int)session.getAttribute("userid");
        //TODO
        return 1;
    }
    @RequestMapping("/delete")
    public int delete(@RequestParam int noteId,HttpSession session){
        int userId=(int)session.getAttribute("userid");
        //TODO
        return 1;
    }

}
