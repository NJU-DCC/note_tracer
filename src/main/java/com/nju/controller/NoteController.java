package com.nju.controller;

import com.nju.controller.stub.NoteServiceStub;
import com.nju.model.DirModel;
import com.nju.model.NoteModel;
import com.nju.service.NoteService;
import com.nju.vo.DirVO;
import com.nju.vo.NoteDetailVO;
import com.nju.vo.NoteInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;
//    NoteService noteService=new NoteServiceStub();

    @RequestMapping("/")
    public String home(ModelMap model,HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<DirVO> directorys= DirVO.entityToVO(noteService.getDirs(userId));
        for(DirVO dir:directorys){
            int dirId=dir.getId();
            List<NoteModel> noteModels=noteService.getNotesByDir(dirId);
            List<String> dirNames=new ArrayList<String>(noteModels.size());
            for(int i=0;i<noteModels.size();i++){
                dirNames.add(dir.getName());
            }
            dir.setNotes(NoteInfoVO.entityToVO_dirName(noteModels,dirNames));
        }

        for(DirVO d:directorys){
            System.out.println("文件夹：id="+d.getId()+", name="+d.getName()+" num="+d.getNumOfNotes());
            for(NoteInfoVO note:d.getNotes()){
                System.out.println("   笔记：id="+note.getId()+" name="+note.getName()+" dirId="+note.getDirId()+" dirName="+note.getDirName());
            }
        }
        model.addAttribute("dirs",directorys);
        return "Login";
    }

    @RequestMapping("/search")
    public List<NoteInfoVO> search(@RequestParam String keyword, HttpSession session){
        int userId=(int)session.getAttribute("userid");
        List<NoteModel> noteModels=noteService.search(keyword,userId);
        List<String> dirNames=new ArrayList<String>(noteModels.size());
        for(NoteModel noteModel:noteModels){
            String dirName=noteService.getDir(noteModel.getDirId()).getTitle();
            dirNames.add(dirName);
        }
        List<NoteInfoVO> notes=NoteInfoVO.entityToVO_dirName(noteModels,dirNames);
        return notes;
    }

    @RequestMapping("/show")
    public NoteDetailVO showNote(@RequestParam int noteId){
        NoteModel noteModel=noteService.getNote(noteId);
        DirModel dirModel=noteService.getDir(noteModel.getDirId());
        NoteDetailVO note=new NoteDetailVO(noteModel,dirModel);
        return note;
    }

    @RequestMapping("/update")
    public int updateNote(@RequestParam NoteDetailVO noteDetailVO){
        NoteModel noteModel=NoteDetailVO.voToEntity(noteDetailVO);
        return noteService.updateNote(noteModel);
    }
    @RequestMapping("/updateDir")
    public int updateDir(@RequestParam DirVO dirVO){
        DirModel dirModel=DirVO.voToEntity(dirVO);
        return noteService.updateDir(dirModel);
    }
    @RequestMapping("/add")
    public int addNote(@RequestParam NoteDetailVO noteDetailVO,HttpSession session){
        NoteModel noteModel=NoteDetailVO.voToEntity(noteDetailVO);
        int uid=(int)session.getAttribute("userId");
        noteModel.setUserId(uid);
        return noteService.addNote(noteModel);
    }
    @RequestMapping("/addDir")
    public int addDir(@RequestParam DirVO dirVO,HttpSession session){
        DirModel dirModel=DirVO.voToEntity(dirVO);
        int uid=(int)session.getAttribute("userId");
        dirModel.setUserId(uid);
        return noteService.addDir(dirModel);
    }
    @RequestMapping("/delete")
    public int deleteNote(@RequestParam int noteId,HttpSession session){
       return noteService.deleteNote(noteId);
    }
    @RequestMapping("/deleteDir")
    public int deleteDir(@RequestParam int dirId){
        return noteService.deleteDir(dirId);
    }
    @RequestMapping("/transform")
    @ResponseBody
    public String transform(@RequestParam String fileName){
        return noteService.transNote(fileName);
    }

}
