package com.nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.nju.model.DirModel;
import com.nju.model.NoteModel;
import com.nju.service.NoteService;
import com.nju.vo.DirVO;
import com.nju.vo.NoteDetailVO;
import com.nju.vo.NoteInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


/**
 * Created by disinuo on 17/4/18.
 */

@Controller
@RequestMapping("/note")
public class NoteController {
    private static String UPLOADED_FOLDER = "/Users/disinuo/Documents/temps/";
    @Autowired
    ServletContext context;

    @Autowired
    NoteService noteService;
    @RequestMapping("")
    public ModelAndView home(ModelAndView model,HttpSession session){
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
        model.getModel().put("dirs",directorys);
        model.setViewName("home");
        return model;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<NoteInfoVO> search(@RequestParam String keyword, HttpSession session){
        int userId=(int)session.getAttribute("userId");
        List<NoteModel> noteModels=noteService.search(keyword,userId);
        System.err.println("in search controller,resultSize= "+noteModels.size());
        List<String> dirNames=new ArrayList<String>(noteModels.size());
        for(NoteModel noteModel:noteModels){
            String dirName=noteService.getDir(noteModel.getDirId()).getTitle();
            dirNames.add(dirName);
        }
        List<NoteInfoVO> notes=NoteInfoVO.entityToVO_dirName(noteModels,dirNames);
        return notes;
    }

    @RequestMapping("/show")
    @ResponseBody
    public NoteDetailVO showNote(@RequestParam int noteId){
        System.out.println("----------in controller showNote-----------");

        NoteModel noteModel=noteService.getNote(noteId);
        if(noteModel!=null){
            DirModel dirModel=noteService.getDir(noteModel.getDirId());
            NoteDetailVO note=new NoteDetailVO(noteModel,dirModel);
            return note;
        }
        return null;

    }

    @RequestMapping("/update")
    @ResponseBody
    public int updateNote(@ModelAttribute NoteDetailVO noteDetailVO){
        NoteModel noteModel=NoteDetailVO.voToEntity(noteDetailVO);
        noteModel.setUpdateAt(new Date());
        return noteService.updateNote(noteModel);
    }
    @RequestMapping("/updateDir")
    @ResponseBody
    public int updateDir(@ModelAttribute DirVO dirVO){
        DirModel dirModel=DirVO.voToEntity(dirVO);
        return noteService.updateDir(dirModel);
    }
    @RequestMapping("/add")
    @ResponseBody
    public int addNote(NoteDetailVO noteDetailVO,HttpSession session){
        NoteModel noteModel=NoteDetailVO.voToEntity(noteDetailVO);
        int uid=(int)session.getAttribute("userId");
        noteModel.setUserId(uid);
        noteModel.setCreateAt(new Date());
        return noteService.addNote(noteModel);
    }
    @RequestMapping("/addDir")
    @ResponseBody
    public int addDir(DirVO dirVO,HttpSession session){
        System.err.println("addDir controller vo title= "+dirVO.getName());

        DirModel dirModel=DirVO.voToEntity(dirVO);
        int uid=(int)session.getAttribute("userId");
        dirModel.setUserId(uid);
        dirModel.setCreateAt(new Date());
        System.err.println("addDir controller title= "+dirModel.getTitle());
        return noteService.addDir(dirModel);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public int deleteNote(@RequestParam int noteId,HttpSession session){
       return noteService.deleteNote(noteId);
    }
    @RequestMapping("/deleteDir")
    @ResponseBody
    public int deleteDir(@RequestParam int dirId){
        return noteService.deleteDir(dirId);
    }


    @RequestMapping(value = "/transform",method = RequestMethod.POST)
    @ResponseBody
    public String transform(@RequestParam("file") MultipartFile file) {
        System.err.println("in transform controller");
        String path=getImgFolderPath()+"scan-img/";
        System.err.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            //TODO 反馈空文件错误
//            model.addAttribute("msg","empty_file");
//            return model;
            return "";
        }
        try {
            byte[] bytes = file.getBytes();
            String path_str=path+(new Date()).getTime()+file.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(path_str), bytes);
            System.err.println(path_str);

            String content=noteService.transNote(path_str);
//            model.addAttribute("content",content);
//            model.addAttribute("msg","success");
            System.err.println("controller,transformed content: "+content);
            return content;
        } catch (IOException e) {
            //TODO TOO_LARGE,...
//            model.addAttribute("msg","something_wrong");
            e.printStackTrace();
        }

        return "";
    }
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        String path=getImgFolderPath()+"insert-img/";
        System.err.println(file.getOriginalFilename());
        if (file.isEmpty()) {
            //TODO 反馈空文件错误
            return "";
        }
        try {
            byte[] bytes = file.getBytes();
            String path_str=path+(new Date()).getTime()+file.getOriginalFilename();
            FileUtils.writeByteArrayToFile(new File(path_str), bytes);
            int index=path_str.indexOf("/img/insert");
            return path_str.substring(index);

        } catch (IOException e) {
            //TODO TOO_LARGE,...
//            model.addAttribute("msg","something_wrong");
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        System.err.println("-------- In test controller----------");
        String path=context.getRealPath("/resources");
        System.err.println("path=???"+path);
        String[] temps=path.split("/");
        String imgPath="";
        for(int i=0;i<temps.length-4;i++){
            imgPath+=temps[i]+"/";
        }
        imgPath+="target/classes/static/img/";
        System.err.println("path=???"+imgPath);

//        String staticPath=p
    }

    private String getImgFolderPath(){
        String path=context.getRealPath("/resources");
        System.err.println("path=???"+path);
        String[] temps=path.split("/");
        String imgPath="";
        for(int i=0;i<temps.length-4;i++){
            imgPath+=temps[i]+"/";
        }
        imgPath+="target/classes/static/img/";
        return imgPath;
    }


}
