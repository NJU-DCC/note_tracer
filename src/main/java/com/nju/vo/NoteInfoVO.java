package com.nju.vo;

import com.nju.model.DirModel;
import com.nju.model.NoteModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by disinuo on 17/4/18.
 */
public class NoteInfoVO {
    private int id;
    private int userId;
    private String name;
    private Date createTime;
    private Date updateTime;
    private int dirId;
    private String dirName;

    public static List<NoteInfoVO> entityToVO_dirModel(List<NoteModel> noteModels,List<DirModel> dirs){
        List<NoteInfoVO> vos=new ArrayList<NoteInfoVO>(noteModels.size());
        for(int i=0,len=noteModels.size();i<len;i++){
            vos.add(new NoteInfoVO(noteModels.get(i),dirs.get(i)));
        }
        return vos;
    }
    public static List<NoteInfoVO> entityToVO_dirVO(List<NoteModel> noteModels,List<DirVO> dirs){
        List<NoteInfoVO> vos=new ArrayList<NoteInfoVO>(noteModels.size());
        for(int i=0,len=noteModels.size();i<len;i++){
            vos.add(new NoteInfoVO(noteModels.get(i),dirs.get(i)));
        }
        return vos;
    }
    public static List<NoteInfoVO> entityToVO_dirName(List<NoteModel> noteModels,List<String> dirs){
        List<NoteInfoVO> vos=new ArrayList<NoteInfoVO>(noteModels.size());
        for(int i=0,len=noteModels.size();i<len;i++){
            vos.add(new NoteInfoVO(noteModels.get(i),dirs.get(i)));
        }
        return vos;
    }


    public NoteInfoVO(NoteModel noteModel,DirVO dirVO){
        this(noteModel,dirVO.getName());
    }
    public NoteInfoVO(NoteModel noteModel,DirModel dirModel){
        this(noteModel,dirModel.getTitle());
    }
    public NoteInfoVO(NoteModel noteModel, String dirName){
        this.id=noteModel.getId();
        this.userId=noteModel.getUserId();
        this.name=noteModel.getTitle();
        this.createTime=noteModel.getCreateAt();
        this.updateTime=noteModel.getUpdateAt();
        this.dirId=noteModel.getDirId();
        this.dirName=dirName;
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
}
