package com.nju.vo;

import com.nju.model.DirModel;
import com.nju.model.NoteModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
public class NoteDetailVO {
    private int id=-1;
    private int userId;
    private String name;
    private Date createTime;
    private Date updateTime;
    private int dirId;
    private String dirName;
    private String content;

    public static List<NoteDetailVO> entityToVO(List<NoteModel> noteModels, List<DirModel> dirs){
        List<NoteDetailVO> vos=new ArrayList<NoteDetailVO>(noteModels.size());
        for(int i=0,len=noteModels.size();i<len;i++){
            vos.add(new NoteDetailVO(noteModels.get(i),dirs.get(i)));
        }
        return vos;
    }

    public static NoteModel voToEntity(NoteDetailVO vo){
        NoteModel noteModel=new NoteModel();
        noteModel.setCreateAt(vo.getCreateTime());
        noteModel.setId(vo.getId());
        noteModel.setContent(vo.getContent());
        noteModel.setUserId(vo.getUserId());
        noteModel.setTitle(vo.getName());
        noteModel.setDirId(vo.getDirId());
        noteModel.setUpdateAt(vo.getUpdateTime());

        return noteModel;

    }

    public NoteDetailVO(NoteModel noteModel, DirModel dirModel){
        this.id=noteModel.getId();
        this.userId=noteModel.getUserId();
        this.name=noteModel.getTitle();
        this.createTime=noteModel.getCreateAt();
        this.updateTime=noteModel.getUpdateAt();
        this.dirId=noteModel.getDirId();
        this.content=noteModel.getContent();
        this.dirName=dirModel.getTitle();
    }
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public int getDirId() {
        return dirId;
    }

    public String getDirName() {
        return dirName;
    }

    public String getContent() {
        return content;
    }
}
