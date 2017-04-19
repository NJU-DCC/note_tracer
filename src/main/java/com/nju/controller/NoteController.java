package com.nju.controller;

import com.nju.service.NoteService;
import com.nju.vo.DirVO;
import com.nju.vo.NoteDetailVO;
import com.nju.vo.NoteInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    @Autowired
    NoteService noteService;

    @RequestMapping("/")
    public String home(ModelMap model,HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<DirVO> directorys= DirVO.entityToVO(noteService.getDirs(userId));

        model.addAttribute("dirs",directorys);

        return "home";
    }

    @RequestMapping("/getDirs")
    public List<DirVO> getAllDirs(HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<DirVO> directorys= DirVO.entityToVO(noteService.getDirs(userId));
        for(DirVO dir:directorys){
//            dir.setNotes(noteService.getNotesByDir(dir.getId()));
            //TODO 关于note里面带不带内容的问题~！
        }
        return directorys;
    }
    @RequestMapping("/search")
    public List<NoteInfoVO> search(@RequestParam String keyword, HttpSession session){
        int userId=(int)session.getAttribute("userid");
        List<NoteInfoVO> notes=null;//TODO
        return notes;
    }
    @RequestMapping("/get")
    public String showNote(ModelMap model, @RequestParam int noteId, HttpSession session){
        int userId=(int)session.getAttribute("userid");
        NoteDetailVO note=null;//TODO
        return "";
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
    @RequestMapping("/addDir")
    public int addDir(@RequestParam DirVO dirVO, HttpSession session){
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

    public int transform(@RequestParam String fileName,HttpSession session){
        int userId=(int)session.getAttribute("userid");
        //TODO
        return 0;
    }

}
